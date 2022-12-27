package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;
import com.hzy.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return blogLoginService.login(user);
    }
}

