package cn.huat.controller;


import cn.huat.mapper.UserMapper;
import cn.huat.pojo.SFile;
import cn.huat.service.SFileService;
import cn.huat.util.DateUtil;
import cn.huat.util.StateUtil;
import cn.huat.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Description 处理发文
 *
 * @author helaxest
 * @date 2020/11/29  11:20
 * @mail:
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/sfile")
public class SFileController {
    @Autowired
    private SFileService sFileService;
    @Autowired
    private UserMapper userMapper;

    /**
     * draftNo的取值
     *
     * @return 生成:CN_创建当天时间 _16位随机字符
     */
    public String getSs() {
        return "CN_" + DateUtil.getSDF(new Date()) + "_" + UUIDGenerator.get16UUID();
    }

    /**
     * fileNo的取值
     *
     * @return FF_创建当天时间 _16位随机字符
     */
    public String getMm() {
        return "FF_" + DateUtil.getSDF(new Date()) + "_" + UUIDGenerator.get16UUID();
    }

    /**
     * 新建发文
     *
     * @param auditor     审核员
     * @param checkingman 复核员
     * @param signator    签发员
     * @param distributor 分发员
     * @return 跳转到发文表单页面
     */
    @GetMapping("/addpage/{account}/{target}")
    public String addPageNew(@PathVariable("account") Integer account, Model auditor, Model checkingman, Model signator, Model distributor, Model draftsMan, @PathVariable("target") Integer target, Model Target, Model user,
                             Model draftno, Model fileno,Model send) {

        draftsMan.addAttribute("account", account);//专门接收account
        //查出所有审核人员的信息
        auditor.addAttribute("auditors", userMapper.findUserByOccupation("副主任"));
        //查出所有复核人员的信息
        checkingman.addAttribute("checkingmans", userMapper.findUserByOccupation("副主任"));
        //查出所有签发人员的信息
        signator.addAttribute("signators", userMapper.findUserByOccupation("主任"));
        // 查出所有分发人员的信息
        distributor.addAttribute("distributors", userMapper.findUserByOccupation("助理"));
        Target.addAttribute("target", target);
        user.addAttribute("User", userMapper.findAll());
        draftno.addAttribute("draftno",getSs());//点击新建发文时生成草拟文号,存放在隐藏域
        fileno.addAttribute("fileno",getMm());//点击新建发文时生成文号,存放在隐藏域
        send.addAttribute("send",1);//设置send
        return "SFileInfo";
    }
    /**
     * 添加发文
     * @param sFile 发文
     * @return 重定向
     */
    @ResponseBody
    @PostMapping("/addpage")
    public String addSFile(SFile sFile) {
        System.out.println(sFile.getSend());
        System.out.println(sFile.getFilecontent());
        sFile.setState(StateUtil.getStateValue(sFile.getSend()));//设置状态
        if(sFileService.findSFileByDraftno(sFile.getDraftno())!=null){
            sFileService.updateSFile(sFile); }else {  sFileService.addSFile(sFile);}
        String str="s";
        return str;

    }
    /**
     *
     * @param accounts 账号
     * @param sFiles 发文
     * @param account  账号
     * @param target 操作类型
     * @param Target 操作类型
     * @return 跳转发文列表页面
     */
    @GetMapping(value = "/findAll/{account}/{target}")
    public String findSFile(@PathVariable("account") Integer accounts, Model sFiles, Model account, @PathVariable("target") Integer target, Model Target) {
        account.addAttribute("account", accounts);
        Target.addAttribute("target", StateUtil.getStateValue(target));
        sFiles.addAttribute("Sfiles", sFileService.findAll());
        return "SFile";
    }

    /**
     *
     * @param darftno 草拟文号
     * @param account 账号
     * @param target 操作类型
     * @param sFile 发文
     * @param auditor 审核
     * @param checkingman 复核
     * @param signator 签发
     * @param distributor 分发
     * @param Account 账号
     * @param Target  操作类型
     * @param user 用户
     * @return 跳转到编辑发文页面
     */
    @GetMapping("/updatesfile/{darftno}/{account}/{target}")
    public String updatePageByState(
            @PathVariable("darftno") String darftno,
            @PathVariable("account") Integer account,
            @PathVariable("target") Integer target,
            Model sFile, Model auditor, Model checkingman,
            Model signator, Model distributor, Model Account,
            Model Target, Model user) {
        user.addAttribute("User", userMapper.findAll());
        //查出所有审核人员的信息
        auditor.addAttribute("auditors", userMapper.findUserByOccupation("副主任"));
        //查出所有复核人员的信息
        checkingman.addAttribute("checkingmans", userMapper.findUserByOccupation("副主任"));
        //查出所有签发人员的信息
        signator.addAttribute("signators", userMapper.findUserByOccupation("主任"));
        // 查出所有分发人员的信息
        distributor.addAttribute("distributors", userMapper.findUserByOccupation("助理"));
        Target.addAttribute("target", target);
        sFile.addAttribute("SFILE", sFileService.findSFileByDraftno(darftno));
        Account.addAttribute("account", account);
        return "updateSFileInfo";
    }

    /**
     *
     * @param sFile 发文
     * @return 重定向的页面
     */
    @ResponseBody
    @PostMapping(value = "/updateSFile")
    public String updateSFile(SFile sFile) {
        System.out.println(sFile.getState());
        if(sFile.getState().length()>0){
            sFileService.updateSFile(sFile);
        }else{
            sFile.setState(StateUtil.getStateValue(sFile.getSend()));//设置状态
            sFileService.updateSFile(sFile);
        }
        System.out.println(sFile);
        String str="s";
        return str;
    }
    /**
     *
     * @param draftno 草拟文号
     * @param account 账号
     * @param target 目标
     * @return 重定向的页面
     */
    @GetMapping("/deletesfile/{draftno}/{account}/{target}")
    public String deleteSFile(@PathVariable("draftno") String draftno,
                              @PathVariable("account") Integer account,
                              @PathVariable("target") Integer target) {
        sFileService.deleteSFileByDraftno(draftno);
        return "redirect:/sfile/findAll" + "/" + account + "/" + target;
    }
    @GetMapping("/toIndex")
    public String toIndex(HttpSession session){
        session.removeAttribute("account");
        return "redirect:/" +"user/index";
    }
}
