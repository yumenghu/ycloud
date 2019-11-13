package com.yumenghu.log.mqsdk;

import com.alibaba.fastjson.JSON;
import com.yumenghu.log.domain.SysLog;
import com.yumenghu.log.service.SysLogService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 19:50
 */
@Component
@Slf4j
public class Receiver {

  @Autowired
  SysLogService sysLogService;

  public void receiveMessage(String message) {
    log.info("Received [{}]", message);
    SysLog sysLog = JSON.parseObject(message, SysLog.class);
    sysLog.setId(UUID.randomUUID().toString());
    sysLogService.saveLogger(sysLog);
  }

}