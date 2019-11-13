package com.yumenghu.log.controller;

import com.yumenghu.common.domain.Page;
import com.yumenghu.log.annotation.SysLogger;
import com.yumenghu.log.domain.SysLog;
import com.yumenghu.log.domain.SysLogDTO;
import com.yumenghu.log.service.SysLogService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 21:12
 */
@RestController
@RequestMapping("/log")
public class LogController {


  @Autowired
  private SysLogService sysLogService;

  /**
   * 日志查询
   * pageParameter
   * @param sysLogDTO
   * @return
   */
  @PreAuthorize("hasAuthority('POST|/log')")
  @PostMapping
  @SysLogger("日志查询")
  public Page<SysLog> findLogs(@Valid @NotNull SysLogDTO sysLogDTO) {
    return sysLogService.findLogs(sysLogDTO);
  }


}