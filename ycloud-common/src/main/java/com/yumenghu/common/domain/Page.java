package com.yumenghu.common.domain;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 21:31
 */
@Data
@Accessors(chain = true)
public class Page<T> {

  /**
   * 总条数
   */
  private Integer total;
  /**
   * 当前页码
   */
  private Integer pageIndex;

  /**
   * 页面内容条数
   */
  private Integer pageSize;

  /**
   * 数据
   */
  private List<T> data;

}