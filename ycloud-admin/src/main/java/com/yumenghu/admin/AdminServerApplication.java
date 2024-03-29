package com.yumenghu.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 14:54
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdminServerApplication.class, args);
  }
}