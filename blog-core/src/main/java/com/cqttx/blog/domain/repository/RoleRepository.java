package com.cqttx.blog.domain.repository;

import com.cqttx.blog.domain.entities.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * @author stc
 * @see JPA提供保存、更新、删除
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
