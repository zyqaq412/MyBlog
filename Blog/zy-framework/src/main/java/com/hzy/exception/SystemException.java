package com.hzy.exception;

import com.hzy.enums.AppHttpCodeEnum;

/**
 * @title: SystemException
 * @Author zxwyhzy
 * @Date: 2022/12/27 22:30
 * @Version 1.0
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
