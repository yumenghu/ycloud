package com.yumenghu.common.log.domain;

import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *@program: ycloud
 *@description: SysLog
 *@author: yu meng hu
 *@create: 2019-11-12 20:37
 */
@Data
@Accessors(chain = true)
public class SysLog {

  /**
   * 事务ID
   */
  private Long id;
  /**
   * 用户名
   */
  private String username;
  /**
   * 用户操作
   */
  private String operation;
  /**
   * 请求方法
   */
  private String method;
  /**
   * 请求参数
   */
  private String params;
  /**
   * IP地址
   */
  private String ip;
  /**
   * 创建时间
   */
  private Date createDate;
}