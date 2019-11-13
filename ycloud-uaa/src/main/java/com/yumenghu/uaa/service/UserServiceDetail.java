package com.yumenghu.uaa.service;

import com.yumenghu.common.util.ParallelUtil;
import com.yumenghu.common.util.ParallelUtil.ParallelJob;
import com.yumenghu.uaa.dao.SysPermissionDao;
import com.yumenghu.uaa.dao.SysRoleDao;
import com.yumenghu.uaa.dao.SysUserDao;
import com.yumenghu.uaa.domain.LoginUserDTO;
import com.yumenghu.uaa.domain.SysUser;
import java.text.MessageFormat;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *@program: ycloud
 *@description:
 *@author: yu meng hu
 *@create: 2019-11-12 23:01
 */
@Service
public class UserServiceDetail implements UserDetailsService {

  @Autowired
  private SysUserDao sysUserDao;
  @Autowired
  private SysRoleDao sysRoleDao;
  @Autowired
  private SysPermissionDao sysPermissionDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    SysUser userDO = sysUserDao.findByUsername(username);
    if (userDO == null) {
      throw new UsernameNotFoundException(MessageFormat.format("{0} is empty.", username));
    }

    ParallelJob<Set<String>> getRoleCodeByUserNameJob = new ParallelJob<Set<String>>()
        .setFunction(() -> sysRoleDao.getRoleCodeByUserName(username));

    ParallelJob<Set<String>> getPermissionByUserNameJob = new ParallelJob<Set<String>>()
        .setFunction(() -> sysPermissionDao.getPermissionByUserName(username));

    ParallelUtil.execute(getRoleCodeByUserNameJob, getPermissionByUserNameJob);

    LoginUserDTO loginUserDTO = new LoginUserDTO()
        .setPermissions(getPermissionByUserNameJob.getResutl())
        .setRoles(getRoleCodeByUserNameJob.getResutl())
        .setUser(userDO);

    return loginUserDTO;
  }
}