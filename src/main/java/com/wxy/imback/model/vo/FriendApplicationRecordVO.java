package com.wxy.imback.model.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
    private Integer isDelete;

    /**
     *
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



}
