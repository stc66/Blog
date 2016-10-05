package com.cqttx.blog.domain.repository;

import com.cqttx.blog.domain.entities.Permission;
import org.springframework.data.repository.CrudRepository;

/**
 * @author stc
 * @see JPA提供保存、更新、删除
 */
public interface PermissionRepository extends CrudRepository<Permission, Integer> {

}
