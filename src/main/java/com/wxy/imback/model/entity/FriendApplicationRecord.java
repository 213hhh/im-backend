package com.wxy.imback.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName friend_application_record
 */
@TableName(value = "friend_application_record")
@Data
public class FriendApplicationRecord implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 业务类型 好友id或者群号
     */
    private Integer businessType;

    /**
     * 申请状态 0未审核 1通过 2不通过
     */
    private Integer auditStatus;

    /**
     * 是否删除 0未删除 1已删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 申请时间
     */
    private Long applyTime;

    /**
     * 是否拉黑 0未拉黑 1拉黑
     */
    private Integer isBlock;

    /**
     * 如果是群则为群id 好友为好友id
     */
    private Long businessId;

    /**
     * 审核时间
     */
    private Long auditTime;

    /**
     * 审核人id
     */
    private Integer auditId;

    /**
     * 申请理由
     */
    private String applyReason;

    /**
     * 审核理由
     */
    private String auditReason;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}