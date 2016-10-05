package com.cqttx.blog.web.controller.back;

import com.cqttx.blog.domain.entities.User;
import com.cqttx.blog.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/admin/")
@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="manager", method = RequestMethod.GET)
	public String manager(){
		
		return "back/manager";
	}
	
	@RequestMapping(value="manager", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> managerPost(String user,int page,int rows,String sort,String order){
		Map<String, Object> result = new HashMap<String, Object>(2) ; 
		List<User> users=userService.listUser(user,page,rows,sort,order).getContent();
		logger.info(String.valueOf(users.size()));
		result.put("total", 5);
		result.put("rows", users);
		return result;
	}
	
	@RequestMapping(value = { "addUser" }, method = RequestMethod.POST)
	@ResponseBody
	public Boolean addUser(String manager,String password,String auth){
		User user = new User();
		user.setUserName(manager);
		user.setPassword(password);
		return userService.addUser(user,null);
	}
	
	@RequestMapping(value = { "editUser" }, method = RequestMethod.POST)
	@ResponseBody
	public Boolean editUser(Integer id,String password,String auth){
		User user = userService.getUserById(id);
		if(!password.isEmpty()){
			user.setPassword(password);
		}
		return userService.updateUser(user,null);
	}	
	
	@RequestMapping(value = { "getUser" }, method = RequestMethod.POST)
	@ResponseBody
	public User getUser(Integer id){
		return userService.getUserById(id);
	}		
	
	@RequestMapping(value = { "deleteUser" }, method = RequestMethod.POST)
	@ResponseBody
	public Integer deleteUser(Integer ids[]){
		for(Integer obj:ids){
			userService.deleteUserById(obj);
		}
		return ids.length;
	}		
}
