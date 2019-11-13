package com.yumenghu.common.exception.handler;

import com.yumenghu.common.exception.YcloudException;
import com.yumenghu.common.domain.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 *@program: ycloud
 *@description: 全局异常统一处理
 *@author: yu meng hu
 *@create: 2019-11-12 21:28
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 处理所有不可知的异常
   */
  @ExceptionHandler(Throwable.class)
  public ResponseEntity handleException(Throwable e) {
    // 打印堆栈信息
    log.error(e.getMessage());
    CommonResult apiError = CommonResult.error(BAD_REQUEST.value(), e.getMessage());
    return buildResponseEntity(apiError);
  }

  /**
   * 处理 接口无权访问异常AccessDeniedException
   */
  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
    // 打印堆栈信息
    log.error(e.getMessage());
    CommonResult apiError = CommonResult.error(FORBIDDEN.value(), e.getMessage());
    return buildResponseEntity(apiError);
  }

  /**
   * 处理自定义异常
   */
  @ExceptionHandler(value = YcloudException.class)
  public ResponseEntity<CommonResult> badRequestException(YcloudException e) {
    // 打印堆栈信息
    log.error(e.getMessage());
    int resultCode;
    if (HttpStatus.resolve(e.getCode()) == null) {
      resultCode = BAD_REQUEST.value();
    } else {
      resultCode = e.getCode();
    }
    CommonResult commonResult = CommonResult.error(resultCode, e.getMessage());
    return buildResponseEntity(commonResult);
  }


  /**
   * 处理所有接口数据验证异常
   *
   * @returns
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<CommonResult> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    // 打印堆栈信息
    log.error(e.getMessage());
    String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
    StringBuffer msg = new StringBuffer(str[1] + ":");
    msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    CommonResult commonResult = CommonResult.error(BAD_REQUEST.value(), msg.toString());
    return buildResponseEntity(commonResult);
  }

  /**
   * 统一返回
   */
  private ResponseEntity<CommonResult> buildResponseEntity( CommonResult commonResult) {
    return new ResponseEntity(commonResult, HttpStatus.valueOf(commonResult.getResultCode()));
  }
}