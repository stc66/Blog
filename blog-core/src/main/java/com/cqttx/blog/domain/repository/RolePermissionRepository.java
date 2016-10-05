package com.cqttx.blog.domain.repository;

import com.cqttx.blog.domain.entities.RolePermission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author stc
 * @see JPA提供保存、更新、删除
 */
public interface RolePermissionRepository extends CrudRepository<RolePermission, Integer> {

	// 根据角色ID删除角色权限
	@Query("delete from RolePermission where roleId = ?1")
	public void deleteRolePermissionByRoleId(Integer roleId);
	
	// 根据角色ID删除角色权限
	@Query("delete from RolePermission where permissionId = ?1")
	public void deleteRolePermissionByPermissionId(Integer permissionId);
}
