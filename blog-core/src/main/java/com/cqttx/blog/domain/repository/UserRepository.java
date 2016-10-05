package com.cqttx.blog.domain.repository;

import com.cqttx.blog.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author stc
 * @see JPA提供保存、更新、删除
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("from User u where u.userName = ?1")
	Page<User> PageByUserName(String userName, Pageable pageable);
}
