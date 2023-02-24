package com.hzy.service;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;

/**
 * @title: LoginService
 * @Author zxwyhzy
 * @Date: 2023/2/24 21:08
 * @Version 1.0
 */
public interface LoginService {
    ResponseResult login(User user);
}
