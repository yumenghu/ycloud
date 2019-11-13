package com.yumenghu.uaa.dao;

import com.yumenghu.uaa.domain.SysUser;
import java.util.Set;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 21:47
 */
@Mapper
public interface SysUserDao {

  @Select("select * from sys_user t where t.username = #{username}")
  SysUser findByUsername(String username);

  @Insert(
      "insert into sys_user(id, username, password, nickname, headImgUrl, phone, sex, enabled, type, createTime, updateTime) "
          + "values(#{id}, #{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{sex}, #{enabled}, #{type}, #{createTime}, #{updateTime})")
  int save(SysUser user);

}