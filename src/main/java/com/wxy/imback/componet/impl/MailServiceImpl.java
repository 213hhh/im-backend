package com.wxy.imback.componet.impl;

import com.wxy.imback.componet.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Autowired()
    private JavaMailSender  mailSender;

    @Value("${spring.mail.from}")
    private String from;


    /**
     * 发送邮箱验证码
     *
     * @param to      邮件发送人
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    @Override
    public void sendMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象1
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(from);
        //邮件接收人
        message.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        mailSender.send(message);
        log.info("邮件发成功:{}", message.toString());
    }
}
