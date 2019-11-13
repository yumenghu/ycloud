package com.yumenghu.uaa.dao;

import com.yumenghu.uaa.domain.SysRole;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户角色关系<br>
 * 用户和角色是多对多关系，sys_role_user是中间表
 *
 * @author 小威老师
 */
@Mapper
public interface UserRoleDao {

  /**
   * 根据用户id获取角色
   * @param userId
   * @return
   */
  @Select("select r.* from sys_role_user ru inner join sys_role r on r.id = ru.roleId where ru.userId = #{userId}")
  Set<SysRole> findRolesByUserId(Long userId);

}
