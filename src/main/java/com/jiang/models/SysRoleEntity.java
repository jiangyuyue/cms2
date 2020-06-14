/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.jiang.models;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author 蒋雨岳
 */
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色ID
	 */
	@TableId
	private Long roleId;

	/**
	 * 角色名称
	 */
	@NotBlank(message="角色名称不能为空")
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建者ID
	 */
	private Long createUserId;

	@TableField(exist = false)
	private User createUser;

	@TableField(exist=false)
	private List<Long> menuIdList;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	/*
	* 公角色
	* */
	private Long subRole;
	/*
	* 角色类型
	* */
	private String type;
	
}
