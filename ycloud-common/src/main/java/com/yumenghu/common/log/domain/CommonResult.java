package com.yumenghu.common.log.domain;

import com.yumenghu.common.constant.GlobalConstants;
import com.yumenghu.common.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.Assert;

/**
 * @program: CEM_service
 * @description: 通用result
 * @author: yu meng hu
 * @create: 2019-08-15 16:41
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CommonResult<T> {

  private Integer resultCode;

  private T data;

  private String resultMessage;

  private Long currentTime;


  public static <T> CommonResult<T> ok(T data) {
    Assert.notNull(data, "Data must not be null");
    return new CommonResult<T>()
        .setData(data)
        .setResultMessage(GlobalConstants.RESULT_SUCCESS_MESSAGE)
        .setCurrentTime(DateUtil.getTimestampNow())
        .setResultCode(GlobalConstants.SUCCESS_CODE);
  }


  public static CommonResult ok() {
    return new CommonResult()
        .setResultMessage(GlobalConstants.RESULT_SUCCESS_MESSAGE)
        .setCurrentTime(DateUtil.getTimestampNow())
        .setResultCode(GlobalConstants.SUCCESS_CODE);
  }


  public static CommonResult error(Integer resultCode,String resultMessage) {
    return new CommonResult()
        .setResultCode(resultCode)
        .setCurrentTime(DateUtil.getTimestampNow())
        .setResultMessage(resultMessage);
  }

  public static CommonResult ok(String resultMessage) {
    return new CommonResult()
        .setResultCode(GlobalConstants.ERROR_CODE)
        .setCurrentTime(DateUtil.getTimestampNow())
        .setResultMessage(resultMessage);
  }
}