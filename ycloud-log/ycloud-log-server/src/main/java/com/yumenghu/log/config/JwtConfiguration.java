package com.yumenghu.log.config;

import com.yumenghu.common.exception.ErrorCode;
import com.yumenghu.common.exception.YcloudException;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 19:40
 */
@Configuration
@Slf4j
public class JwtConfiguration {

  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Bean
  @Qualifier("tokenStore")
  public TokenStore tokenStore() {

    log.info("Created JwtTokenStore");
    return new JwtTokenStore(jwtAccessTokenConverter);
  }

  @Bean
  protected JwtAccessTokenConverter jwtTokenEnhancer() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    Resource resource = new ClassPathResource("public.cert");
    String publicKey;
    try (InputStream in = resource.getInputStream()) {
      publicKey = new String(FileCopyUtils.copyToByteArray(in));
    } catch (IOException e) {
      throw new YcloudException(ErrorCode.FAIL, e.getMessage());
    }
    converter.setVerifierKey(publicKey);
    return converter;
  }
}