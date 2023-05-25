package com.wxy.imback.utils;

import com.wxy.imback.constant.BizCodeEnum;
import com.wxy.imback.constant.ErrorCode;
import com.wxy.imback.expection.BizException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author WXY
 * @Date
 * @Version 1.0
 */
@Slf4j
@Data
public class Result<T> {



    // 状态码
    private Integer code;
    // 提示信息
    private String msg;
    // 具体内容
    private T data;


    /**
     * 返回成功信息（带返回数据）
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result resultVO = new Result();
        resultVO.setData(object);
        resultVO.setCode(BizCodeEnum.SUCESS.getCode());
        resultVO.setMsg(BizCodeEnum.SUCESS.getMessage());
        return resultVO;
    }


    /**
     * 返回成功信息（带返回数据）
     * @param object
     * @return
     */
    public static Result error(Object object) {
        Result resultVO = new Result();
        resultVO.setData(object);
        resultVO.setCode(BizCodeEnum.ERROR.getCode());
        resultVO.setMsg(BizCodeEnum.ERROR.getMessage());
        return resultVO;
    }
    

    /**
     * 返回错误数据
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result resultVO = new Result();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }


    public static Result error( String msg) {
        Result resultVO = new Result();

        resultVO.setMsg(msg);
        return resultVO;
    }

    /**
     * 返回错误数据（枚举类型入参）
     * @param resultEnum
     * @return
     */
    public static Result error(BizCodeEnum resultEnum) {
        Result resultVO = new Result();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        return resultVO;
    }













//    private Integer code;
//    private String message;
//    private T data;
//
//
//    private Result() {
//        this.code = 0;
//    }
//
//
//    public Result(T data) {
//        if (data instanceof ErrorCode) {
//            ErrorCode error = (ErrorCode) data;
//            this.code = error.getCode();
//            this.message = error.getMessage();
//        } else {
//            this.code = 0;
//            this.data = data;
//        }
//
//    }
//
//    private Result(Integer code, String message) {
//        this.code = code;
//        this.message = message;
//    }
//
//    public static Result success(BizException business) {
//        Result result = new Result(business.getCode(), business.getMessage());
//        return result;
//    }
//
//    public static Result success(String message) {
//        Result result = new Result();
//        result.setMessage(message);
//        return result;
//    }
//
//    public static Result success(Object object) {
//        Result result = new Result();
//        result.setCode(BizCodeEnum.SUCESS.getCode());
//        result.setMessage(BizCodeEnum.SUCESS.getMessage());
//        result.setData(object);
//        return result;
//    }
//
//    public static Result fail(ErrorCode errorCode) {
//        return new Result(errorCode.getCode(), errorCode.getMessage());
//    }
//
//    public static Result fail(BizException business) {
//        Result result = new Result(business.getCode(), business.getMessage());
//        return result;
//    }
//
//
//    public static Result fail(String message) {
//
//        return new Result(message);
//    }
//
//    public static Result fail(Integer code, String message) {
//
//        return new Result(code, message);
//    }
//
//
//    public T getData() {
//        return this.data;
//    }
//
//    public boolean isSuccess() {
//        return this.code.equals("0");
//    }
//
//    public Integer getCode() {
//        return this.code;
//    }
//
//    public String getMessage() {
//        return this.message;
//    }
//
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }

}
