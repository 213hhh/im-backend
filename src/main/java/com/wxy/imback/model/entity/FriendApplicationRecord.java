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
    private Integer userId;

    /**
     * 好友id
     */
    private Integer friendId;

    /**
     * 申请状态 0未通过 1通过
     */
    private Integer passed;

    /**
     * 是否删除 0未删除 1已删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 是否拉黑 0未拉黑 1拉黑
     */
    private Integer isBlock;

    /**
     * 添加时间
     */
    private Long addTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}