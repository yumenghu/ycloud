package com.yumenghu.uaa.dao;

import com.yumenghu.uaa.domain.SysPermission;
import java.util.Set;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface SysPermissionDao {

	@Insert("insert into sys_permission(permission, name, createTime, updateTime) values(#{permission}, #{name}, #{createTime}, #{createTime})")
	int save(SysPermission sysPermission);

	@Update("update sys_permission t set t.name = #{name}, t.permission = #{permission}, t.updateTime = #{updateTime} where t.id = #{id}")
	int update(SysPermission sysPermission);

	@Delete("delete from sys_permission where id = #{id}")
	int delete(Long id);

	@Select("select * from sys_permission t where t.id = #{id}")
	SysPermission findById(Long id);

	@Select("select * from sys_permission t where t.permission = #{permission}")
	SysPermission findByPermission(String permission);

	@Select("select sys_permission.permission from sys_user, sys_role_user, sys_permission ,sys_role_permission "
			+ "where sys_user.id = sys_role_user.userId and  sys_role_user.roleId = sys_role_permission.roleId "
			+ "and sys_role_permission.permissionId = sys_permission.id and sys_user.username = #{username}")
	Set<String> getPermissionByUserName(String username);


}
