package com.cqttx.blog.domain.repository;

import com.cqttx.blog.domain.entities.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author stc
 * @see JPA提供保存、更新、删除
 */
public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

	@Query("delete from UserRole where userId = ?1")
	public void deleteUserRoleByUserId(Integer userId);

	@Query("delete from UserRole where roleId = ?1")
	public void deleteUserRoleByRoleId(Integer roleId);
}
