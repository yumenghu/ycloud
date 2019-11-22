package com.yumenghu.uaa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
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
public class SysUser implements Serializable {

  private static final long serialVersionUID = -8432944720359404438L;
  /**
   * 主键ID
   */
  @JsonIgnore
  private Long id;
  /**
   * 用户名
   */
  private String username;
  /**
   * 密码
   */
  private String password;
  /**
   * 昵称
   */
  private String nickname;
  /**
   * 图片地址
   */
  private String headImgUrl;
  /**
   * 电话号码
   */
  private String phone;
  /**
   * 性别
   */
  private Integer sex;
  /**
   * 状态
   */
  private Boolean enabled;
  /**
   * 暂时无用
   */
  private String type;
  /**
   * 创建时间
   */
  private Date createTime;
  /**
   * 修改时间
   */
  private Date updateTime;


}