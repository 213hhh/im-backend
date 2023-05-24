package com.wxy.imback.service.impl;

import com.wxy.imback.constant.BizCodeEnum;
import com.wxy.imback.constant.SendCodeEnum;
import com.wxy.imback.expection.BizException;
import com.wxy.imback.mapper.FriendApplicationRecordMapper;
import com.wxy.imback.mapper.UserMapper;
import com.wxy.imback.model.LoginUser;
import com.wxy.imback.model.entity.User;
import com.wxy.imback.model.params.userparams.UserLoginByMailQuery;
import com.wxy.imback.model.params.userparams.UserRegisterByMailParam;
import com.wxy.imback.model.vo.UserVO;
import com.wxy.imback.service.NotifyService;
import com.wxy.imback.service.UserService;
import com.wxy.imback.utils.CheckUtil;
import com.wxy.imback.utils.CommonUtil;
import com.wxy.imback.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotifyService notifyService;



    @Autowired
    private FriendApplicationRecordMapper applicationRecordMapper;

    @Autowired
    private RedissonClient redissonClient;

    private static final String SALT = "@#$";

    /**
     * token过期时间 7天
     */
    private static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    /**
     * 好友验证过期时间 7天
     */
    public static final long VALIDATION_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    /**
     * 邮箱注册
     *
     * @param param
     * @return
     */
    @Override
    public Boolean userRegisterByMail(UserRegisterByMailParam param, String request) throws BizException {
        Boolean check = false;
        CheckUtil.verifyUserName(param.getUserName());
        CheckUtil.confirmPassword(param.getPassword(), param.getConfirmPassword());
        if (CheckUtil.isEmail(param.getEmail())) {
            check = notifyService.checkCode(SendCodeEnum.USER_REGISTER, param.getEmail(), param.getCaptcha());
        }
        if (!check) {
            throw new BizException(BizCodeEnum.CODE_CAPTCHA_ERROR);
        }
        if (findUserByMail(param.getEmail()) != null) {
            throw new BizException(BizCodeEnum.ACCOUNT_REPEAT);
        }
        insertUser(param, request);

        return true;
    }

    private void insertUser(UserRegisterByMailParam param, String request) {
        User user = new User();
        String password = CommonUtil.MD5(param.getPassword() + SALT);
        user.setPassword(password);
        user.setEmail(param.getEmail());
        user.setUserName(param.getUserName());
        user.setGmtCreate(CommonUtil.getCurrentTimestamp());
        user.setGmtModified(CommonUtil.getCurrentTimestamp());
        user.setActivate(1);
        userMapper.insert(user);
    }


    /**
     * 邮箱登陆
     *
     * @param param
     * @return
     */
    @Override
    public String userLoginByMail(UserLoginByMailQuery param) {
        User user = userMapper.selectByMail(param.getEmail());
        Assert.isTrue(null != user, BizCodeEnum.ACCOUNT_UNREGISTER.getMessage());
        String password = CommonUtil.MD5(param.getPassword() + SALT);
        Assert.isTrue((Objects.equals(password, user.getPassword())), BizCodeEnum.ACCOUNT_PWD_ERROR.getMessage());
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);
        String token = JWTUtil.geneJsonWebToken(loginUser);
//        redisTemplate.opsForValue().set(token, "1", EXPIRE, TimeUnit.MILLISECONDS);
        return token;
    }

    /**
     * 根据邮箱查找用户
     *
     * @param mail
     * @return
     */
    @Override
    public UserVO findUserByMail(String mail) {
        Assert.isTrue(StringUtils.isNotBlank(mail), BizCodeEnum.EMAIL_IS_NULL.getMessage());
        User user = userMapper.selectByMail(mail);
        Assert.isTrue(user != null, BizCodeEnum.ACCOUNT_UNREGISTER.getMessage());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }



}
