package com.cqttx.blog.domain.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author stc
 * @see 用户与角色
 */
@Entity
public class UserRole {

	@Id
	@GeneratedValue
	private Integer id;

	// 用户ID
	private int userId;

	// 角色ID
	private int roleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
