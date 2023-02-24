package com.hzy.controller;

import com.hzy.annotion.SystemLog;
import com.hzy.domain.ResponseResult;
import com.hzy.domain.entity.User;
import com.hzy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title: UserController
 * @Author zxwyhzy
 * @Date: 2023/2/23 11:29
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取登录用户数据
     * @return
     */
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/userInfo")
    @SystemLog(BusinessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @PostMapping("/register")
    @SystemLog(BusinessName = "注册用户")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user);
    }
}
