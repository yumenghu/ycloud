package com.yumenghu.uaa.config;

import com.yumenghu.common.util.PermitAllUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 22:52
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Value("${ycloud.resource.server.config.isDev:false}")
  private Boolean isDev = false;

  @Override
  public void configure(HttpSecurity http) throws Exception {

    if (isDev) {
      http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers(PermitAllUrl.permitAllUrl("/user/login", "/user/registry", "/user/test"))
          .permitAll()
          .antMatchers("/**").permitAll();
    } else {
      http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers(PermitAllUrl.permitAllUrl("/user/login", "/user/registry", "/user/test"))
          .permitAll()
          .antMatchers("/**").permitAll();
    }
  }
}