package com.wxy.imback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wxy.imback.model.entity.FriendApplicationRecord;
import com.wxy.imback.model.vo.FriendApplicationRecordVO;
import com.wxy.imback.model.vo.FriendAuditVO;
import com.wxy.imback.model.vo.FriendVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【friend_application_record】的数据库操作Mapper
 * @createDate 2023-05-21 20:42:56
 * @Entity generator.FriendApplicationRecord
 */
public interface FriendApplicationRecordMapper extends BaseMapper<FriendApplicationRecord> {

    /**
     * 申请好友
     *
     * @param userId 用户id
     * @return
     */
    Boolean applyFriend(@Param("user_id") Long userId);

    /**
     * 根据好友id查询好友申请记录
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @return
     */
    FriendApplicationRecordVO selectByFriendId(@Param("user_id") Long userId, @Param("friend_id") Long friendId);

    /**
     * 获取好友申请列表
     *
     * @param userId 用户id
     * @return
     */
    List<FriendAuditVO> selectFriendApplyList(@Param("user_id") Long userId);


    /**
     * 更新好友申请状态
     *
     * @param userId    用户id
     * @param friendId  好友id
     * @param applyTime 申请时间
     * @return
     */
    Boolean updateApplyStatus(@Param("user_id") Long userId, @Param("friend_id") Long friendId, @Param("apply_time") long applyTime);

    /**
     * 审核好友申请
     *
     * @param audit       审核状态
     * @param businessId  好友id
     * @param userId      用户id
     * @param auditTime   审核时间
     * @param auditReason 审核理由
     */
    Boolean updateAuditStatus(@Param("audit") int audit, @Param("business_id") Long businessId, @Param("audit_reason") String auditReason, @Param("user_id") Long userId, @Param("audit_time") long auditTime);

    List<FriendVO> selectFriendRequestList(@Param("user_id") Long userId);
}




