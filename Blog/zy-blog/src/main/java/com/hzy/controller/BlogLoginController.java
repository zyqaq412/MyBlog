package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;
import com.hzy.enums.AppHttpCodeEnum;
import com.hzy.exception.SystemException;
import com.hzy.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: BlogLoginController
 * @Author zxwyhzy
 * @Date: 2022/12/27 20:27
 * @Version 1.0
 */
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        // StringUtils.hasText(字符串)
        // 如果字符串里面的值为null， ""， "  "，那么返回值为false；否则为true
        if(!StringUtils.hasText(user.getUserName())){
            // 提示 必须要用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
}

