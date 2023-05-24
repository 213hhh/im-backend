package com.wxy.imback.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxy.imback.model.entity.Friend;
import org.apache.ibatis.annotations.Param;

/**
* @author Administrator
* @description 针对表【friend】的数据库操作Mapper
* @createDate 2023-05-21 20:42:50
* @Entity generator.Friend
*/
public interface FriendMapper extends BaseMapper<Friend> {

    /**
     * 插入好友信息
     * @param userId
     * @param businessId
     * @param auditTime
     */
    void insert(@Param("user_id") Long userId, @Param("business_id") Long businessId, @Param("audit_time") long auditTime);
}




