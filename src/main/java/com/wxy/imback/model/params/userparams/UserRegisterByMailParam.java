package com.wxy.imback.model.params.userparams;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Data
public class UserRegisterByMailParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * e-mail
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    private String confirmPassword;


    /**
     * 验证码
     */
    private String captcha;


}
