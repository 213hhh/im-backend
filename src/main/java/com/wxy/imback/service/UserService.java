package com.wxy.imback.service;

import com.wxy.imback.expection.BizException;
import com.wxy.imback.model.params.userparams.UserLoginByMailQuery;
import com.wxy.imback.model.params.userparams.UserRegisterByMailParam;
import com.wxy.imback.model.vo.FriendListVO;
import com.wxy.imback.model.vo.FriendAuditVO;
import com.wxy.imback.model.vo.UserVO;

import java.util.List;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
public interface UserService {

    /**
     * 邮箱注册
     *
     * @param param
     * @return
     */
    Boolean userRegisterByMail(UserRegisterByMailParam param, String request) throws BizException;


    /**
     * 邮箱登陆
     *
     * @param param
     * @return
     */
    String userLoginByMail(UserLoginByMailQuery param);

    /**
     * 根据邮箱获取当前用户
     *
     * @param mail
     * @return
     */
    UserVO findUserByMail(String mail);


}
