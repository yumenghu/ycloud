package com.yumenghu.gateway.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *@program: cem-service
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-20 13:06
 */
@Data
@Accessors(chain = true)
public class AuthorizationInfoVO {

  @JsonProperty( "Authorization")
  private String authorization;

  private String cityName;

  private String nickname;
}