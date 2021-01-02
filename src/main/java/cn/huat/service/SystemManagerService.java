package cn.huat.service;

import cn.huat.pojo.User;
import cn.huat.util.EasyResult;

import com.alibaba.fastjson.JSONObject;


/**
 * ClassName: SystemManagerService <br/>
 * Description: <br/>
 * date: 2020/11/26 13:20<br/>
 *
 * @author suhd<br />
 */

public interface SystemManagerService {
    EasyResult getUserListByManagerId(Integer managerId, JSONObject param);

    int deleteUserByUserId(Integer userId);

    Integer getNowPage();

    User getUser(Integer userId);

    int editUser(JSONObject param);
}
