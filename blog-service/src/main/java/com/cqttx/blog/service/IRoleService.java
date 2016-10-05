package com.cqttx.blog.service;

import com.cqttx.blog.domain.entities.Role;

public interface IRoleService {

	// 添加角色
	public Boolean addRole(Role role, int[] permissionIds);

	// 更新角色
	public Boolean updateRole(Role role, int[] permissionIds);

	// 根据ID删除角色
	public void deleteRoleById(Integer id);

	// 根据ID得到角色
	public Role getRoleById(Integer id);

}
