package cn.huat.controller;

import cn.huat.mapper.DepartmentMapper;
import cn.huat.mapper.UserMapper;
import cn.huat.pojo.EnreSFlie;
import cn.huat.pojo.SFile;
import cn.huat.pojo.User;
import cn.huat.service.EnreSFileService;
import cn.huat.service.SFileService;
import cn.huat.util.DateUtil;
import cn.huat.util.UUIDGenerator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * Description 发文登记
 *
 * @author helaxest
 * @date 2020/11/29  11:18
 * @mail:
 * @since JDK 1.8
 */

@Controller
@RequestMapping("/enreSFile")
public class EnreSFileController {
    @Autowired
    private EnreSFileService enreSFileService;
    @Autowired
    private SFileService sFileService;
    @Autowired
    private UserMapper userMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * id的取值
     *
     * @return FF_创建当天时间 _16位随机字符
     */
    public String getMm() {
        return "FF_" + DateUtil.getSDF(new Date()) + "_" + UUIDGenerator.get16UUID();
    }

    /**
     * @param draftno 草拟文号
     * @param sfile   发文
     * @param account 用户账号
     * @param Account 用户账号
     * @param dept    部门
     * @param user    用户
     * @return
     */
    @GetMapping("/addpage/{draftno}/{account}")
    public String addPage(@PathVariable("draftno") String draftno,
                          Model sfile, @PathVariable("account") Integer account,
                          Model Account, Model dept, Model user) {
        sfile.addAttribute("sfile", sFileService.findSFileByDraftno(draftno));
        Account.addAttribute("account", account);
        dept.addAttribute("depts", departmentMapper.findDepartment());
        return "EnreSFile";
    }

    /**
     * @param enreSFile 发文登记
     * @param account   账号
     * @param target    操作类型
     * @return
     */
    @PostMapping(value = "/add/{account}/{target}")
    public String addSFile(EnreSFlie enreSFile, @PathVariable("account") Integer account,
                           @PathVariable("target") Integer target) {
        SFile sFile = sFileService.findSFileByFileno(enreSFile.getId());
        sFile.setState("登记完毕");
        sFileService.updateSFile(sFile);
        System.out.println(enreSFile.getReceiverorgan());
        String str = enreSFile.getReceiverorgan();
        String[] split = str.split("\\|");
        for (int i = 1; i < split.length; i++) {
            System.out.println(split[i]);
            if (userMapper.findByDept(split[i])!=null) {
                enreSFile.setId(getMm());
                enreSFile.setReceiverorgan(split[i]);
                enreSFile.setReceiver(userMapper.findByDept(split[i]));
                enreSFileService.addEnreSFile(enreSFile);
            }
        }
        return "redirect:/sfile/findAll" + "/" + account + "/" + target;
    }
//    @ResponseBody
//    @PostMapping("/find")
//    public List<User> findUserByDept(@RequestBody JSONObject param) {
//        String department = param.getString("department");
//        System.out.println(department);
//        List<User> Users= userMapper.findByDept(department);
//        //存放user的名字
//        return Users;
//    }
}
