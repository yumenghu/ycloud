package com.yumenghu.uaa.config;

import java.util.UUID;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-21 11:13
 */
public class RandomAuthenticationKeyGenerator implements AuthenticationKeyGenerator {

  @Override
  public String extractKey(OAuth2Authentication authentication) {
    return UUID.randomUUID().toString();
  }
}