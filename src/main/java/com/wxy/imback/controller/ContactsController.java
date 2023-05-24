package com.wxy.imback.controller;

import com.wxy.imback.model.params.contactsparams.FindUserSecretParam;
import com.wxy.imback.model.params.contactsparams.FriendApplyParam;
import com.wxy.imback.model.params.contactsparams.FriendAuditParam;
import com.wxy.imback.model.vo.FriendAuditVO;
import com.wxy.imback.model.vo.UserFriendApplyVO;
import com.wxy.imback.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author WXY
 * @Date 2023/5/24 8:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    /**
     * 查找好友
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/find_friend")
    public UserFriendApplyVO findFriend(@RequestBody FindUserSecretParam param) throws Exception {
        return contactsService.findFriend(param);
    }

    /**
     * 添加好友
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/apply_friend")
    public Boolean applyFriend(@RequestBody FriendApplyParam param) throws Exception {
        return contactsService.applyFriend(param);
    }

    /**
     * 获取新好友申请列表
     *
     * @return
     */
    @GetMapping("/friend_apply_list")
    public List<FriendAuditVO> friendApplyList(){
        return contactsService.friendApplyList();
    }

    /**
     * 审核好友申请
     * @param param
     * @return
     */
    @PostMapping("/audit_friend_apply")
    public Boolean auditFriendApply(@RequestBody FriendAuditParam param  ){

        return contactsService.auditFriendApply(param);
    }


}
