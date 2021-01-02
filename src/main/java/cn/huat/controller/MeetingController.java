package cn.huat.controller;

import cn.huat.mapper.AttendeeMapper;
import cn.huat.mapper.UserMapper;
import cn.huat.pojo.Meting;
import cn.huat.pojo.User;
import cn.huat.service.AttendeeService;
import cn.huat.service.MeetingService;
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

/**
 * 申请人员、审核人员
 */
@Controller
@RequestMapping("/apl")
public class MeetingController {
    @Resource
    private MeetingService meetingService;

    @Resource
    private AttendeeService attendeeService;

    @Resource
    private UserMapper userMapper;



    /**************申请人部分*******************/
    //①步骤一 填写表单
    //申请表单
    @RequestMapping("/appform/{user}")
    public String Aplf(Model b, @PathVariable("user") int user) {
        b.addAttribute("user", user);
        //生成随机会议序号
        String s = UUIDGenerator.get16UUID();//生成的会议序号
        b.addAttribute("meetingNo",s);
        //查到所有的用户
        List<User> all = userMapper.findAll();
        b.addAttribute("allUser",all);
        b.addAttribute("yyy",all);

        return "MeetingApplicationForm";
    }

    //②步骤二 写完表单,跳转到列表
    @RequestMapping("/insert")
    public String insert(Meting meting, String participants,String aatime,String bbtime) {
        //输出会议序号
        System.out.println(meting.getMeetingserialno());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象：年月日 时分秒
        Date date = new Date();
        try {
            date = sdf.parse(aatime);
            meting.setStarttime(date);
            date=sdf.parse(bbtime);
            meting.setEndtime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(participants);
        meetingService.insert(meting);//申请人插入meting
        attendeeService.insertParticipant(meting,participants);

//        return "redirect:/apl/applist";//重定向到申请列表
        return "redirect:/apl/find" + "/" + meting.getApplicator();
    }

    //③查询所有的会议安排表，将查询到的结果传到MeetingApplicationList
    @RequestMapping("/find/{user}")//
    public String find(Model a, @PathVariable("user") int user) {
        System.out.println("调用findall方法  获取meting表");
        List<Meting> metings = meetingService.find();//
        a.addAttribute("meeting", metings);//往前台传数据
        a.addAttribute("user", user);//往前台传user

        return "MeetingApplicationList";
    }

    //跳转到修改页面，申请人进入到MeetingApplicationList进行数据的修改
    @RequestMapping("/edit/{meetingSerialNo}")
    public String edit(Model editForm, @PathVariable("meetingSerialNo") String meetingSerialNo) {
        Meting byMeetingSerialNo = meetingService.findByMeetingSerialNo(meetingSerialNo);// 查询会议的form表单
        editForm.addAttribute("meting", byMeetingSerialNo);
        String p = attendeeService.findAccountBymeetingSerialNo(meetingSerialNo);
        editForm.addAttribute("participants",p);
        //查到所有的用户
        List<User> all = userMapper.findAll();
        editForm.addAttribute("allUser",all);

        return "MeetingApplicationEdit";
    }

    //修改当前的表单信息 保存
    @RequestMapping("/updateForm")
    public String updateForm(Meting meting,String participants,String aatime,String bbtime) {

        String meetingserialno = meting.getMeetingserialno();
        System.out.println(aatime);
        System.out.println(bbtime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//创建日期转换对象：年月日 时分秒
        Date date = new Date();
        try {
            date = sdf.parse(aatime);
            meting.setStarttime(date);
            date=sdf.parse(bbtime);
            meting.setEndtime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        meetingService.updateForm(meting);
        attendeeService.updateForm(participants,meetingserialno);
        return "redirect:/apl/find" + "/" + meting.getApplicator();
    }

    //删除当前的表单 保存
    @RequestMapping("/delete/{meetingSerialNo}")//传入会议的编号进行删除
    public String deleteForm(@PathVariable("meetingSerialNo") String meetingSerialNo) {
        Meting byMeetingSerialNo = meetingService.findByMeetingSerialNo(meetingSerialNo);//获取会议编号
        Integer applicator = byMeetingSerialNo.getApplicator();//通过会议编号获取申请人的号码
        meetingService.deleteForm(meetingSerialNo);//删除申请人申请的会议
        return "redirect:/apl/find" + "/" + applicator;//重定向到当前页面
    }

    /**************审核人部分*******************/
/*
1.审核人首先进入审核列表 查看申请人申请的会议记录
2.审核人可以对会议表单进行 通过 ，不通过 保存 查看详情 的操作
3.审核人对会议表单进行 通过 ，不通过 处理时重定向到列表
4审核人对会议表单进行 查看详情时跳转到详情页 点击退出按钮时退到审核列表
 */
    @RequestMapping("/reviewfind/{user}")
    public String reviewfind(Model auditor, @PathVariable("user") int user) {
        List<Meting> metings = meetingService.find();//
        auditor.addAttribute("meetings", metings);//往前台传数据
        auditor.addAttribute("user", user);//往前台传入user
        return "MeetingReviewList";
    }

    @RequestMapping("/review/{meetingSerialNo}")//获取审核的数据
    public String review(@PathVariable("meetingSerialNo") String meetingSerialNo, Model review) {
        //跳转到审核界面 根据会议编号,调用接口根据会议编号获取审核表单
        Meting meting = meetingService.findByMeetingSerialNo(meetingSerialNo);
        review.addAttribute("meting", meting);//往前台传入数据

        return "MeetingReviewForm";
    }

    @RequestMapping("/reviewupdate")//提交数据
    public String reviewupdate(Meting meting) {
        meetingService.updateForm(meting);//调用接口 更新数据
        return "redirect:/apl/reviewfind" + "/" + meting.getAuditor();
    }
}
