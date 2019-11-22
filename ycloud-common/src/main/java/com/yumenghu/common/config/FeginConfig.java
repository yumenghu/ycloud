package com.yumenghu.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *@program: ycloud
 *@description: 解决fegin调用过程Token传递问题
 *@author: yu meng hu
 *@create: 2019-11-14 14:28
 */
public class FeginConfig implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    //添加token
    requestTemplate.header("Authorization", request.getHeader("Authorization"));
  }
}