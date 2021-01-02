package cn.huat.controller;

import cn.huat.mapper.UserMapper;
import cn.huat.pojo.Meting;
import cn.huat.pojo.Minute;
import cn.huat.pojo.User;
import cn.huat.service.MeetingService;
import cn.huat.service.MinuteService;
import cn.huat.util.DateUtil;
import cn.huat.util.UUIDGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
会议纪要
1.通过主页进入会议纪要界面
2.纪要列表显示的时meeting数据的内容
3.点击按钮“编写纪要”跳转到会议纪要的表单填写界面
4.提交，跳转到纪要列表

 */
@Controller
@RequestMapping("/minute")
public class MinuteController {
    @Resource
    private MeetingService meetingService;
    @Resource
    private MinuteService minuteService;
    @Resource
    private UserMapper userMapper;

    /*************************************/
    @RequestMapping("/minutelist/{user}")//传入meeting列表
    public String minutelist(@PathVariable("user") int user, Model minute) {

        System.out.println("11111111111111111111111111111111111111111");
        List<Meting> meetings = meetingService.find();//查询所有meeting数据
        minute.addAttribute("meeting", meetings);//传给前台数据
        minute.addAttribute("user", user);//传给前台用户名
        return "MinuteList";
    }

    //跳转到会议记录表单 并填写
    @RequestMapping("/minuteform/{meetingSerialNo}")
    public String minuteform(@PathVariable("meetingSerialNo") String meetingSerialNo, Model minuteform) {
        System.out.println("222222222222222222222222222222222222222222222");
        //生成随机主键ID
        Integer s = Integer.parseInt(DateUtil.getSDFM(new Date()));
        minuteform.addAttribute("minuteID",s);
        //生成随机文号
        String file = UUIDGenerator.get16UUID();
        minuteform.addAttribute("fileNo",file);
        Meting meeting = meetingService.findByMeetingSerialNo(meetingSerialNo);//1.根据会议编号查询meeting表
        minuteform.addAttribute("meeting", meeting);//2.把meeting表传给前台


        return "MinuteForm";
    }

    @RequestMapping("/insert")
    public String minuteupdate(Minute minute,String aatime,String bbtime) {//把Minute 表传入数据库中
        System.out.println("minute");
        Integer s = Integer.parseInt(String.valueOf(minute.getId()));//将string类型转化成int类型
        minute.setId(s);//设置id
        System.out.println(aatime);
        System.out.println(bbtime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象：年月日 时分秒
        Date date = new Date();
        try {
            date = sdf.parse(aatime);
            minute.setStarttime(date);
            date=sdf.parse(bbtime);
            minute.setEndtime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        minuteService.insertMinute(minute);
        String no = minute.getMeetingserialno();//会议序号
        Meting meeting = meetingService.findByMeetingSerialNo(no);//根据序号查询到meeting表
        int minuterecorder = meeting.getMinuterecorder();//记录人
        meetingService.setState(minute.getMeetingserialno());
        return "redirect:/minute/minutelist" + "/" + minuterecorder;
    }

    @RequestMapping("/minuteviewlist/{user}")
    public String minuteview(Model minuteview, @PathVariable("user") int user) {
        System.out.println("用户名纪要的账号为" + user);
//        List<Minute> minutes = minuteService.findall(user);//通过user 找到所有相关的mintue数据
//        minuteview.addAttribute("user", user);
        List<Minute> minutes=minuteService.findallMinutes();
        minuteview.addAttribute("minutes", minutes);
        return "MinuteVIewlist";
    }


}
