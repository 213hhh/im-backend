package com.wxy.imback.model;

import lombok.Data;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Data
public class LoginUser {


    private Long userId;

    /**
     * 用户名
     */
    private String userName;


    /**
     * E-MAIL
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

}
