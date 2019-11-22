package com.yumenghu.uaa.controller;

import com.yumenghu.common.domain.Page;
import com.yumenghu.log.annotation.SysLogger;
import com.yumenghu.common.domain.CommonResult;
import com.yumenghu.log.config.RabbitConfig;
import com.yumenghu.log.domain.SysLog;
import com.yumenghu.uaa.domain.UserVO;
import com.yumenghu.uaa.fegin.LogFeginClient;
import com.yumenghu.uaa.fegin.domain.SysLogDTO;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yumenghu.uaa.service.UserService;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-13 00:05
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {


  @Autowired
  UserService userService;

  @ApiOperation(value = "新增用户", notes = "username和password为必选项")
  @PostMapping("/registry")
  @SysLogger("registry")
  public CommonResult createUser(@RequestBody @Valid @NotNull UserVO user) {
    userService.createUser(user);
    return CommonResult.ok();
  }

  @GetMapping("/user-me")
  public Authentication principal() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    log.debug("user-me:{}", authentication.getName());
    return authentication;
  }


  @Autowired
  private AmqpTemplate rabbitTemplate;

  @GetMapping("/test")
  public void test() {
    rabbitTemplate.convertAndSend(RabbitConfig.queueName, "Hello from RabbitMQ!");
  }

  @Autowired
  private LogFeginClient logFeginClient;

  @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','POST|/user/test-authorization')")
  @GetMapping("/test-authorization")
  public CommonResult<Page<SysLog>> testAuthorization() {
    return CommonResult.ok(logFeginClient.findLogs(new SysLogDTO().setUsername("")));
  }
}