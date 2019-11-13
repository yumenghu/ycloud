package com.yumenghu.log.dao;

import com.yumenghu.log.domain.SysLog;
import com.yumenghu.log.domain.SysLogDTO;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 19:51
 */
@Mapper
public interface SysLogDao {

  @Insert(
      "insert into `sys_log` (`id`,`create_date`,`ip`, `method`,`operation`,`params`,`username`) "
          + "values(#{id}, #{createDate}, #{ip}, #{method}, #{operation}, #{params}, #{username})")
  void save(SysLog sysLog);

  /**
   * 计算总数
   * @param sysLogDTO
   * @return
   */
  int count(SysLogDTO sysLogDTO);

  /**
   * 查询日志
   * @param sysLogDTO
   * @return
   */
  List<SysLog> findData(SysLogDTO sysLogDTO);

}