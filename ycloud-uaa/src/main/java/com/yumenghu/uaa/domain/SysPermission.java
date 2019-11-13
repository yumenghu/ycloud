package com.yumenghu.uaa.domain;

import java.util.Date;
import lombok.Data;

/**
 * 权限标识
 * 
 */
@Data
public class SysPermission{

	private Long id;
	private String permission;
	private String name;
	private Date createTime;
	private Date updateTime;
}
