package com.yumenghu.log.service;

import com.yumenghu.common.domain.Page;
import com.yumenghu.log.dao.SysLogDao;
import com.yumenghu.log.domain.SysLog;
import com.yumenghu.log.domain.SysLogDTO;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 19:51
 */
@Service
public class SysLogService {

  @Autowired
  SysLogDao sysLogDao;

  public void saveLogger(SysLog sysLog) {
    sysLogDao.save(sysLog);
  }

  public Page<SysLog> findLogs(SysLogDTO sysLogDTO) {

    int total = sysLogDao.count(sysLogDTO);
    List<SysLog> list = Collections.emptyList();
    if (total > 0) {
      sysLogDTO.repair();
      list = sysLogDao.findData(sysLogDTO);
    }
    return new Page<SysLog>()
        .setData(list)
        .setPageIndex(sysLogDTO.getPageIndex())
        .setPageSize(sysLogDTO.getPageIndex())
        .setTotal(total);
  }
}