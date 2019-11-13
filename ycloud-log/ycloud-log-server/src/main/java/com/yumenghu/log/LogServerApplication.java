package com.yumenghu.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 14:47
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LogServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(LogServerApplication.class, args);
  }
}