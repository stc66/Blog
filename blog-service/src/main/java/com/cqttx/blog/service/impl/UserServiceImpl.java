package com.cqttx.blog.service.impl;

import com.cqttx.blog.domain.entities.Role;
import com.cqttx.blog.domain.entities.RolePermission;
import com.cqttx.blog.domain.entities.User;
import com.cqttx.blog.domain.entities.UserRole;
import com.cqttx.blog.domain.mapper.*;
import com.cqttx.blog.domain.repository.UserRepository;
import com.cqttx.blog.domain.repository.UserRoleRepository;
import com.cqttx.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Autowired
	private PermissionMapper permissionMapper;

	// 添加用户
	@Override
	@Transactional
	public Boolean addUser(User user, int[] roleIds) {
		Boolean flag = false;
		User result = null;

		user.setCreateTime(new Date());
		result = userRepository.save(user);
		if (result != null) {
			flag = true;
			if (roleIds != null && roleIds.length > 0) {
				UserRole userRole = null;
				for (int rid : roleIds) {
					userRole = new UserRole();
					userRole.setUserId(result.getId());
					userRole.setRoleId(rid);
					userRoleRepository.save(userRole);
				}
			}
		}
		return flag;
	}

	// 更新用户
	@Override
	@Transactional
	public Boolean updateUser(User user, int[] roleIds) {
		Boolean flag = false;
		User result = null;

		result = userRepository.save(user);
		if (result != null) {
			flag = true;
			if (roleIds != null && roleIds.length > 0) {
				// 先删除原来的中间表再保存
				List<UserRole> list = userRoleMapper.findUserRoleByUserId(result.getId());
				for (UserRole ur : list) {
					userRoleRepository.delete(ur.getId());
				}
				// 重新保存
				UserRole userRole = null;
				for (int rid : roleIds) {
					userRole = new UserRole();
					userRole.setUserId(result.getId());
					userRole.setRoleId(rid);
					userRoleRepository.save(userRole);
				}
			}
		}
		return flag;
	}

	// 根据ID删除User
	@Override
	@Transactional
	public void deleteUserById(Integer id) {
		userRepository.delete(id);
		// 删除用户角色表
		userRoleRepository.deleteUserRoleByUserId(id);
	}

	// 根据ID得到User
	@Override
	public User getUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	// 根据用户名查询用户
	@Override
	public User getUserByUserName(String userName) {
		return userMapper.findUserByUserName(userName);
	}

	// 获取用户的所有角色值
	public Set<String> getUserRoleValues(int userId) {
		Set<String> set = new HashSet<String>();
		List<UserRole> list = userRoleMapper.findUserRoleByUserId(userId);
		for (UserRole ur : list) {
			set.add(roleMapper.findRoleById(ur.getRoleId()).getRoleValue());
		}
		return set;
	}

	// 获取用户的所有权限值
	public Set<String> getUserPermissionValues(int userId) {
		Set<String> set = new HashSet<String>();
		List<UserRole> list = userRoleMapper.findUserRoleByUserId(userId);

		Set<Role> setRole = new HashSet<Role>();
		for (UserRole ur : list) {
			setRole.add(roleMapper.findRoleById(ur.getRoleId()));
		}
		Set<RolePermission> setRolePermission = new HashSet<RolePermission>();
		for (Role r : setRole) {
			setRolePermission.addAll(rolePermissionMapper.findListRolePermissionByRoleId(r.getId()));
		}
		for (RolePermission rp : setRolePermission) {
			set.add(permissionMapper.findPermissionById(rp.getPermissionId()).getUrl());
		}
		return set;
	}

	@Override
	public User userLogin(String userName, String password){
		User user = userMapper.findUserByUserNameAndPassword(userName, password);
		return user;
	}

	@Override
	public Page<User> listUser(String name, int page, int rows, String sort, String order) {
		Sort sorts = null;
		if (order.equalsIgnoreCase("desc")) {
			sorts = new Sort(new Order(Sort.Direction.DESC, sort, Sort.NullHandling.NULLS_LAST))
					.and(new Sort(Sort.Direction.ASC, "id"));
		} else {
			sorts = new Sort(new Order(Sort.Direction.ASC, sort, Sort.NullHandling.NULLS_LAST))
					.and(new Sort(Sort.Direction.ASC, "id"));
		}

		Pageable pageable = new PageRequest(page - 1, rows, sorts);
		if (name != null && !name.equals("")) {
			return userRepository.PageByUserName(name, pageable);
		}
		return userRepository.findAll(pageable);
	}
}
