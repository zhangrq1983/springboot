package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心
 * 1、配置Eureka信息
 * 2、@EnableEurekaServer
 */
@EnableEurekaServer
@SpringBootApplication
public class Springboot15SpringcloudEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot15SpringcloudEurekaServerApplication.class, args);
    }

}
