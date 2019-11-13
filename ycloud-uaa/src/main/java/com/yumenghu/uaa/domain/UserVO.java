package com.yumenghu.uaa.domain;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 21:16
 */
@Data
@Accessors(chain = true)
public class UserVO {

  /**
   * 用户名
   */
  @NotEmpty
  private String username;
  /**
   * 密码
   */
  @NotEmpty
  private String password;
  /**
   * 昵称
   */
  private String nickname;

  /**
   * 电话号码
   */
  private String phone;
  /**
   * 性别
   */
  private Integer sex;

}