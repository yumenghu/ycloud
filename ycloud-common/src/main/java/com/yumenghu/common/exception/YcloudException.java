package com.yumenghu.common.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 20:01
 */
@NoArgsConstructor
public class YcloudException extends RuntimeException {

  private Integer errorCode;

  public YcloudException(ErrorCode errorCode) {
    super(errorCode.getMsg());
    this.errorCode = errorCode.getCode();
  }

  public YcloudException(ErrorCode errorCode, String msg) {
    super(msg);
    this.errorCode = errorCode.getCode();
  }


  public YcloudException(HttpStatus httpStatus, String msg) {
    super(msg);
    this.errorCode = httpStatus.value();
  }

  public Integer getErrorCode() {
    return errorCode;
  }


  public int getCode() {
    return errorCode;
  }

  public String getMsg() {
    return this.getMsg();
  }

}