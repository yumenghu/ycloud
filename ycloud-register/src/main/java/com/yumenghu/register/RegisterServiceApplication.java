package com.yumenghu.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *@program: ycloud
 *@description: 注册中心启动类 访问地址 http://localhost:8761
 *
 *@author: yu meng hu
 *@create: 2019-11-07 18:48
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(RegisterServiceApplication.class, args);
  }

}