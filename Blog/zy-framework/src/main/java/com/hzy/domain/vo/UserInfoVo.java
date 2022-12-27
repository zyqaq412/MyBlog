package com.hzy.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @title: UserInfoVo
 * @Author zxwyhzy
 * @Date: 2022/12/27 20:36
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    private String sex;

    private String email;


}

