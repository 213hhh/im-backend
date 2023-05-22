package com.wxy.imback.controller;

import com.wxy.imback.constant.BizCodeEnum;
import com.wxy.imback.expection.BizException;
import com.wxy.imback.model.params.UserLoginByMailQuery;
import com.wxy.imback.model.params.UserRegisterByMailParam;
import com.wxy.imback.model.vo.FriendListVO;
import com.wxy.imback.model.vo.FriendVO;
import com.wxy.imback.model.vo.UserVO;
import com.wxy.imback.service.UserService;
import com.wxy.imback.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 邮箱注册
     *
     * @param param
     * @return
     */
    @PostMapping("/mail_register")
    public Result userRegisterByMail(@RequestBody UserRegisterByMailParam param, HttpServletRequest request) throws BizException {
        String header = request.getHeader("");
        return userService.userRegisterByMail(param, header) ? Result.success(BizCodeEnum.SUCESS) : Result.error(BizCodeEnum.REGISTER_FAILED);
    }

    /**
     * 邮箱登录
     *
     * @param param
     * @return
     */
    @PostMapping("/mail_login")
    public String userLoginByMail(@RequestBody UserLoginByMailQuery param) {
        return userService.userLoginByMail(param);
    }


    /**
     * 根据邮箱查找用户
     *
     * @param mail
     * @return
     */
    @GetMapping("/get")
    public UserVO findUserByMail(@RequestParam String mail) {
        return userService.findUserByMail(mail);

    }

    /**
     * 添加好友
     *
     * @param mail
     * @return
     */
    @PostMapping("/add_friend")
    public Integer addFriend(@RequestParam String mail) {
        return userService.addFriend(mail);
    }

    /**
     * 获取新好友申请列表
     *
     * @return
     */
    @GetMapping("/get_friend_add_list")
    public List<FriendVO> getFriendAddList() {
        return userService.getFriendAddList();
    }


    /**
     * 好友申请审核
     */
    @PostMapping("/check_friend")
    public void checkFriend(@RequestParam Integer id) {
        userService.checkFriend(id);
    }


    @GetMapping("/contacts")
    public List<FriendListVO> getContacts(){
        return userService.getContacts();
    }

}
