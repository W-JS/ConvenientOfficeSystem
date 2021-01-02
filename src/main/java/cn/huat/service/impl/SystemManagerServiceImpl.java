package cn.huat.service.impl;

import cn.huat.mapper.UserMapper;
import cn.huat.pojo.User;
import cn.huat.service.SystemManagerService;
import cn.huat.util.EasyResult;
import cn.huat.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: SystemManagerServiceImpl <br/>
 * Description: <br/>
 * date: 2020/11/26 13:22<br/>
 *
 * @author suhd<br />
 */
@Service
public class SystemManagerServiceImpl implements SystemManagerService {
    @Autowired
    private UserMapper userMapper;

    private static Integer total;
    private static Integer max;

    @Override
    public EasyResult getUserListByManagerId(Integer managerId, JSONObject param) {
        PageUtil pageUtil = new PageUtil();
        // 获取部门主管部门

        User user = userMapper.selectByPrimaryKey(managerId);

        Integer count = userMapper.selectAllCountByUserDepartment(user.getDepartment());

        Integer page = param.getInteger("page");
        Integer size = param.getInteger("size");
        Integer index = (page - 1)*size;
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 10;
        }
        pageUtil.setPage(page);
        pageUtil.setPageSize(size);
        pageUtil.setTotal(count);
        total = pageUtil.getTotal();
        max = pageUtil.getPageSize();
        List<User> userList = userMapper.selectAllUserByPage(user.getDepartment(), index, size);
        pageUtil.setData(userList);

        return EasyResult.ok(pageUtil);
    }

    @Override
    public int deleteUserByUserId(Integer userId) {
        return userMapper.deleteUserByUserId(userId);
    }

    @Override
    public Integer getNowPage(){
        if ((total - 1) % max != 0){
            return (total-1)/max +1;
        }
        return (total-1)/max;
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int editUser(JSONObject param) {
        int result = userMapper.updateUserInfoByAccount(param);
        return result;
    }

}
