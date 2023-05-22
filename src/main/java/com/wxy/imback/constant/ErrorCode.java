package com.wxy.imback.constant;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/19 8:50
 * @Version 1.0
 */
public interface ErrorCode extends Serializable {
    String getMessage();

    int getCode();

}
