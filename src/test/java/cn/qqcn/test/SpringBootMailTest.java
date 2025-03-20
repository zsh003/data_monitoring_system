package cn.qqcn.test;


import cn.qqcn.service.MailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootMailTest {

    @Autowired(required = false)
    private MailService mailService;

    @Test
    public void sendSimpleText(){
        String to="zhujiongos@126.com";
        String title="标题：简单的文本发送测试";
        String content="简单的文本";
        Assertions.assertTrue(mailService.sendSimpleText(to,title,content));
    }
}

