package com.yumenghu.uaa.fegin;

import com.yumenghu.common.config.FeginConfig;
import com.yumenghu.common.domain.Page;
import com.yumenghu.log.domain.SysLog;
import com.yumenghu.uaa.fegin.domain.SysLogDTO;
import com.yumenghu.uaa.fegin.impl.LogFeginClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *@program: ycloud
 *@description: fegin
 *@author: yu meng hu
 *@create: 2019-11-14 15:43
 */
@FeignClient(value = "ycloud-log", fallback = LogFeginClientImpl.class, configuration = FeginConfig.class)
public interface LogFeginClient {

  /**
   * 调用log服务获取日志测试
   * @param sysLogDTO
   * @return
   */
  @PostMapping("/log")
  Page<SysLog> findLogs(@RequestParam SysLogDTO sysLogDTO);

}