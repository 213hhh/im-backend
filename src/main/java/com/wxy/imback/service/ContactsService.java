package com.wxy.imback.service;

import com.wxy.imback.expection.BizException;
import com.wxy.imback.model.params.contactsparams.FindUserSecretParam;
import com.wxy.imback.model.params.contactsparams.FriendApplyParam;
import com.wxy.imback.model.params.contactsparams.FriendAuditParam;
import com.wxy.imback.model.vo.FriendAuditVO;
import com.wxy.imback.model.vo.FriendListVO;
import com.wxy.imback.model.vo.FriendVO;
import com.wxy.imback.model.vo.UserFriendApplyVO;

import java.util.List;

/**
 * @Author WXY
 * @Date 2023/5/24 9:19
 * @Version 1.0
 */
public interface ContactsService {

    /**
     * 查找好友
     * @param param
     * @return
     */
    UserFriendApplyVO findFriend(FindUserSecretParam param) throws Exception;

    /**
     * 添加好友
     * @param param
     * @return
     */
    Boolean applyFriend(FriendApplyParam param) throws Exception;


    /**
     * 获取新好友申请列表
     *
     * @return
     */
    List<FriendAuditVO> friendApplyList();

    /**
     * 审核好友申请
     * @param param
     * @return
     */
    Boolean auditFriendApply(FriendAuditParam param) ;


    /**
     * 好友请求列表
     * @return
     */
    List<FriendVO> friendRequestList();

    /**
     * 获取好友列表
     * @return
     */
    List<FriendListVO> getFriendList();
}
