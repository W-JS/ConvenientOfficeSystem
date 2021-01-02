package cn.huat.mapper;


import cn.huat.pojo.Meting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**************/
@Repository
@Mapper
public interface MetingMapper {
    int deleteByPrimaryKey(String meetingserialno);

    int insert(Meting record);

    int insertSelective(Meting record);

    Meting selectByPrimaryKey(String meetingserialno);

    int updateByPrimaryKeySelective(Meting record);

    int updateByPrimaryKey(Meting record);

    List<Meting> findall();//查询到所以结果

    List<String> findByUser(int user);

    int setState(String meetingSerialNo);
}