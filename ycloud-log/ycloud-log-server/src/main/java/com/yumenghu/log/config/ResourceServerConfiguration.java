package com.yumenghu.log.config;

import com.yumenghu.common.util.PermitAllUrl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 22:34
 */
@Configuration
@EnableResourceServer
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

  @Value("${ycloud.resource.server.config.isDev:false}")
  private Boolean isDev = false;

  @Override
  public void configure(HttpSecurity http) throws Exception {

    if (isDev) {
      http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers(PermitAllUrl.permitAllUrl())
          .permitAll()
          .antMatchers("/**").permitAll();
    } else {
      http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers(PermitAllUrl.permitAllUrl())
          .permitAll()
          .antMatchers("/**").authenticated();
    }
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    log.info("Configuring ResourceServerSecurityConfigurer ");
    resources.resourceId("foo")
        .tokenStore(tokenStore);
  }

  @Autowired
  TokenStore tokenStore;
}