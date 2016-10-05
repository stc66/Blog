package com.cqttx.blog.domain.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author stc
 * @see 用户,由于和Mybatis混用,不再改默认表和字段名字,不做映射,便 于Mybatis使用
 */

@Entity
// @Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	// 用户名
	@Column(unique = true, nullable = false, length = 50)
	private String userName;

	// 密码
	@Column(nullable = false, length = 100)
	private String password;

	// 别名
	@Column(length = 100)
	private String nickName;

	// Email
	@Column(length = 100)
	private String email;

	// 联系电话
	@Column(length = 50)
	private String phone;

	// 创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	// 登录时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;

	// 登录次数
	private Long loginNumber;

	// 登录IP地址
	@Column(length = 39)
	private String loginIpAddress;

	// 排序
	private Integer sort;

	// 用户状态 0:创建未认证 ,1:正常状态,2：用户被锁定.
	private byte status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Long getLoginNumber() {
		return loginNumber;
	}

	public void setLoginNumber(Long loginNumber) {
		this.loginNumber = loginNumber;
	}

	public String getLoginIpAddress() {
		return loginIpAddress;
	}

	public void setLoginIpAddress(String loginIpAddress) {
		this.loginIpAddress = loginIpAddress;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

}
