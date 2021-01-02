package cn.huat.service.impl;

import cn.huat.mapper.MetingMapper;
import cn.huat.pojo.Meting;
import cn.huat.service.AttendeeService;
import cn.huat.service.MeetingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {
    @Resource
    private MetingMapper metingMapper;
    @Resource
    private AttendeeService attendeeService;

    @Override
    public int insert(Meting meting) {

        return metingMapper.insert(meting);
    }

    @Override
    public List<Meting> find() {
        return metingMapper.findall();//实现findall方法
    }

    @Override
    public Meting findByMeetingSerialNo(String meetingSerialNo) {
     return metingMapper.selectByPrimaryKey(meetingSerialNo);

    }

    @Override
    public int updateForm(Meting meting) {//修改表单

        return metingMapper.updateByPrimaryKeySelective(meting);
    }

    @Override
    public int deleteForm(String meetingSerialNo) {
        return metingMapper.deleteByPrimaryKey(meetingSerialNo);
    }

    @Override
    public List<String> findByUser(int user) {
        return metingMapper.findByUser(user);
    }

    @Override
    public List<Meting> findNotInformation() {
     Set<String> m=new HashSet<String>();
        List<Meting> metings = new ArrayList<>();//存放会议安排表
        if(attendeeService.findNotInformation().size()>0){
            m.addAll(attendeeService.findNotInformation());

//            String[] hh = m.toArray(new String[m.size()]);
            System.out.println(m.size());
            String[] hh=new String[m.size()];
            m.toArray(hh);
            for (int i = 0; i < hh.length; i++) {
                System.out.println("进入循环");

                Meting meting = metingMapper.selectByPrimaryKey(hh[i]);
                metings.add(meting);
                System.out.println(metings);

            }
        }
        //在attendee表找到未通知的会议序号

        System.out.println(m);
        //在meting表中通过会议序号找到会议安排表

        return metings;
    }

    @Override
    public int setState(String meetingSerialNo) {
        return metingMapper.setState(meetingSerialNo);
    }


}
