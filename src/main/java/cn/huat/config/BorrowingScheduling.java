package cn.huat.config;


import cn.huat.pojo.Borrowing;
import cn.huat.service.ArchiveService;
import cn.huat.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class BorrowingScheduling {

    @Autowired
    private ArchiveService archiveService;

    @Autowired
    private MailClient mailClient;

    @Scheduled(cron = "20 38 17 ? * *")//每分钟同步
    public void BorrowingEndTime() {
        Date today = new Date();

        List<Borrowing> borrowings = archiveService.getBorrowingsByState();
        if (borrowings != null && borrowings.size() > 0) {
            for (Borrowing borrowing : borrowings) {
                Date date = DateUtils.addDateDays(borrowing.getReturntime(), -3);
                if (today.getTime() < borrowing.getReturntime().getTime()
                        && today.getTime() >= date.getTime() ) {
                    //发送短信
                    new Thread(()-> {
                        String subject = "归还通知";
                        String content = "你好,你借阅的文件号为"+borrowing.getIleno()+"的文件即将到期,请及时归还或续借";
                        mailClient.sendMail("827714432@qq.com", subject, content);

                    }).start();

                } else if (borrowing.getReturntime().getTime() >= today.getTime()){
                    //自动归还
                    archiveService.updateStateByBorrowingNo(borrowing.getBorrowingno(),"3", null);
                } else {
                    continue;
                }
            }
        }

    }
}
