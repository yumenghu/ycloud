package com.yumenghu.common.service;

import com.yumenghu.common.util.SecurityUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-22 10:20
 */
public class ElPermissionConfig {

  public Boolean check(String ...permissions){
    // 如果是匿名访问的，就放行
    String anonymous = "anonymous";
    if(Arrays.asList(permissions).contains(anonymous)){
      return true;
    }
    // 获取当前用户的所有权限
    List<String> elPermissions = SecurityUtil.getAuthentication().getAuthorities().stream().map(
        GrantedAuthority::getAuthority).collect(Collectors.toList());
    // 判断当前用户的所有权限是否包含接口上定义的权限
    return elPermissions.contains("ROLE_SUPER_ADMIN") || Arrays.stream(permissions).anyMatch(elPermissions::contains);
  }
}