package cn.huat.controller;

import cn.huat.pojo.Meting;
import cn.huat.service.AttendeeService;
import cn.huat.service.MeetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/*
跳转到通知界面
 */
@Controller
@RequestMapping("/att")
public class AttendeeController {
    @Resource
   private MeetingService meetingService;
    @Resource
    private AttendeeService attendeeService;

    @RequestMapping("/noti/{user}")
    public String notilist(Model auditor, @PathVariable("user") int user) {

        List<Meting> metings = meetingService.findNotInformation();//
        auditor.addAttribute("meetings", metings);//往前台传数据
        auditor.addAttribute("user", user);//往前台传入user
        return "NotificationList";
    }

    @RequestMapping("/notification/{meetingSerialNo}")//传入会议的编号进行删除
    public String notification(@PathVariable("meetingSerialNo") String meetingSerialNo) {

        Meting byMeetingSerialNo = meetingService.findByMeetingSerialNo(meetingSerialNo);//获取会议编号
        Integer informer = byMeetingSerialNo.getInformer();//通过会议编号获取通知人的号码
        attendeeService.notifyAll(meetingSerialNo);
//        meetingService.deleteForm(meetingSerialNo);//删除申请人申请的会议
        return "redirect:/att/noti" + "/" + informer;//重定向到当前页面
    }
}
