package com.wxy.imback.service.impl;

import com.wxy.imback.componet.MailService;
import com.wxy.imback.constant.BizCodeEnum;
import com.wxy.imback.constant.RedisCacheKey;
import com.wxy.imback.constant.SendCodeEnum;
import com.wxy.imback.service.NotifyService;
import com.wxy.imback.utils.CheckUtil;
import com.wxy.imback.utils.CommonUtil;
import com.wxy.imback.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.Assert.isTrue;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {


    private static String cacheValue;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource
    private MailService mailService;

    /**
     * 验证码标题
     */
    private static final String SUBJECT = "验证码";

    /**
     * 验证码内容
     */
    private static final String CONTENT = "您的验证码是%s,有效期60秒";

    /**
     * 验证时间戳为60s
     */
    private static final long EXPIRED_TIME = 60 * 1000;

    /**
     * 验证码过期时间
     */
    private static final long CODE_EXPIRED_TIME = 60 * 1000;

    /**
     * 邮箱发送验证码
     *
     * @param sendCodeEnum
     * @param to
     * @return
     */
    @Override
    public Result sendCode(SendCodeEnum sendCodeEnum, String to) {
        if (!CheckUtil.isEmail(to)) {
            return Result.error(BizCodeEnum.CODE_ERROR);
        }
        String code = CommonUtil.getCode(6);
        String codeCacheKey = String.format(RedisCacheKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);
        String codeCacheValue = redisTemplate.opsForValue().get(codeCacheKey);
        if (isDuplicateSend(codeCacheValue)) {
            return Result.error(BizCodeEnum.CODE_LIMITED);
        }
        String value = code + "_" + CommonUtil.getCurrentTimestamp();
        redisTemplate.opsForValue().set(codeCacheKey, value, CODE_EXPIRED_TIME, TimeUnit.MILLISECONDS);
        mailService.sendMail(to, SUBJECT, String.format(CONTENT, code));
        return Result.success(BizCodeEnum.SUCESS);
    }

    /**
     * 防止重复发送
     *
     * @param cacheValue
     * @return
     */
    @Nullable
    private static boolean isDuplicateSend(String cacheValue) {
        if (StringUtils.isBlank(cacheValue)) {
            return false;
        }
        String[] parts = cacheValue.split("_");
        isTrue(parts.length >= 2, "Invalid cacheValue format");
        long ttl = Long.parseLong(parts[1]);
        //当前时间戳-验证码发送时间戳，如果小于60秒，则不给重复发送
        if (CommonUtil.getCurrentTimestamp() - ttl < EXPIRED_TIME) {
            log.info("重复发送验证码,时间间隔:{} 秒", (CommonUtil.getCurrentTimestamp() - ttl) / 1000);
            return true;
        }
        return false;
    }

    /**
     * 校验验证码
     *
     * @param sendCodeEnum
     * @param to
     * @param code
     * @return
     */
    @Override
    public boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code) {
        String cacheKey = String.format(RedisCacheKey.CHECK_CODE_KEY, sendCodeEnum.name(), to);
        String cacheValue = redisTemplate.opsForValue().get(cacheKey);
        if (StringUtils.isBlank(cacheValue)) {
            return false;
        }
        String[] parts = cacheValue.split("_");
        isTrue(parts.length >= 1, "Invalid cacheValue format");
        String cacheCode = cacheValue.split("_")[0];
        if (cacheCode.equalsIgnoreCase(code)) {
            redisTemplate.delete(cacheKey);
            return true;
        }
        return false;
    }
}
