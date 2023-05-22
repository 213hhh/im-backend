package com.wxy.imback.service;

import com.wxy.imback.constant.SendCodeEnum;
import com.wxy.imback.utils.Result;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
public interface NotifyService {

    /**
     * 发送验证码
     *
     * @param sendCodeType
     * @param to
     * @return
     */
    Result sendCode(SendCodeEnum sendCodeType, String to);


    /**
     * 校验验证码
     * @param code
     * @return
     */
    boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code);

}
