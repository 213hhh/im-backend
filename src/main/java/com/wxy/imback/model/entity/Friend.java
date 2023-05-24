package com.wxy.imback.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName friend
 */
@TableName(value ="friend")
@Data
public class Friend implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 用户id

     */
    private Long userId;

    /**
     * 好友id
     */
    private Long friendId;

    /**
     * 添加时间
     */
    private Long applyTime;

    /**
     * 审核时间
     */
    private Long auditTime;

    /**
     * 0未删除 1删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 删除时间
     */
    private Long delTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}