package com.yumenghu.uaa.config;

import com.yumenghu.common.constant.SystemClientInfo;
import com.yumenghu.uaa.domain.LoginUserDTO;
import com.yumenghu.uaa.service.RedisAuthorizationCodeServices;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 22:39
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private RedisAuthorizationCodeServices redisAuthorizationCodeServices;

  @Autowired
  @Qualifier("redisTokenStore")
  private TokenStore tokenStore;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient(SystemClientInfo.CLIENT_ID)
        .secret(bCryptPasswordEncoder.encode(SystemClientInfo.CLIENT_SECRET))
        .scopes(SystemClientInfo.CLIENT_SCOPE)
        .autoApprove(true)
        .authorizedGrantTypes(SystemClientInfo.AUTHORIZATION_CODE_GRANT_TYPE,
            SystemClientInfo.IMPLICIT_GRANT_TYPE,
            SystemClientInfo.PASSWORD_GRANT_TYPE,
            SystemClientInfo.REFRESH_TOKEN_GRANT_TYPE)
        //24小时过期
        .accessTokenValiditySeconds(30);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        .tokenStore(tokenStore)
        .authenticationManager(authenticationManager)
//        .authorizationCodeServices(redisAuthorizationCodeServices)
        .tokenEnhancer((accessToken, authentication) -> {
          addLoginUserInfo(accessToken, authentication);
          return accessToken;
        });
  }


  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() throws Exception {
    return new BCryptPasswordEncoder();
  }

  /**
   * 配置前来验证token的client信息
   * @param oauthServer
   * @throws Exception
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
    /**
     * 必须设置allowFormAuthenticationForClients 否则没有办法用postman获取token
     * 也需要指定密码加密方式BCryptPasswordEncoder
     */
    oauthServer
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients()
        .passwordEncoder(bCryptPasswordEncoder);
  }

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;


  /**
   * 将当前用户信息追加到登陆后返回的json数据里<br>
   * 通过参数access_token.add-userinfo控制<br>
   * 2018.07.13
   *
   * @param accessToken
   * @param authentication
   */
  private void addLoginUserInfo(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    if (accessToken instanceof DefaultOAuth2AccessToken) {
      DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;

      Authentication userAuthentication = authentication.getUserAuthentication();
      Object principal = userAuthentication.getPrincipal();
      if (principal instanceof LoginUserDTO) {
        LoginUserDTO loginUser = (LoginUserDTO) principal;
        // 旧的附加参数
        Map<String, Object> map = new HashMap<>(
            defaultOAuth2AccessToken.getAdditionalInformation());
        // 追加当前登陆用户
        map.put("nickname", loginUser.getUser().getNickname());

        defaultOAuth2AccessToken.setAdditionalInformation(map);
      }
    }
  }

  @Bean
  protected JwtAccessTokenConverter jwtTokenEnhancer() {
    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
        new ClassPathResource("fzp-jwt.jks"), "fzp123".toCharArray());
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
    return converter;
  }


}