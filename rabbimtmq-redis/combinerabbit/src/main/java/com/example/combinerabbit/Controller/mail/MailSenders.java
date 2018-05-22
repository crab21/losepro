package com.example.combinerabbit.Controller.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Controller
public class MailSenders {
    private static Logger logger = LogManager.getLogger();
    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value = "/msend")
    @ResponseBody
    public String mailSender() {
        System.out.println("--------------------------------------");
        logger.info("邮件开始发送.....");
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("pywang@dhlk-tech.com");
            messageHelper.setTo("pywang@dhlk-tech.com");

            messageHelper.setSubject("标题：发送Html内容");

            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            messageHelper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
        logger.info("邮件发送成功");
        return "ok";
    }
}
