package com.wjh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 学校服务启动类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */

@SpringBootApplication
@EnableDiscoveryClient // nacos第三步启动类加注解
@EnableFeignClients
@MapperScan(basePackages = { "com.wjh.mapper" })
@EnableTransactionManagement
@Async
public class SchoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }
}
