package com.wxy.imback.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WXY
 * @Date 2023/5/22 16:42
 * @Version 1.0
 */
@Data
public class FriendListVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 昵称
     */
    private String nickName;

    /**
     * E-MAIL
     */
    private String email;


    /**
     * 性别
     */
    private Integer gender;

    /**
     * 头象
     */
    private String avatar;

    /**
     * 签名
     */
    private String personalSignature;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 手机号码
     */
    private String mobile;


}
