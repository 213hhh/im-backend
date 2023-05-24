package com.wxy.imback.model.params.userparams;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/19 9:47
 * @Version 1.0
 */
@Data
public class UserLoginByMailQuery implements Serializable {


    private static final long serialVersionUID = 1L;



    /**
     * e-mail
     */
    private String email;

    /**
     * 密码
     */
    private String password;

}
