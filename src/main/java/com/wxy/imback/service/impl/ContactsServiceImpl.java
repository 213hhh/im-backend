package com.wxy.imback.service.impl;

import com.wxy.imback.constant.AuditStatus;
import com.wxy.imback.constant.BizCodeEnum;
import com.wxy.imback.constant.BusinessType;
import com.wxy.imback.interceptor.LoginInterceptor;
import com.wxy.imback.mapper.FriendApplicationRecordMapper;
import com.wxy.imback.mapper.FriendMapper;
import com.wxy.imback.mapper.UserMapper;
import com.wxy.imback.model.LoginUser;
import com.wxy.imback.model.entity.FriendApplicationRecord;
import com.wxy.imback.model.entity.User;
import com.wxy.imback.model.params.contactsparams.FindUserSecretParam;
import com.wxy.imback.model.params.contactsparams.FriendApplyParam;
import com.wxy.imback.model.params.contactsparams.FriendAuditParam;
import com.wxy.imback.model.vo.FriendApplicationRecordVO;
import com.wxy.imback.model.vo.FriendAuditVO;
import com.wxy.imback.model.vo.UserFriendApplyVO;
import com.wxy.imback.service.ContactsService;
import com.wxy.imback.utils.CheckUtil;
import com.wxy.imback.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author WXY
 * @Date 2023/5/24 9:20
 * @Version 1.0
 */
@Slf4j
@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FriendApplicationRecordMapper friendApplicationRecordMapper;

    @Autowired
    private FriendMapper friendMapper;

    /**
     * 好友验证过期时间3天
     */
    public static final long VALIDATION_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 3;

    /**
     * 查找好友
     *
     * @param param
     * @return
     */
    @Override
    public UserFriendApplyVO findFriend(FindUserSecretParam param) throws Exception {
        Assert.isTrue(CheckUtil.isEmail(param.getUserIdentify()), BizCodeEnum.EMAIL_IS_NULL.getMessage());
        User user = userMapper.selectByMail(param.getUserIdentify());
        Assert.isTrue(user != null, BizCodeEnum.ACCOUNT_UNREGISTER.getMessage());
        return getUserFriendApplyVO(user);
    }

    @NotNull
    private static UserFriendApplyVO getUserFriendApplyVO(User user) throws Exception {
        UserFriendApplyVO userFriendApplyVO = new UserFriendApplyVO();
        userFriendApplyVO.setUserIdentify(CommonUtil.encrypt(user.getUserId().toString()));
        userFriendApplyVO.setNickName(user.getNickName());
        userFriendApplyVO.setAvatar(user.getAvatar());
        return userFriendApplyVO;
    }

    /**
     * 添加好友
     *
     * @param param
     * @return
     */
    @Override
    public Boolean applyFriend(FriendApplyParam param) throws Exception {
        LoginUser loginUser = LoginInterceptor.threadLocal.get();
        Long businessId = Long.parseLong(CommonUtil.decrypt(param.getUserIdentify()));
        Assert.isTrue(!loginUser.getUserId().equals(businessId), BizCodeEnum.CAN_NOT_ADD_MYSELF.getMessage());
        FriendApplicationRecordVO friendApplicationRecordVO = friendApplicationRecordMapper.selectByFriendId(loginUser.getUserId(), businessId);
        if (friendApplicationRecordVO == null) {
            return saveFriend(loginUser, businessId);
        }
        Assert.isTrue(!(friendApplicationRecordVO.getAuditStatus().equals(AuditStatus.PASS)),
                BizCodeEnum.ADD_AS_FRIEND.getMessage());
        if (friendApplicationRecordVO.getAuditStatus().equals(AuditStatus.NOT_AUDIT)) {
            log.info("重复发送间隔:{}", (System.currentTimeMillis() - friendApplicationRecordVO.getApplyTime()) / 1000);
            Assert.isTrue((System.currentTimeMillis() - friendApplicationRecordVO.getApplyTime()) / 1000 >= VALIDATION_EXPIRATION_TIME, BizCodeEnum.ADD_REPEAT.getMessage());
        }
        return friendApplicationRecordMapper.updateApplyStatus(loginUser.getUserId(), businessId, System.currentTimeMillis());
    }

    private Boolean saveFriend(LoginUser loginUser, Long friendId) {
        FriendApplicationRecord friendApplicationRecord = new FriendApplicationRecord();
        friendApplicationRecord.setUserId(loginUser.getUserId());
        friendApplicationRecord.setBusinessType(BusinessType.USER_ID);
        friendApplicationRecord.setApplyTime(System.currentTimeMillis());
        friendApplicationRecord.setBusinessId(friendId);
        friendApplicationRecord.setApplyReason("");
        int i = friendApplicationRecordMapper.insert(friendApplicationRecord);
        return i > 0;
    }

    /**
     * 获取好友申请列表
     *
     * @return
     */
    @Override
    public List<FriendAuditVO> friendApplyList() {
        LoginUser loginUser = LoginInterceptor.threadLocal.get();
        return friendApplicationRecordMapper.selectFriendApplyList(loginUser.getUserId());

    }

    /**
     * 审核好友申请
     * todo 分布式事务
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean auditFriendApply(FriendAuditParam param) {
        LoginUser loginUser = LoginInterceptor.threadLocal.get();
        long auditTime = System.currentTimeMillis();
        Assert.isTrue(param.getAudit() != null && param.getBusinessId() != null && param.getAuditReason() != null, BizCodeEnum.PARAM_ERROR.getMessage());
        if (param.getAudit().equals(AuditStatus.PASS)) {
            //通过好友申请
            applyFriendRecord(param, loginUser, auditTime);
            //todo MQ同步好友关系
            return true;
        }
        if (param.getAudit().equals(AuditStatus.NOT_PASS)) {
            //拒绝
            return disagreeFriendRecord(param, loginUser, auditTime);
        }
        return false;
    }

    /**
     * 拒绝好友申请
     *
     * @param param
     * @param loginUser
     * @param auditTime
     * @return
     */
    private Boolean disagreeFriendRecord(FriendAuditParam param, LoginUser loginUser, long auditTime) {
        return friendApplicationRecordMapper.updateAuditStatus(param.getAudit(), param.getBusinessId(), param.getAuditReason(), loginUser.getUserId(), auditTime);

    }

    /**
     * 通过好友申请
     *
     * @param param
     * @param loginUser
     * @param auditTime
     */
    private void applyFriendRecord(FriendAuditParam param, LoginUser loginUser, long auditTime) {
        friendApplicationRecordMapper.updateAuditStatus(param.getAudit(), param.getBusinessId(), param.getAuditReason(), loginUser.getUserId(), auditTime);
        friendMapper.insert(loginUser.getUserId(), param.getBusinessId(), auditTime);
    }
}
