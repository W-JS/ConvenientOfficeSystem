package cn.huat.service.impl;

import cn.huat.mapper.MinuteMapper;
import cn.huat.pojo.Minute;
import cn.huat.service.MeetingService;
import cn.huat.service.MinuteService;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("minuteService")
public class MinuteServiceImpl implements MinuteService {
    @Resource
    private MinuteMapper minuteMapper;
    @Resource
    private MeetingService meetingService;

    @Override
    public int insertMinute(Minute minute) {

        return minuteMapper.insert(minute);
    }

    @Override
    public Minute findById(Integer id) {
        return minuteMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Minute> findall(int user) {
        List<String> meetingSerialNo = meetingService.findByUser(user);//通过meeting获得草拟人的表的会议编号
//        int meetingSerialNO = minuteMapper.findCount(user);
        List<Minute> Minute = new ArrayList<>();//存放查找的会议纪要
//        System.out.println("查到的会议序号为" + meetingSerialNo);
//        System.out.println("记录表中会议号size"+meetingSerialNo.size());//记录表中会议号的size
        for (int j = 0; j < meetingSerialNo.size()-1; j++) {
//            System.out.println(meetingSerialNo.get(j));
//            System.out.println(minuteMapper.findByMinute(meetingSerialNo.get(j)));
            if (minuteMapper.findByMinute(meetingSerialNo.get(j)) != null) {
                //判断meetingSerialNo.get(j)是否为空
                //如果为空,则表示未找到;
                //若不为空,则把数据添加到会议纪要结果集中
                Minute.add(j, minuteMapper.findByMinute(meetingSerialNo.get(j)));
//                System.out.println(Minute);
            }
        }
        return Minute;
    }

    @Override
    public List<Minute> findallMinutes() {
        return minuteMapper.findallMinutes();
    }


}
