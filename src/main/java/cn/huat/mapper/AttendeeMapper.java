package cn.huat.mapper;


import cn.huat.pojo.Attendee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**********/
@Repository
@Mapper
public interface AttendeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attendee record);

    int insertSelective(Attendee record);

    Attendee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attendee record);

    int updateByPrimaryKey(Attendee record);

    List<Attendee> find();

    List<Integer> findBymeetingSerialNo(String meetingSerialNo);

    Integer findIdByPandM(int account, String meetingSerialNo);



    int findMaxcount();

    int notificate(String meetingSerialNo);

    Set<String> findNotInformation();

//    int deleteByMetingNO(String meetingserialno);
}