package com.wxy.imback.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;


    /**
     * 头像
     */
    private String avatar;


    /**
     * 生日
     */
    private Date birthday;


    /**
     * 性别
     */
    private Integer gender;


}
