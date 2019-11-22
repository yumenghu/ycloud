package com.yumenghu.uaa.fegin.domain;

import com.yumenghu.common.domain.PageParameter;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 21:41
 */
@Data
@Accessors(chain = true)
public class SysLogDTO extends PageParameter {

  /**
   * 用户名
   */
  private String username;
  /**
   * 用户操作
   */
  private String operation;
  /**
   * 请求参数
   */
  private String params;
  /**
   * 开始时间
   */
  private Date startDate;

  /**
   * 结束时间
   */
  private Date endDate;


}