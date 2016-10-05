package com.cqttx.blog.service;

import com.cqttx.blog.domain.entities.Permission;

public interface IPermissionService {

	// 添加权限
	public Boolean addPermission(Permission permission);

	// 更新权限
	public Boolean updatePermission(Permission permission);

	// 根据ID删除权限
	public void deletePermissionById(Integer id);

	// 根据ID得到权限
	public Permission getPermissionById(Integer id);

}
