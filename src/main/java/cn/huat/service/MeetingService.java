package cn.huat.service;

import cn.huat.pojo.Meting;

import java.util.List;

public interface MeetingService {
    int  insert(Meting meting);
    List<Meting> find();

    Meting findByMeetingSerialNo(String meetingSerialNo);

    int updateForm(Meting meting);

    int deleteForm(String meetingSerialNo);

    List<String> findByUser(int user);//通过user 找到相关的会议序号

    List<Meting> findNotInformation();

    int  setState(String meetingSerialNo);
}
