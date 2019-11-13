package com.yumenghu.log.aop;

import com.alibaba.fastjson.JSON;
import com.yumenghu.common.util.DateUtil;
import com.yumenghu.common.util.HttpUtil;
import com.yumenghu.common.util.UserUtil;
import com.yumenghu.log.annotation.SysLogger;
import com.yumenghu.log.domain.SysLog;
import com.yumenghu.log.service.LoggerService;
import java.lang.reflect.Method;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 20:08
 */
@Component
@Aspect
public class SysLoggerAspect {

  @Autowired
  private LoggerService loggerService;

  @Pointcut("@annotation(com.yumenghu.log.annotation.SysLogger)")
  public void loggerPointCut() {

  }

  @Before("loggerPointCut()")
  public void saveSysLog(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();

    SysLog sysLog = new SysLog();
    SysLogger sysLogger = method.getAnnotation(SysLogger.class);
    if (sysLogger != null) {
      //注解上的描述
      sysLog.setOperation(sysLogger.value());
    }
    //请求的方法名
    String className = joinPoint.getTarget().getClass().getName();
    String methodName = signature.getName();
    sysLog.setMethod(className + "." + methodName + "()");
    //请求的参数
    Object[] args = joinPoint.getArgs();
    String params = "";
    for (Object o : args) {
      params += JSON.toJSONString(o);
    }
    if (!StringUtils.isEmpty(params)) {
      sysLog.setParams(params);
    }
    //设置IP地址
    sysLog.setIp(HttpUtil.getIpAddress());
    //用户名
    String username = UserUtil.getCurrentPrinciple();
    if (!StringUtils.isEmpty(username)) {
      sysLog.setUsername(username);
    }
    sysLog.setCreateDate(DateUtil.getNowDate());
    //保存系统日志
    loggerService.log(sysLog);
  }
}