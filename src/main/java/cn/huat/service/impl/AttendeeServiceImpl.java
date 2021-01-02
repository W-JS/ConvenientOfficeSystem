package cn.huat.service.impl;

import cn.huat.mapper.AttendeeMapper;
import cn.huat.mapper.UserMapper;
import cn.huat.pojo.Attendee;
import cn.huat.pojo.Meting;
import cn.huat.pojo.User;
import cn.huat.service.AttendeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service("attendeeService")
public class AttendeeServiceImpl implements AttendeeService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AttendeeMapper attendeeMapper;
    @Resource
    private User user;
    @Resource
    private Attendee attendee;

    @Override
    public int insertParticipant(Meting meting, String participants) {
        System.out.println("gyyggggg");
        String[] person = participants.split(",");
        int number = 0;
        int k=0;
        if(attendeeMapper.find().size()>0) {
            System.out.println("ok");
            //若attendee表存在记录
           k = attendeeMapper.findMaxcount();//从attendee查询得到的id最大值
            System.out.println("attendee表存在记录");
        }
        for (int i = 1; i < person.length; i++) {
            System.out.println(person[i]);
            int account = Integer.parseInt(person[i]);//将输入的字符串强转为int类型

            if (userMapper.selectByPrimaryKey(account) != null) {    //判断用户账号是否存在
                attendee.setMeetingserialno(meting.getMeetingserialno());
                attendee.setId(k+i);
                user = userMapper.selectByPrimaryKey(account);//通过用户账号获取user表
                attendee.setDepartment(user.getDepartment());//直接通过user表获得部门
                attendee.setOccupation(user.getOccupation());//直接通过user表获得职务
                attendee.setAccount(account);
                attendee.setName(user.getName());//直接通过user表获得姓名
                attendee.setInformationornot(1);//默认值为"未通知"
                attendeeMapper.insert(attendee);
                number++;
            }
        }
      return number;
    }

    @Override
    public String findAccountBymeetingSerialNo(String meetingSerialNo) {
//        通过会议序号从attend表查询account
        List<Integer> accounts = attendeeMapper.findBymeetingSerialNo(meetingSerialNo);
//String[] account=new String[accounts.size()-1];
        String account = new String();
        for (int i = 1; i < accounts.size(); i++) {
            System.out.println("yes");
            account += accounts.get(i).toString() + ",";
        }
        return account;
    }

    @Override
    public int updateForm(String participants, String meetingSerialNo) {
        String[] person = participants.split(",");
        int number = 0;
        int k=0;
        if(attendeeMapper.find().size()>0) {
            //若attendee表存在记录
            k = attendeeMapper.findMaxcount();//从attendee查询得到的id最大值
            System.out.println("attendee表存在记录");
        }
        for (int i = 1; i < person.length; i++) {

            int account = Integer.parseInt(person[i]);//将输入的字符串强转为int类型

            if (userMapper.selectByPrimaryKey(account) != null) {    //判断用户账号是否存在
                if (attendeeMapper.findIdByPandM(account, meetingSerialNo)!= null) {
                    //找不到通知记录就插入
                    attendee.setId(k+i);
                    attendee.setMeetingserialno(meetingSerialNo);
                    user = userMapper.selectByPrimaryKey(account);//通过用户账号获取user表
                    attendee.setDepartment(user.getDepartment());//直接通过user表获得部门
                    attendee.setOccupation(user.getOccupation());//直接通过user表获得职务
                    attendee.setAccount(account);
                    attendee.setName(user.getName());//直接通过user表获得姓名
                    attendee.setInformationornot(1);//默认值为"未通知"
                    attendeeMapper.insert(attendee);//插入新通知用户
                    number++;
                } else {
                    attendee.setId(attendeeMapper.findIdByPandM(account, meetingSerialNo));
                    attendee.setMeetingserialno(meetingSerialNo);
                    user = userMapper.selectByPrimaryKey(account);//通过用户账号获取user表
                    attendee.setDepartment(user.getDepartment());//直接通过user表获得部门
                    attendee.setOccupation(user.getOccupation());//直接通过user表获得职务
                    attendee.setAccount(account);
                    attendee.setName(user.getName());//直接通过user表获得姓名
                    attendee.setInformationornot(1);//默认值为"未通知"
                    attendeeMapper.updateByPrimaryKey(attendee);
                    number++;
                }
            }
        }

        return number;
    }

    @Override
    public int notifyAll(String meetingSerialNo) {
        return attendeeMapper.notificate(meetingSerialNo);
    }

    @Override
    public Set<String> findNotInformation() {
        return attendeeMapper.findNotInformation();
    }

//    @Override
//    public int insertAttendee(String participants, String meetingSerialNo) {
//        String[] person = participants.split(",");
//        int number = 0;
//        int k=0;
//        if(attendeeMapper.find()!=null) {
//            //若attendee表存在记录
//            k = attendeeMapper.findMaxcount();//从attendee查询得到的id最大值
//            System.out.println("attendee表存在记录");
//        }
//        for (int i = 1; i < person.length; i++) {
//            System.out.println(person[i]);
//            int account = Integer.parseInt(person[i]);//将输入的字符串强转为int类型
//
//            if (userMapper.selectByPrimaryKey(account) != null) {    //判断用户账号是否存在
//                if (attendeeMapper.findIdByPandM(account, meetingSerialNo) == null) {
//                    //找不到通知记录就插入
//                    attendee.setId(k+i);
//                    attendee.setMeetingserialno(meetingSerialNo);
//                    user = userMapper.selectByPrimaryKey(account);//通过用户账号获取user表
//                    attendee.setDepartment(user.getDepartment());//直接通过user表获得部门
//                    attendee.setOccupation(user.getOccupation());//直接通过user表获得职务
//                    attendee.setAccount(account);
//                    attendee.setName(user.getName());//直接通过user表获得姓名
//                    attendee.setInformationornot(1);//默认值为"未通知"
//                    attendeeMapper.insert(attendee);//插入新通知用户
//                    number++;
//                } else {
//                    attendee.setId(attendeeMapper.findIdByPandM(account, meetingSerialNo));
//                    attendee.setMeetingserialno(meetingSerialNo);
//                    user = userMapper.selectByPrimaryKey(account);//通过用户账号获取user表
//                    attendee.setDepartment(user.getDepartment());//直接通过user表获得部门
//                    attendee.setOccupation(user.getOccupation());//直接通过user表获得职务
//                    attendee.setAccount(account);
//                    attendee.setName(user.getName());//直接通过user表获得姓名
//                    attendee.setInformationornot(1);//默认值为"未通知"
//                    attendeeMapper.updateByPrimaryKey(attendee);
//                    number++;
//                }
//            }
//        }
//
//        return number;
//    }
}
