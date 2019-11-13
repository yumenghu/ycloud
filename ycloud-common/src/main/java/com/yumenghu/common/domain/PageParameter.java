package com.yumenghu.common.domain;

import lombok.Data;

/**
 *@program: ycloud
 *@description: 分页参数
 *@author: yu meng hu
 *@create: 2019-11-13 21:26
 */
@Data
public class PageParameter {

  /**
   * 分页页码
   */
  private Integer pageIndex;

  /**
   * 内容条数
   */
  private Integer pageSize;

  public void repair() {
    if (this.pageIndex == null || this.pageIndex < 0) {
      this.pageIndex = 1;
    }
    if (this.pageSize == null || this.pageSize < 0) {
      this.pageSize = 10;
    }
  }

}