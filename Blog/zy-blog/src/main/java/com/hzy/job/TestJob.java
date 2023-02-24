package com.hzy.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @title: TestJob
 * @Author zxwyhzy
 * @Date: 2023/2/24 15:07
 * @Version 1.0
 */
@Component
public class TestJob {
    @Scheduled(cron = "0/5 * * * * ?") // 这个注解表示需要定时的方法
    public void testJob(){
        System.out.println("定时任务");
    }
}
