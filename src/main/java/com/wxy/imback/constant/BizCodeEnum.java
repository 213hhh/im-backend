package com.wxy.imback.constant;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */

public enum BizCodeEnum implements ErrorCode {


    SUCESS(200, "sucess"),
    ERROR(-1, "error"),
    /**
     * 通用操作码
     */
    OPS_REPEAT(110001, "重复操作"),

    /**
     * 验证码
     */
    CODE_TO_ERROR(240001, "接收号码不合规"),
    CODE_LIMITED(240002, "验证码发送过快"),
    CODE_ERROR(240003, "验证码错误"),
    CODE_CAPTCHA_ERROR(240101, "图形验证码错误"),

    /**
     * 账号
     */
    ACCOUNT_REPEAT(250001, "账号已经存在"),
    ACCOUNT_UNREGISTER(250002, "账号不存在"),
    ACCOUNT_PWD_ERROR(250003, "账号或者密码错误"),
    ACCOUNT_UNLOGIN(250004, "账号未登陆"),
    EMAIL_IS_NULL(250005, "请输入正确邮箱"),
    PHONE_IS_NULL(250006, "请输入正确手机号"),
    PASSWORD_IS_NULL(250007, "请输入密码"),
    USER_NAME_IS_NULL(250008, "请输入用户名"),
    REGISTER_FAILED(250009, "注册失败"),
    TOKEN_IS_NULL(250010, "请重新登陆"),


    /**
     * 好友
     */

    CAN_NOT_ADD_MYSELF(260000, "不能添加自己"),
    ADD_REPEAT(260001, "请勿重复添加"),
    ADD_AS_FRIEND(260002, "已添加为好友"),

    /**
     * 文件相关
     */
    FILE_UPLOAD_USER_IMG_FAIL(600101, "用户头像文件上传失败");

    private String message;

    private int code;

    BizCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}