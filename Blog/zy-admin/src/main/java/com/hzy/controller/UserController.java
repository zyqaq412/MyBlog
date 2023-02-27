package com.hzy.controller;

import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;
import com.hzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: UserController
 * @Author zxwyhzy
 * @Date: 2023/2/27 16:35
 * @Version 1.0
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public ResponseResult list(User user, Integer pageNum, Integer pageSize) {
        return userService.selectUserPage(user,pageNum,pageSize);
    }
}
