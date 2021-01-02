package cn.huat.convenientofficesystem;

import cn.huat.ConvenientofficesystemApplication;
import cn.huat.config.MailClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ConvenientofficesystemApplication.class)
class ConvenientofficesystemApplicationTests {

    @Autowired
    MailClient mailClient;

    @Test
    void contextLoads() {
        mailClient.sendMail("827714432@qq.com", "ceshi", "ceshi");
    }

}
