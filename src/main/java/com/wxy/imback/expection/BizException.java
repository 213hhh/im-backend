package com.wxy.imback.expection;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */

import com.wxy.imback.constant.BizCodeEnum;
import com.wxy.imback.constant.ErrorCode;
import lombok.Data;


/**
 * 全局异常处理
 */
@Data
public class BizException extends Exception {

    private Integer code;
    private String msg;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }


}