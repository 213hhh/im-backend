package com.wxy.imback.model.params.contactsparams;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/24 9:18
 * @Version 1.0
 */
@Data
public class FriendApplyParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户标识
     */
    private String userIdentify;

    /**
     * 申请理由
     */
    private String applyReason;

}
