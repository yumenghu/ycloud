package com.yumenghu.uaa.domain;

import java.util.Date;
import lombok.Data;

/**
 *@program: ycloud
 *@description: 角色
 *@author: yu meng hu
 *@create: 2019-11-12 21:16
 */
@Data
public class SysRole {

	private Long id;
	private String code;
	private String name;
	private Date createTime;
	private Date updateTime;
}
