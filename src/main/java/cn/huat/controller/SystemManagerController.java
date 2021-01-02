package cn.huat.controller;

import cn.huat.pojo.User;
import cn.huat.service.SystemManagerService;
import cn.huat.util.EasyResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SystemManagerController <br/>
 * Description: <br/>
 * date: 2020/11/25 21:28<br/>
 *
 * @author suhd<br />
 */
@RestController
@RequestMapping("/system")
public class SystemManagerController {

    @Autowired
    private SystemManagerService systemManagerService;

    @PostMapping("/getUserList")
    public EasyResult getUserList(@RequestBody JSONObject param){

        // 获取当前用户账号
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        EasyResult result = systemManagerService.getUserListByManagerId(Integer.parseInt(userId),param);
        return result;
    }

    @PostMapping("/deleteUser")
    public EasyResult deleteUser(@RequestBody JSONObject param){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = String.valueOf(param.getInteger("userId"));
        String name = authentication.getName();
        if (userId.equals(name)){
            return EasyResult.ok("deleteError");
        }
        systemManagerService.deleteUserByUserId(param.getInteger("userId"));
        Integer maxPage = systemManagerService.getNowPage();
        return EasyResult.ok(maxPage);
    }

    @PostMapping("/getUser")
    public EasyResult getUser(@RequestBody JSONObject param){
        User user = systemManagerService.getUser(param.getInteger("userId"));
        return EasyResult.ok(user);
    }
    @PostMapping("/editUser")
    public EasyResult editUser(@RequestBody JSONObject param){
        systemManagerService.editUser(param);
        Integer nowPage = systemManagerService.getNowPage();
        return EasyResult.ok(nowPage);
    }
}
