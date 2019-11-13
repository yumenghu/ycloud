package com.yumenghu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 14:53
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayServerApplication.class, args);
  }
}