package cn.huat.mapper;

import cn.huat.pojo.Minute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/******************/
@Repository
@Mapper
public interface MinuteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Minute record);

    int insertSelective(Minute record);

    Minute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Minute record);

    int updateByPrimaryKey(Minute record);

    List<Minute> selectAll();

    Minute selectByFileNo(String fileno);

    //    胡涛


    Minute findByMinute(String minuteRecorder);

    int findCount();

    List<Minute> findallMinutes();
}