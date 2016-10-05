package com.cqttx.blog.service.impl;

import com.cqttx.blog.domain.entities.Nav;
import com.cqttx.blog.domain.repository.NavRepository;
import com.cqttx.blog.service.INavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("navService")
public class NavServiceImpl implements INavService {

	@Autowired
	private NavRepository navRepository;
	
	@Override
	public List<Nav> getNavByNid(int nid) {
		return navRepository.findByNid(nid);
	}

	@Override
	public void save(Nav nav) {
		navRepository.save(nav);
	}

}
