package com.yumenghu.gateway.controller;

import com.yumenghu.common.constant.SystemClientInfo;
import com.yumenghu.common.domain.CommonResult;
import com.yumenghu.gateway.domain.AuthorizationDTO;
import com.yumenghu.gateway.domain.AuthorizationInfoVO;
import com.yumenghu.gateway.feign.Oauth2Client;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登陆、刷新token、退出
 *
 * @author 小威老师
 */
@Slf4j
@RestController
public class TokenController {

  private static final String AUTHORIZATION_PRFIX = "Bearer ";
  @Autowired
  private Oauth2Client oauth2Client;

  /**
   * 系统登陆<br>
   * 根据用户名登录<br>
   * 采用oauth2密码模式获取access_token和refresh_token
   *
   * @param username
   * @param password
   * @return
   */
  @PostMapping("/sys/login")
  public CommonResult<AuthorizationInfoVO> login(String username, String password) {
    Map<String, String> parameters = new HashMap<String, String>() {{
      put(OAuth2Utils.GRANT_TYPE, SystemClientInfo.PASSWORD_GRANT_TYPE);
      put(OAuth2Utils.CLIENT_ID, SystemClientInfo.CLIENT_ID);
      put("client_secret", SystemClientInfo.CLIENT_SECRET);
      put(OAuth2Utils.SCOPE, SystemClientInfo.CLIENT_SCOPE);
      put("username", username);
      put("password", password);
    }};
    AuthorizationDTO authorizationDTO = oauth2Client.postAccessToken(parameters);
    return CommonResult.ok(new AuthorizationInfoVO()
        .setAuthorization(AUTHORIZATION_PRFIX + authorizationDTO.getAccessToken())
        .setCityName(authorizationDTO.getCity())
        .setNickname(authorizationDTO.getNickname()));
  }

}
