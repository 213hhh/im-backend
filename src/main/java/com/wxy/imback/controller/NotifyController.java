package com.wxy.imback.controller;

import com.google.code.kaptcha.Producer;
import com.wxy.imback.constant.BizCodeEnum;
import com.wxy.imback.constant.SendCodeEnum;
import com.wxy.imback.model.params.SendMailCodeParams;
import com.wxy.imback.service.NotifyService;
import com.wxy.imback.utils.CommonUtil;
import com.wxy.imback.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class NotifyController {


    @Autowired
    private Producer captchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private NotifyService notifyService;

    /**
     * 验证码过期时间
     */
    private static final long CAPTCHA_CODE_EXPIRED = 60 * 1000 * 10;

    /**
     * 获取图形验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String text = captchaProducer.createText();
        log.info("验证码:{}", text);
        //存储到redis
        redisTemplate.opsForValue().set(getCaptcha(request), text, CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);
        BufferedImage image = captchaProducer.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("验证码异常:{}", e);
        }
    }

    /**
     * 发送邮箱验证码
     *
     * @param params
     * @param request
     * @return
     */
    @GetMapping("send_code")
    public Result sendRegisterCode(@RequestBody SendMailCodeParams params,
                                   HttpServletRequest request) {
        String key = getCaptcha(request);
        String cacheCaptcha = redisTemplate.opsForValue().get(key);
        log.info("key :{}", key);
        log.info("cacheCaptcha :{}", cacheCaptcha);
        if (params == null || !params.getCaptcha().equalsIgnoreCase(cacheCaptcha)) {
            return Result.error(BizCodeEnum.CODE_ERROR);
        }
        redisTemplate.delete(key);
        return notifyService.sendCode(SendCodeEnum.USER_REGISTER, params.getTo());
    }


    public String getCaptcha(HttpServletRequest request) {
        Long ip = CommonUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String key = "user-service:captcha:" + CommonUtil.MD5(ip + userAgent);
        log.info("ip:{}", ip);
        log.info("userAgent:{}", userAgent);
        log.info("key:{}", key);
        return key;
    }


}
