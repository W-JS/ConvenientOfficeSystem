package cn.huat.controller;

import cn.huat.mapper.UserMapper;
import cn.huat.pojo.Notice;
import cn.huat.pojo.User;
import cn.huat.service.impl.NoticeServiceImpl;
import cn.huat.util.EasyResult;
import cn.huat.util.MessageCheckUtil;
import cn.huat.util.NoticePageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: LoginController <br/>
 * Description: <br/>
 * date: 2020/11/25 14:02<br/>
 *
 * @author suhd<br />
 */

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private NoticeServiceImpl noticeServiceImpl;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public String login() {
        return "login";
    }


    // 获取并发送6位手机验证码
    @GetMapping("/message/{telephoneNumber}")
    @ResponseBody
    public EasyResult getTelephoneMsg(@PathVariable String telephoneNumber){
        String messageCode = MessageCheckUtil.getMessageCode(telephoneNumber);
        return EasyResult.ok(messageCode);
    }



    /**
     * @return 进入主页，显示所有公告
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index(Model model, NoticePageUtil noticePageUtil, Principal principal, HttpSession session) {
        noticePageUtil.setRows(noticeServiceImpl.findNoticesRows());
        noticePageUtil.setPath("/user/index");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(authentication.getName()));

        List<Notice> list = noticeServiceImpl.findNotices(user.getDepartment(), noticePageUtil.getOffset(), noticePageUtil.getLimit());
        List<Map<String, Object>> notices = new ArrayList<>();
        if (list != null) {
            for (Notice notice : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("notice", notice);
                notices.add(map);
            }
        }
        model.addAttribute("notices", notices);

        model.addAttribute("authority",getaAuthority());
        model.addAttribute("name",user.getName());
        session.setAttribute("account", user.getAccount());
        return "index";
    }

    /**
     * @param current 页码
     * @param noticeno 公告编号
     * @return 进入主页公告详情页面，显示该公告
     */
    @RequestMapping(path = "/detail", method = RequestMethod.GET)
    public String getNotice(@RequestParam("current") int current, @RequestParam("noticeno") String noticeno, Model model) {
        // 公告
        Notice notice = noticeServiceImpl.findNoticeById(noticeno);
        model.addAttribute("notice", notice);

        model.addAttribute("authority",getaAuthority());

        model.addAttribute("current",current);

        return "/notice/notice-index";
    }

    public int getaAuthority(){
        // 获取当前登陆用户账号
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());

        Integer account = Integer.valueOf(authentication.getName());
        User user = noticeServiceImpl.findUserByAccount(account);
        int authority = 0;
        if (user.getAccessauthority().equals("administrator") || user.getAccessauthority().equals("subAdministrator")){
            authority = 1;
        }
        return authority;
    }

    @RequestMapping("/manager")
    public String toManager(){
        return "systemManager";
    }

    @RequestMapping("/unAuth")
    public String unAuth() {
        return "unauth";
    }
    @GetMapping("/SendFileIndex")
    public String toSendFile(HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("account", authentication.getName());
        return "SendFileIndex";
    }



}
