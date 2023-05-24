package com.wxy.imback.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/24 9:15
 * @Version 1.0
 */
@Data
public class UserFriendApplyVO implements Serializable {

    private static final long serialVersionUID = 1L;


    private String userIdentify;

    private String nickName;

    private String avatar;
}
