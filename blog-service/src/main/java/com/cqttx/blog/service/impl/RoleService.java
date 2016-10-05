package com.cqttx.blog.service.impl;

import com.cqttx.blog.domain.entities.Role;
import com.cqttx.blog.domain.entities.RolePermission;
import com.cqttx.blog.domain.mapper.RoleMapper;
import com.cqttx.blog.domain.mapper.RolePermissionMapper;
import com.cqttx.blog.domain.repository.RolePermissionRepository;
import com.cqttx.blog.domain.repository.RoleRepository;
import com.cqttx.blog.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("roleService")
@Transactional(readOnly = true)
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	// 添加角色
	@Override
	@Transactional
	public Boolean addRole(Role role, int[] permissionIds) {
		Boolean flag = false;
		Role result = null;

		role.setCreateTime(new Date());
		result = roleRepository.save(role);
		if (result != null) {
			flag = true;
			if (permissionIds != null && permissionIds.length > 0) {
				RolePermission rp = null;
				for (int pid : permissionIds) {
					rp = new RolePermission();
					rp.setRoleId(result.getId());
					rp.setPermissionId(pid);
					rolePermissionRepository.save(rp);
				}
			}
		}
		return flag;
	}

	// 更新角色
	@Override
	@Transactional
	public Boolean updateRole(Role role, int[] permissionIds) {
		Boolean flag = false;
		Role result = null;

		result = roleRepository.save(role);
		if (result != null) {
			flag = true;
			if (permissionIds != null && permissionIds.length > 0) {
				// 先删除原来的中间表再保存
				List<RolePermission> list = rolePermissionMapper.findListRolePermissionByRoleId(result.getId());
				for (RolePermission rp : list) {
					rolePermissionRepository.delete(rp.getId());
				}
				// 重新保存
				RolePermission rp = null;
				for (int pid : permissionIds) {
					rp = new RolePermission();
					rp.setRoleId(result.getId());
					rp.setPermissionId(pid);
					rolePermissionRepository.save(rp);
				}
			}
		}

		return flag;
	}

	// 根据ID删除角色
	@Override
	@Transactional
	public void deleteRoleById(Integer id) {
		roleRepository.delete(id);
		// 删除角色权限表
		rolePermissionRepository.deleteRolePermissionByRoleId(id);
	}

	// 根据ID得到角色
	@Override
	public Role getRoleById(Integer id) {
		return roleMapper.findRoleById(id);
	}

}
