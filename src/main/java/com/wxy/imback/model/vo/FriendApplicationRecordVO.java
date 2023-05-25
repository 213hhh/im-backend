package com.wxy.imback.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/24 19:09
 * @Version 1.0
 */
@Data
public class FriendApplicationRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;


    /**
     * 申请状态 0未审核 1通过 2不通过
     */
    private Integer auditStatus;


    /**
     * 申請時間
     */
    private Long applyTime;


    /**
     * 如果是群则为群id 好友为好友id
     */
    private Long businessId;

    /**
     * 审核时间
     */
    private Long auditTime;

    /**
     * 审核理由
     */
    private String auditReason;



}
