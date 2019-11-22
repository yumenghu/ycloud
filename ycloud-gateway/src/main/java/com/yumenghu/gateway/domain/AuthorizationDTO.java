package com.yumenghu.gateway.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 *@program: cem-service
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-20 12:59
 */
@Data
public class AuthorizationDTO {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("token_type")
  private String tokenType;
  @JsonProperty("refresh_token")
  private String refreshToken;
  @JsonProperty("expires_in")
  private String expiresIn;
  private String scope;
  private String nickname;
  private String city;
  private String jti;

}