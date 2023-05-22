package com.wxy.imback.model.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WXY
 * @Date 2023/5/21 21:56
 * @Version 1.0
 */
@Data
public class FriendVO implements Serializable {
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

    /**
     * 好友审核 0未通过 1通过
     */
    private Integer passed;

}
