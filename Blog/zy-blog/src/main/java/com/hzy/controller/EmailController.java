package com.hzy.controller;


import com.hzy.domain.ResponseResult;
import com.hzy.domain.dto.ToEmail;
import com.hzy.service.EmailService;
import com.hzy.service.impl.EmailServiceImpl;
import com.hzy.utils.RedisCache;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title: EmailController
 * @Author zxwyhzy
 * @Date: 2023/4/28 14:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    EmailService emailService = new EmailServiceImpl();
    @PostMapping
    public ResponseResult SendMail(@RequestBody ToEmail toEmail){

        return emailService.commonEmail(toEmail);
    }
}
