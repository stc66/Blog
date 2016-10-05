package com.cqttx.blog.service;

import com.cqttx.blog.domain.entities.Nav;

import java.util.List;

public interface INavService {

	public List<Nav> getNavByNid(int nid);
	
	public void save(Nav nav);
}
