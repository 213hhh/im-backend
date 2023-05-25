package com.wxy.imback.model.params.contactsparams;

import com.wxy.imback.constant.AuditStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/24 19:48
 * @Version 1.0
 */
@Data
public class FriendAuditParam implements Serializable {


    private static long serialVersionUID = 1L;


    /**
     *  审核状态 0:待审核 1:通过 2:拒绝
     */
    private Integer audit ;




    /**
     * 如果是群则为群id 好友为好友id
     */
    private Long businessId;

    /**
     * 审核理由
     */
    private String auditReason;
}
