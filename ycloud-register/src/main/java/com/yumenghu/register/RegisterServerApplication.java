package com.yumenghu.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 10:20
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(RegisterServerApplication.class, args);
  }
}