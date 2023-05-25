package com.wxy.imback.utils;


import com.wxy.imback.constant.BizCodeEnum;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Slf4j
public class CheckUtil {

    /**
     * 邮箱正则
     */
    private static final Pattern MAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    /**
     * 手机号正则
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");


    /**
     * 密码正则
     */
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^\\\\w{4,32}$");

    /**
     * 邮箱验证
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Assert.isTrue(StringUtils.isNotBlank(email), BizCodeEnum.EMAIL_IS_NULL.getMessage());
        Matcher m = MAIL_PATTERN.matcher(email);
        return m.matches();

    }


    public static void confirmPassword(String password, String confirmPassword) {
        Assert.isTrue(StringUtils.isNotBlank(password), BizCodeEnum.PASSWORD_IS_NULL.getMessage());
        Assert.isTrue(password.equals(confirmPassword), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
    }

    public static void verifyUserName(String userName) {
        Assert.isTrue(StringUtils.isNotBlank(userName), BizCodeEnum.USER_NAME_IS_NULL.getMessage());
    }

}