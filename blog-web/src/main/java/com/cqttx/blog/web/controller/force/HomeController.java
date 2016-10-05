package com.cqttx.blog.web.controller.force;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = { "", "/", "/index" })
	public String home(Model model) {
		model.addAttribute("test", "显示结果");
		return "force/index";
	}
}
