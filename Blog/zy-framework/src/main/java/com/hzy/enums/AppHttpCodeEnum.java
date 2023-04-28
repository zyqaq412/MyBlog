package com.hzy.enums;

/**
 * @title: AppHttpCodeEnum
 * @Author zxwyhzy
 * @Date: 2022/12/16 1:43
 * @Version 1.0
 */
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONTENT_NOT_NULL(506,"评论不能为空"),
    FILE_TYPE_ERROR(507,"文件类型错误，请上传png格式图片" ),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    NICKNAME_NOT_NULL(509, "昵称不能为空"),
    PASSWORD_NOT_NULL(510, "密码不能为空"),
    EMAIL_NOT_NULL(511, "邮箱不能为空"),
    NICKNAME_EXIST(512, "昵称已存在"),
    UPDATE_MENU_NULL(513,"内容不能为空"),
    UPDATE_MENU_CF(514,"父菜单不能为自己"),
    ADD_MENU_FAIL(515, "当前菜单已存在"),
    CODE_NOT_NULL(508, "验证码不能为空");



    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
