package com.wxy.imback.service;

import com.wxy.imback.expection.BizException;
import com.wxy.imback.model.params.UserLoginByMailQuery;
import com.wxy.imback.model.params.UserRegisterByMailParam;
import com.wxy.imback.model.vo.FriendVO;
import com.wxy.imback.model.vo.UserVO;
import com.wxy.imback.utils.Result;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 添加好友
     *
     * @param mail
     * @return
     */
    Integer addFriend(String mail);

    /**
     * 获取新好友申请列表
     *
     * @return
     */
    List<FriendVO> getFriendAddList();

    /**
     * 好友申请审核
     * @param id
     */
    void checkFriend(Integer id);
}
