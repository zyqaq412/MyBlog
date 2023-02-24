package com.hzy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @title: BlogAdminApplication
 * @Author zxwyhzy
 * @Date: 2023/2/24 20:34
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.hzy.mapper")
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
