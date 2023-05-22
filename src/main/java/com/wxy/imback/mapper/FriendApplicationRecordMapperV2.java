package com.wxy.imback.mapper;

import com.wxy.imback.model.entity.FriendApplicationRecord;
import com.wxy.imback.model.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author WXY
 * @Date 2023/5/22 8:39
 * @Version 1.0
 */
public interface FriendApplicationRecordMapperV2 {

    FriendApplicationRecord selectByFriendId(@Param("friend_id") Integer friendId);

}
