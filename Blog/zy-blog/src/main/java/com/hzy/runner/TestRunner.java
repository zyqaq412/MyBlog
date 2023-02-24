package com.hzy.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @title: TestRunner
 * @Author zxwyhzy
 * @Date: 2023/2/24 15:01
 * @Version 1.0
 */
//@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序初始化");
    }
}
