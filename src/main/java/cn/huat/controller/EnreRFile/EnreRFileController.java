package cn.huat.controller.EnreRFile;


import cn.huat.mapper.DepartmentMapper;
import cn.huat.mapper.EnreRFileMapper;
import cn.huat.mapper.UserMapper;
import cn.huat.pojo.EnreRFile;
import cn.huat.pojo.RFile;
import cn.huat.pojo.SFile;
import cn.huat.pojo.User;
import cn.huat.service.impl.EnreRFileServiceImpl.EnreRFileServiceImpl;
import cn.huat.util.EasyResult;
import cn.huat.util.UUIDGenerator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/EnreRFile")
public class EnreRFileController {

    @Autowired
    EnreRFileServiceImpl enreRFileService;


    @Autowired
    EnreRFileMapper rFileMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    DepartmentMapper departmentMapper;


    /*
     * 保存表格提交的数据
     * */
    private JSONObject jsonObject;

    /*
    *
    * 保存登入人所在单位的人员信息
    * */

    private  List<User> userList;

    /*
     * 主主页面获取各个功能所需收文数量
     *
     * to do ：需要进行各项收文人员的识别，验证身份
     * */
    @RequestMapping("/toRFileindex")
    public String topage(HttpServletRequest httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        获取登录人所在部门的人员信息
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(authentication.getName()));
        userList = userMapper.selectByDept(user.getDepartment());


        List<EnreRFile> rFiles = enreRFileService.getAllRFileByState(1, Integer.parseInt(authentication.getName()));
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("regFiles", enreRFileService.getEnreRFile().size());//新登记收文数目
        session.setAttribute("CregFiles", enreRFileService.getAllEnreRFileByStateAndReceiver(0,Integer.parseInt(authentication.getName())).size());//续登记收文数目
        session.setAttribute("uicrRFiles", rFiles.size());//录入收文数目
        session.setAttribute("frmAuditRFile",enreRFileService.getAllRFileByStateAndAudit(2,Integer.parseInt(authentication.getName())).size());//审核收文数目
        session.setAttribute("frmDraftRFile",enreRFileService.getAllRFileByStateAndDraftsman(3,Integer.parseInt(authentication.getName())).size());//拟办收文数目
        session.setAttribute("frmAuthorizeRFile",enreRFileService.getAllRFileByStateAndAuthorizeman(4,Integer.parseInt(authentication.getName())).size());//批办收文数目
        session.setAttribute("frmTranPersonRFile",enreRFileService.getAllRFileByStateAndTranPerson(5,Integer.parseInt(authentication.getName())).size());//承办收文数目
        return "EnreRFile/enrerfileindex";
    }

    /*
     * ajax提交数据，后端保存数据
     * */
    @PostMapping("/getregistrationFile")
    @ResponseBody
    public EasyResult getregistrationFile(@RequestBody JSONObject param) {
        jsonObject = param;
        EasyResult result = EasyResult.build(1, "数据提交成功", param);
        return result;
    }


    /*
     * 所要登记收文列表窗口
     * */
    @RequestMapping("/enreList")
    public String topage1(Model httpServletRequest) {
        httpServletRequest.addAttribute("regFilesRes", enreRFileService.getEnreRFile());
//        System.out.println(enreRFileService.newRegistrationFiles().toString());
        return "EnreRFile/newregistrationfileList";
    }

    /*
     * 所要续登记收文列表窗口
     * */
    @RequestMapping("/cenreList")
    public String ctopage(Model httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        httpServletRequest.addAttribute("cregFilesRes", enreRFileService.getAllEnreRFileByStateAndReceiver(0,Integer.parseInt(authentication.getName())));
//        System.out.println(enreRFileService.newRegistrationFiles().toString());
        return "EnreRFile/cnewregistrationfileList";
    }

    /*
    * 获取录入收文列表
    * */

    @RequestMapping("/uicrRFileList")
    public String utopage(Model httpServletRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<EnreRFile> rFiles = enreRFileService.getAllRFileByState(1, Integer.parseInt(authentication.getName()));
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (rFiles.size() > 0) {
            for (int i = 0; i < rFiles.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                SFile sFile = enreRFileService.getAllSFileByFileNo(rFiles.get(i).getFileno());//收文登记表中文号和发文中文号一致的发文
                map.put("filecontent", sFile.getFilecontent());
                map.put("affixcontent", sFile.getAffixcontent());
                map.put("mainto", sFile.getMainto());
                map.put("cc", sFile.getCc());
                map.put("enrerfile", rFiles.get(i));
                mapList.add(map);
            }
            httpServletRequest.addAttribute("uicrRFileList", mapList);
        } else {
            System.out.println("无数据");
        }

        return "EnreRFile/UICRecordRFileList";
    }


    /*
    *
    * 获取审核收文列表
    * */
    @RequestMapping("/frmAuditRFileList")
    public String frmAuditRFileList(Model httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        /*
        * 获取收文登记表中状态为2的收文，同时签收人是当前登录人员
        * */
        List<EnreRFile> rFiles = enreRFileService.getAllRFileByStateAndAudit(2, Integer.parseInt(authentication.getName()));

        List<Map<String, Object>> mapList = new ArrayList<>();
        if (rFiles.size() > 0) {
            for (int i = 0; i < rFiles.size(); i++) {
                Map<String, Object> map = new HashMap<>();

                /*
                * 改 20020-12-16
                * */
                RFile rFile = enreRFileService.getAllRFileByFileNo(rFiles.get(i).getFileno());//收文登记表中文号和收文中文号一致的发文
                map.put("filecontent", rFile.getFilecontent());
                map.put("affixcontent", rFile.getAffixcontent());
                map.put("mainto",rFile.getMainto());
                map.put("cc", rFile.getCc());
                map.put("enrerfile", rFiles.get(i));
               /*
               * 名称
               * */
                map.put("maintoname", departmentMapper.findByNo(rFile.getMainto()));
                map.put("recorderName",userMapper.getNameByNo(rFiles.get(i).getRecorder()));

                mapList.add(map);
            }
            httpServletRequest.addAttribute("frmAuditRFileList", mapList);
        }
        return "EnreRFile/FrmAuditRFileList";
    }


    /*
    * 获取拟办收文列表
    *
    * */
    @RequestMapping("/frmDraftRFileList")
    public String frmDraftRFileList(Model httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<EnreRFile> rFiles = enreRFileService.getAllRFileByStateAndDraftsman(3, Integer.parseInt(authentication.getName()));
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (rFiles.size() > 0) {
            for (int i = 0; i < rFiles.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                /*
                 * 改 20020-12-16
                 * */
                RFile rFile = enreRFileService.getAllRFileByFileNo(rFiles.get(i).getFileno());//收文登记表中文号和收文中文号一致的发文
                map.put("filecontent", rFile.getFilecontent());
                map.put("affixcontent", rFile.getAffixcontent());
                map.put("mainto",rFile.getMainto());
                map.put("cc", rFile.getCc());
                map.put("enrerfile", rFiles.get(i));
                /*
                 * 名称
                 * */
                map.put("maintoname", departmentMapper.findByNo(rFile.getMainto()));
                map.put("recorderName",userMapper.getNameByNo(rFiles.get(i).getRecorder()));
                map.put("draftName",userMapper.getNameByNo(rFiles.get(i).getDraftsman()));//拟办人名
                map.put("auditName",userMapper.getNameByNo(rFiles.get(i).getAuditor()));//审核人名
                map.put("authorizemanName",userMapper.getNameByNo(rFiles.get(i).getAuthorizeman()));//批办人人名
                map.put("tranpersonName",userMapper.getNameByNo(rFiles.get(i).getTranperson()));//承办人名
                mapList.add(map);
            }
            httpServletRequest.addAttribute("frmDraftRFileList", mapList);
        }
        return "EnreRFile/FrmDraftRFileList";
    }

    /*
     * 获取批办收文列表
     *
     * */
    @RequestMapping("/frmAuthorizeRFileList")
    public String frmAuthorizeRFileList(Model httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<EnreRFile> rFiles = enreRFileService.getAllRFileByStateAndAuthorizeman(4, Integer.parseInt(authentication.getName()));
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (rFiles.size() > 0) {
            for (int i = 0; i < rFiles.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                /*
                 * 改 20020-12-16
                 * */
                RFile rFile = enreRFileService.getAllRFileByFileNo(rFiles.get(i).getFileno());//收文登记表中文号和收文中文号一致的发文
                map.put("filecontent", rFile.getFilecontent());
                map.put("affixcontent", rFile.getAffixcontent());
                map.put("mainto",rFile.getMainto());
                map.put("cc", rFile.getCc());
                map.put("enrerfile", rFiles.get(i));
                /*
                 * 名称
                 * */
                map.put("maintoname", departmentMapper.findByNo(rFile.getMainto()));
                map.put("recorderName",userMapper.getNameByNo(rFiles.get(i).getRecorder()));
                map.put("draftName",userMapper.getNameByNo(rFiles.get(i).getDraftsman()));//拟办人名
                map.put("auditName",userMapper.getNameByNo(rFiles.get(i).getAuditor()));//审核人名
                map.put("authorizemanName",userMapper.getNameByNo(rFiles.get(i).getAuthorizeman()));//批办人人名
                map.put("tranpersonName",userMapper.getNameByNo(rFiles.get(i).getTranperson()));//承办人名
                mapList.add(map);
            }
            httpServletRequest.addAttribute("frmAuthorizeRFileList", mapList);
        }
        return "EnreRFile/FrmAuthorizeList";
    }


    /*
     * 获取承办收文列表
     *
     * */
    @RequestMapping("/frmTransactRFileList")
    public String frmTransactRFileList(Model httpServletRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<EnreRFile> rFiles = enreRFileService.getAllRFileByStateAndTranPerson(5, Integer.parseInt(authentication.getName()));
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (rFiles.size() > 0) {
            for (int i = 0; i < rFiles.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                /*
                 * 改 20020-12-16
                 * */
                RFile rFile = enreRFileService.getAllRFileByFileNo(rFiles.get(i).getFileno());//收文登记表中文号和收文中文号一致的发文
                map.put("filecontent", rFile.getFilecontent());
                map.put("affixcontent", rFile.getAffixcontent());
                map.put("mainto",rFile.getMainto());
                map.put("cc", rFile.getCc());
                map.put("enrerfile", rFiles.get(i));
                /*
                 * 名称
                 * */
                map.put("maintoname", departmentMapper.findByNo(rFile.getMainto()));
                map.put("recorderName",userMapper.getNameByNo(rFiles.get(i).getRecorder()));
                map.put("draftName",userMapper.getNameByNo(rFiles.get(i).getDraftsman()));//拟办人名
                map.put("auditName",userMapper.getNameByNo(rFiles.get(i).getAuditor()));//审核人名
                map.put("authorizemanName",userMapper.getNameByNo(rFiles.get(i).getAuthorizeman()));//批办人人名
                map.put("tranpersonName",userMapper.getNameByNo(rFiles.get(i).getTranperson()));//承办人名
                mapList.add(map);
            }
            httpServletRequest.addAttribute("frmTransactRFileList", mapList);
        }
        return "EnreRFile/FrmTransactRFileList";
    }






    /*
    * 返回登记窗口
    * */

    @RequestMapping(value = "/registrationFile")
    public String registrationFile(Model model) {
        model.addAttribute("file", jsonObject);
        System.out.println(jsonObject);
        model.addAttribute("person", userList);
        return "EnreRFile/FrmEnreRFile";
    }

    /*
    * 返回续登记窗口
    * */
    @RequestMapping(value = "/cregistrationFile")
    public String cregistrationFile(Model model) {
        model.addAttribute("file", jsonObject);
        System.out.println(jsonObject);
        model.addAttribute("person", userList);
        return "EnreRFile/CFrmEnreRFile";
    }

    /*
     * 录入收文接口
     * */
    @RequestMapping(value = "/uicrecordRFile")
    public String UICRecordRFile(Model model) {
        model.addAttribute("file", jsonObject);
        System.out.println(jsonObject);
        model.addAttribute("person", userList);
        /*
        * 获取部门列表
        *
        * */

        model.addAttribute("dept",departmentMapper.findDepartment());

        return "EnreRFile/UICRecordRFile";
    }

    /*
    * 审核收文窗口
    * */
    @RequestMapping(value = "/frmAuditRFile")
    public String frmAuditRFile(Model model) {
        model.addAttribute("file", jsonObject);
        System.out.println(jsonObject);
        model.addAttribute("person", userList);
        /*
         * 获取部门列表
         *
         * */

        model.addAttribute("dept",departmentMapper.findDepartment());
        return "EnreRFile/FrmAuditRFile";
    }


    /*
    * 拟办收文窗口
    * */
    @RequestMapping(value = "/frmDraftRFile")
    public String frmDraftRFile(Model model) {
        model.addAttribute("file", jsonObject);
        System.out.println(jsonObject);
        model.addAttribute("person", userList);
        return "EnreRFile/FrmDraftRFile";
    }

    /*
    * 批办收文窗口
    *
    * */
    @RequestMapping(value = "/frmAuthorizeRFile")
    public String frmAuthorizeRFile(Model model) {
        model.addAttribute("file", jsonObject);
        System.out.println(jsonObject);
        model.addAttribute("person", userList);
        return "EnreRFile/FrmAuthorize";
    }

    /*
    * 承办收文窗口
    * */
    @RequestMapping(value = "/frmTransactRFile")
    public String frmTransactRFile(Model model) {
        model.addAttribute("file", jsonObject);
        System.out.println(jsonObject);
        model.addAttribute("person", userList);
        return "EnreRFile/FrmTransactRFile";
    }



    /*
    * 收文登记表提交表单，保存表单数据到数据库
    * */
    @PostMapping("/frmenrerfile")
    public String frmenrerfile(EnreRFile Sfile) {
        Sfile.setId(UUIDGenerator.get32UUID());
        /*
        *
        * 重写，规范成业务层
        * */
        rFileMapper.insert(Sfile);
        return "redirect:/EnreRFile/enreList";
    }


    /*
    * 续登记窗口表单提交和登记窗口表单提交
    *
    * */
    @PostMapping("/cfrmenrerfile")
    public String cfrmenrerfile(EnreRFile Sfile) {
        System.out.println(Sfile + "aa");
        rFileMapper.updateByPrimaryKey(Sfile);
        return "redirect:/EnreRFile/cenreList";
    }


    /*
    * 录入收文窗口表单提交接口
    * */
    @PostMapping("/uicrrfile")
    public String uicrrfile(@RequestParam Map<String,String> uicrrfile, RFile rfile) {
        System.out.println(uicrrfile + "aa");
        System.out.println("收文"+rfile);
//        rFileMapper.updateByPrimaryKey(Sfile);
//        System.out.println(uicrrfile.get("state"));
        if ("1".equals(uicrrfile.get("state"))){
            return "redirect:/EnreRFile/uicrRFileList";
        }else if("2".equals(uicrrfile.get("state"))){//保存收文，同时更新收文登记表中的状态
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setState(Integer.parseInt(uicrrfile.get("state")));
            enreRFile.setId(uicrrfile.get("id"));
            enreRFile.setAuditor(Integer.parseInt(uicrrfile.get("auditor")));//更新审核人

            enreRFileService.insertRFile(rfile,enreRFile);
            return "redirect:/EnreRFile/uicrRFileList";
        }
        return "redirect:/EnreRFile/uicrRFileList";
    }



    /*
    *
    * 审核收文表单提交接口
    * */
    @PostMapping("/frmauditrfile")
    public String frmauditrfile(@RequestParam Map<String,String> frmauditrfile, RFile rfile) {

        System.out.println(frmauditrfile);

        if ("2".equals(frmauditrfile.get("state"))){
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setId(frmauditrfile.get("id"));
            enreRFile.setAuditing(frmauditrfile.get("auditing"));
            enreRFile.setDraftsman(Integer.parseInt(frmauditrfile.get("draftsman")));
            enreRFile.setAuthorizeman(Integer.parseInt(frmauditrfile.get("authorizeman")));
            enreRFile.setTranperson(Integer.parseInt(frmauditrfile.get("tranperson")));
            enreRFileService.updateRFile(enreRFile);
            return "redirect:/EnreRFile/frmAuditRFileList";
        }else if("3".equals(frmauditrfile.get("state"))){//保存收文，同时更新收文登记表中的状态
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setState(Integer.parseInt(frmauditrfile.get("state")));
            enreRFile.setId(frmauditrfile.get("id"));
            enreRFile.setAuditing(frmauditrfile.get("auditing"));
            enreRFile.setDraftsman(Integer.parseInt(frmauditrfile.get("draftsman")));
            enreRFile.setAuthorizeman(Integer.parseInt(frmauditrfile.get("authorizeman")));
            enreRFile.setTranperson(Integer.parseInt(frmauditrfile.get("tranperson")));
            enreRFileService.updateRFile(enreRFile);
            return "redirect:/EnreRFile/frmAuditRFileList";
        }
        return "redirect:/EnreRFile/frmAuditRFileList";
    }

    /*
    *
    * 拟办收文表单提交接口
    * */

    @PostMapping("/frmdraftrfile")
    public String frmdraftrfile(@RequestParam Map<String,String> frmauditrfile, RFile rfile) {
        System.out.println("拟办"+frmauditrfile);
        if ("3".equals(frmauditrfile.get("state"))){
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setId(frmauditrfile.get("id"));
            enreRFile.setDrafting(frmauditrfile.get("drafting"));

            enreRFileService.updateRFile(enreRFile);
            return "redirect:/EnreRFile/frmDraftRFileList";
        }else if("4".equals(frmauditrfile.get("state"))){//保存收文，同时更新收文登记表中的状态
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setState(Integer.parseInt(frmauditrfile.get("state")));
            enreRFile.setId(frmauditrfile.get("id"));
            enreRFile.setDrafting(frmauditrfile.get("drafting"));
            enreRFileService.updateRFile(enreRFile);
            return "redirect:/EnreRFile/frmDraftRFileList";
        }
        return "redirect:/EnreRFile/frmDraftRFileList";
    }


    /*
    * 批办收文表单提交接口
    * */
    @PostMapping("/frmauthorizerfile")
    public String frmauthorizerfile(@RequestParam Map<String,String> frmauditrfile, RFile rfile) {
        System.out.println("批办"+frmauditrfile);
        if ("4".equals(frmauditrfile.get("state"))){
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setId(frmauditrfile.get("id"));
            enreRFile.setAuthorizing(frmauditrfile.get("authorizing"));
            enreRFileService.updateRFile(enreRFile);
            return "redirect:/EnreRFile/frmAuthorizeRFileList";
        }else if("5".equals(frmauditrfile.get("state"))){//保存收文，同时更新收文登记表中的状态
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setState(Integer.parseInt(frmauditrfile.get("state")));
            enreRFile.setId(frmauditrfile.get("id"));
            enreRFile.setAuthorizing(frmauditrfile.get("authorizing"));
            enreRFileService.updateRFile(enreRFile);
            return "redirect:/EnreRFile/frmAuthorizeRFileList";
        }
        return "redirect:/EnreRFile/frmAuthorizeRFileList";
    }


    /*
    * 承办收文表单提交接口
    *
    * */
    @PostMapping("/frmtrasactrfile")
    public String frmtrasactrfile(@RequestParam Map<String,String> frmauditrfile, RFile rfile) {
        if ("5".equals(frmauditrfile.get("state"))){
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setId(frmauditrfile.get("id"));
            enreRFile.setTransacting(frmauditrfile.get("transacting"));
            enreRFileService.updateRFile(enreRFile);
            return "redirect:/EnreRFile/frmTransactRFileList";
        }else if("6".equals(frmauditrfile.get("state"))){//保存收文，同时更新收文登记表中的状态
            EnreRFile enreRFile = new EnreRFile();
            enreRFile.setState(Integer.parseInt(frmauditrfile.get("state")));
            enreRFile.setId(frmauditrfile.get("id"));
            enreRFile.setTransacting(frmauditrfile.get("transacting"));
            enreRFileService.updateRFile(enreRFile);
            return "redirect:/EnreRFile/frmTransactRFileList";
        }
        return "redirect:/EnreRFile/frmTransactRFileList";
    }

    /*
    * 录入表单获取指定部门的人员列表
    * */

    @PostMapping("getDept")
    @ResponseBody
    public EasyResult getDepa(@RequestBody String dept){
        System.out.println(dept);
        EasyResult res = EasyResult.build(1,"success",userMapper.selectByDept(dept));
        return res;
    }


}
