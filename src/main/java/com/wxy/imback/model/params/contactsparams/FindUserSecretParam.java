package com.wxy.imback.model.params.contactsparams;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/24 9:13
 * @Version 1.0
 */
@Data
public class FindUserSecretParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邮箱
     */
    private String userIdentify;
}
