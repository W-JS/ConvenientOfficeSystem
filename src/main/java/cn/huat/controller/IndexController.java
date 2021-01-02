package cn.huat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    /**
     * @return 进入主页
     */
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String returnIndex() {
        return "forward:/user/index";
    }

    /**
     * 会议入口
     * @param session
     * @return
     */
    @RequestMapping("/meetingindex")
    public String index(HttpSession session){
//        session.setAttribute("user",202001001);//模拟用户进入会议系统
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("user", authentication.getName());
        return "meeting";
    }
}
