package com.yumenghu.log.service;

import com.alibaba.fastjson.JSON;
import com.yumenghu.log.config.RabbitConfig;
import com.yumenghu.log.domain.SysLog;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 20:54
 */
@Service
public class LoggerService {
  @Autowired
  private AmqpTemplate rabbitTemplate;

  public void log(SysLog sysLog){
    rabbitTemplate.convertAndSend(RabbitConfig.queueName, JSON.toJSONString(sysLog));
  }
}