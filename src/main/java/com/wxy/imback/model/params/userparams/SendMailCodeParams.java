package com.wxy.imback.model.params.userparams;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * @Author WXY
 * @Date 2023/5/21 15:28
 * @Version 1.0
 */
@Data
public class SendMailCodeParams implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 收件人
     */
    String to;

    /**
     * 验证码
     */
    String captcha;

}
