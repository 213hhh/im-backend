package com.wxy.imback.constant;

import lombok.Data;

/**
 * @Author WXY
 * @Date 2023/5/24 19:17
 * @Version 1.0
 */
@Data
public class AuditStatus{

    /**
     * 未审核
     */
    public static final Integer NOT_AUDIT = 0;

    /**
     * 通过
     */
   public static final Integer PASS =1;

    /**
     * 不通过
     */
   public static final Integer NOT_PASS=2;



}
