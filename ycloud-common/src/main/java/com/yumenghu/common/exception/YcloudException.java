package com.yumenghu.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 20:01
 */
@NoArgsConstructor
public class YcloudException extends RuntimeException {

  private ErrorCode errorCode;

  public YcloudException(ErrorCode errorCode) {
    super(errorCode.getMsg());
    this.errorCode = errorCode;
  }

  public YcloudException(ErrorCode errorCode, String msg) {
    super(msg);
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }


  public int getCode() {
    return errorCode.getCode();
  }

  public String getMsg() {
    return errorCode.getMsg();
  }

}