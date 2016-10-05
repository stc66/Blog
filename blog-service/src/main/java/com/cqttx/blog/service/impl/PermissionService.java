package com.cqttx.blog.service.impl;

import com.cqttx.blog.domain.entities.Permission;
import com.cqttx.blog.domain.mapper.PermissionMapper;
import com.cqttx.blog.domain.repository.PermissionRepository;
import com.cqttx.blog.domain.repository.RolePermissionRepository;
import com.cqttx.blog.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("permissionService")
@Transactional(readOnly = true)
public class PermissionService implements IPermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	// 添加权限
	@Override
	@Transactional
	public Boolean addPermission(Permission permission) {
		Boolean flag = false;
		Permission result = null;

		permission.setCreateTime(new Date());
		result = permissionRepository.save(permission);

		if (result != null) {
			flag = true;
		}

		return flag;
	}

	// 更新权限
	@Override
	@Transactional
	public Boolean updatePermission(Permission permission) {
		Boolean flag = false;
		Permission result = null;

		result = permissionRepository.save(permission);

		if (result != null) {
			flag = true;
		}

		return flag;
	}

	// 根据ID删除权限
	@Override
	@Transactional
	public void deletePermissionById(Integer id) {
		permissionRepository.delete(id);
		// 删除角色权限表
		rolePermissionRepository.deleteRolePermissionByPermissionId(id);
	}

	// 根据ID得到权限
	@Override
	public Permission getPermissionById(Integer id) {
		return permissionMapper.findPermissionById(id);
	}
}
