package com.cqttx.blog.service;

import com.cqttx.blog.domain.entities.User;
import org.springframework.data.domain.Page;

import java.util.Set;

/**
 * @author stc
 * @see 用户Service接口
 */
public interface IUserService {

	// 添加用户
	public Boolean addUser(User user, int[] roleIds);

	// 更新用户
	public Boolean updateUser(User user, int[] roleIds);

	// 根据ID删除User
	public void deleteUserById(Integer id);	
	
	// 根据ID得到User
	public User getUserById(Integer id);

	// 根据用户名查询用户
	public User getUserByUserName(String userName);

	// 获取用户的所有角色值
	public Set<String> getUserRoleValues(int userId);
	
	// 获取用户的所有权限值
	public Set<String> getUserPermissionValues(int userID);
	
	// 用户登录
	public User userLogin(String userName, String password);
	
	

	// 根据分页查询所有用户
	public Page<User> listUser(String name, int page, int rows, String sort, String order);
}
