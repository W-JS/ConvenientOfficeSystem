package cn.huat.config;

import cn.huat.pojo.Notice;
import cn.huat.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class NoticeEffectivenessScheduling {

    @Autowired
    private NoticeServiceImpl noticeServiceImpl;

    // 设置日期输出的格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "30 17 21 * * ?")// 每天21:17:30执行一次
    public void noticeEffectiveness() {
        Date today = new Date();

        List<Notice> list = noticeServiceImpl.findAllNotices();
        if (list != null) {
            System.out.println("当前时刻：" + sdf.format(today));
            for (Notice notice : list) {
                if (notice.getExpirationtime().getTime() < today.getTime()) {
                    System.out.println(notice.getNoticeno() + " 在 " + sdf.format(notice.getExpirationtime()) + " 时已过期");
                    noticeServiceImpl.updateNoticeByDelete(notice.getPublishingtime(), 0, 1, notice.getNoticeno());
                } else {
                    System.out.println(notice.getNoticeno() + " 在 " + sdf.format(notice.getExpirationtime()) + " 时过期");
                }
            }
        }
    }

//      implements SchedulingConfigurer
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        // 定时任务表达式，实际项目可以从配置文件、数据库等中获取
//        String cron = "0/5 * * * * *";
//        scheduledTaskRegistrar.addCronTask(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("每五秒执行定时任务！");
//            }
//        }, cron);
//    }
}
