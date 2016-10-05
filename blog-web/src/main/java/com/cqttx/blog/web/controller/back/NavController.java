package com.cqttx.blog.web.controller.back;

import com.cqttx.blog.domain.entities.Nav;
import com.cqttx.blog.service.INavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/admin/")
@RestController
public class NavController {

	@Autowired
	private INavService navService;

	@RequestMapping(value = "nav")
	public List<Nav> getNav(@RequestParam(value = "id", defaultValue = "0") int id) {
		return this.navService.getNavByNid(id);
	}
}
