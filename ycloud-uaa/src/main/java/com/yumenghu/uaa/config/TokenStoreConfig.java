package com.yumenghu.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-22 16:09
 */
@Configuration
public class TokenStoreConfig {

    private final RedisConnectionFactory redisConnectionFactory;
    private final ClientDetailsService clientDetailsService;

    @Autowired
    public TokenStoreConfig(RedisConnectionFactory redisConnectionFactory, ClientDetailsService clientDetailsService) {
      this.redisConnectionFactory = redisConnectionFactory;
      this.clientDetailsService = clientDetailsService;
    }

    /**
     * havingValue 当name的值与此值相同时加载配置
     * @return
     */
    @Bean(name = "redisTokenStore")
    public TokenStore tokenStore() {
      MyRedisTokenStore tokenStore = new MyRedisTokenStore(redisConnectionFactory, clientDetailsService);
      //解决同一username每次登陆access_token都相同的问题
      tokenStore.setAuthenticationKeyGenerator(new RandomAuthenticationKeyGenerator());
      tokenStore.setPrefix("user-token:");
      return tokenStore;
    }

}