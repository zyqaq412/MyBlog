package com.hzy;

import org.mapstruct.MapperConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @title: ZyAppLicationo
 * @Author zxwyhzy
 * @Date: 2022/12/15 16:07
 * @Version 1.0
 */
/*
 * @Date: 2022/12/15 16:08
 *  创建启动类
 */
@SpringBootApplication
@MapperScan("com.hzy.mapper")
@EnableScheduling // 开启定时任务
@EnableSwagger2
public class ZyAppLicationo {
    public static void main(String[] args) {
        SpringApplication.run(ZyAppLicationo.class,args);
    }
}
