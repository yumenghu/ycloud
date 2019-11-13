package com.yumenghu.uaa.service;

import com.yumenghu.common.util.DateUtil;
import com.yumenghu.uaa.dao.SysUserDao;
import com.yumenghu.uaa.domain.SysUser;
import com.yumenghu.uaa.domain.UserVO;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 21:52
 */
@Service
public class UserService {

  @Autowired
  SysUserDao userDao;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public void createUser(UserVO user) {
    SysUser userDO = new SysUser();
    BeanUtils.copyProperties(user, userDO);
    userDO.setCreateTime(DateUtil.getNowDate())
        .setPassword(bCryptPasswordEncoder.encode(user.getPassword()))
        .setEnabled(true);
    userDao.save(userDO);
  }

}