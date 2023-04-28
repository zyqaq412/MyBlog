package com.hzy.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @title: ToEmail
 * @Author zxwyhzy
 * @Date: 2023/4/28 14:47
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToEmail implements Serializable {
    /**
     * 邮件接收方
     */
    private String to;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
}
