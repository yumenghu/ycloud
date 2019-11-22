package com.yumenghu.uaa.fegin.impl;

import com.yumenghu.common.domain.Page;
import com.yumenghu.log.domain.SysLog;
import com.yumenghu.uaa.fegin.LogFeginClient;
import com.yumenghu.uaa.fegin.domain.SysLogDTO;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-14 15:57
 */
public class LogFeginClientImpl implements LogFeginClient {

  @Override
  public Page<SysLog> findLogs(SysLogDTO sysLogDTO) {
    return null;
  }
}