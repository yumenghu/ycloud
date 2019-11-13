package com.yumenghu.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 11:31
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class UaaServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(UaaServerApplication.class, args);
  }
}