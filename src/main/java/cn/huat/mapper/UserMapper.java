package cn.huat.mapper;

import cn.huat.pojo.User;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer account);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer account);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据当前部门主管部门查询当前部门所有员工
     * @param userDepartment
     * @return
     */
    List<User> selectAllUserByPage(@Param("userDepartment")String userDepartment, @Param("index")Integer index, @Param("size")Integer size);

    /**
     * 根据用户部门查询当前部门人数
     * @param userDepartment
     * @return
     */
    Integer selectAllCountByUserDepartment(@Param("userDepartment")String userDepartment);

    Integer deleteUserByUserId(@Param("userId") Integer userId);


    /**
     * 根据用户账户修改除权限和密码之外其他信息
     * @param param
     * @return
     */
    Integer updateUserInfoByAccount(JSONObject param);

    /***************************************************************************************/
    List<User> findUserByOccupation(String occupation);
    List<User> findAll();
    List<User> findUserAndDept();


    Integer findByDept(String department);

    /*
     * 根据部门查询当前部门的人数
     *
     * */
    List<User> selectByDept(String dept);


    /*
     * 2020-12-16
     * */
    String getNameByNo(int no);
}