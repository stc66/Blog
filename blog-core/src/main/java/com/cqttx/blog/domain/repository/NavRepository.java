package com.cqttx.blog.domain.repository;

import com.cqttx.blog.domain.entities.Nav;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NavRepository extends JpaRepository<Nav, Integer> {

	public List<Nav> findByNid(int nid);
}
