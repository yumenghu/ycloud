package com.yumenghu.uaa.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 21:54
 */

@Data
@Accessors(chain = true)
public class LoginDTO {
  private SysUser user;
  private String token;
}