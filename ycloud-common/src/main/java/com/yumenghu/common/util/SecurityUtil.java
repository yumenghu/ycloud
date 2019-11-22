package com.yumenghu.common.util;

import com.yumenghu.common.exception.YcloudException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-22 10:21
 */
public class SecurityUtil {

  public static Authentication getAuthentication() {
    Authentication authentication;
    try {
      authentication = SecurityContextHolder.getContext().getAuthentication();
    } catch (Exception e) {
      throw new YcloudException(HttpStatus.UNAUTHORIZED, "登录状态过期");
    }
    return authentication;
  }

  /**
   * 获取系统用户名称
   * @return 系统用户名称
   */
  public static String getUsername() {
    return (String) getAuthentication().getPrincipal();
  }
}