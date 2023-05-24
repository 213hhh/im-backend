package com.wxy.imback.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/21 21:56
 * @Version 1.0
 */
@Data
public class FriendAuditVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer userId;

    /**
     * 昵称
     */
    private String nickName;


    /**
     * 头像
     */
    private String avatar;

    /**
     * 申请状态 0未审核 1通过 2不通过
     */
    private Integer auditStatus;



}
