package com.yumenghu.uaa.dao;

import com.yumenghu.uaa.domain.SysRole;
import java.util.Set;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SysRoleDao {

	/**
	 * 根据id获取 role
	 * @param id
	 * @return
	 */
	@Select("select * from sys_role t where t.id = #{id}")
	SysRole findById(Long id);

	/**
	 * 根据roleCode获取 role
	 * @param code
	 * @return
	 */
	@Select("select * from sys_role t where t.code = #{code}")
	SysRole findByCode(String code);

	/**
	 * 根据用户获取 roleCode
	 * @param username
	 * @return
	 */
	@Select("select sys_role.`code` from sys_user, sys_role_user, sys_role "
			+ "where "
			+ "sys_user.id = sys_role_user.userId "
			+ "and  sys_role_user.roleId = sys_role.id "
			+ "and sys_user.username = #{username}")
	Set<String> getRoleCodeByUserName(String username);
}
