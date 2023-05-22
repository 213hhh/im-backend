package com.wxy.imback.componet;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
public interface MailService {


    /**
     * 发送邮箱验证码
     * @param to
     * @param subject
     * @param content
     */
    void sendMail(String to, String subject, String content);

}
