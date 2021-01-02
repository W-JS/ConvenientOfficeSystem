package cn.huat.service;

import cn.huat.pojo.Meting;

import java.util.Set;

public interface AttendeeService {
    int insertParticipant(Meting meting, String participants);

    String findAccountBymeetingSerialNo(String meetingSerialNo);

    int updateForm(String participants,String meetingSerialNo);

    int  notifyAll(String meetingSerialNo);

    Set<String> findNotInformation();

//    int insertAttendee(String participants, String meetingserialno);
}
