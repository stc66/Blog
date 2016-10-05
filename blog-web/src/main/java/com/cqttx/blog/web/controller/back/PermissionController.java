package com.cqttx.blog.web.controller.back;

import com.cqttx.blog.domain.entities.Permission;
import com.cqttx.blog.domain.entities.User;
import com.cqttx.blog.service.IPermissionService;
import com.cqttx.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin/permission/")
@Controller
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="manager", method = RequestMethod.GET)
	public String manager(){
		return "back/permissionManager";
	}
	
	@RequestMapping(value="manager", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> managerPost(String user,int page,int rows,String sort,String order){
		Map<String, Object> result = new HashMap<String, Object>(2) ; 
		List<User> users=userService.listUser(user,page,rows,sort,order).getContent();
		result.put("total", 5);
		result.put("rows", users);
		return result;
	}	
	
	@RequestMapping(value = { "addPermission" }, method = RequestMethod.POST)
	@ResponseBody
	public Boolean addPermission(Permission permission){
		
		
		return permissionService.addPermission(permission);
	}
}
