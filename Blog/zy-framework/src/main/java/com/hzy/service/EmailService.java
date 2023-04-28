package com.hzy.service;

import com.hzy.domain.ResponseResult;

import com.hzy.domain.dto.ToEmail;
import org.springframework.stereotype.Service;

/**
 * @title: EmailService
 * @Author zxwyhzy
 * @Date: 2023/4/28 14:49
 * @Version 1.0
 */
@Service
public interface EmailService {
    /**
     *  发送验证码
     * @param toEmail 邮箱数据传输类
     * @return
     */
    ResponseResult commonEmail(ToEmail toEmail);

    ResponseResult commentRemind(ToEmail toEmail);
}
