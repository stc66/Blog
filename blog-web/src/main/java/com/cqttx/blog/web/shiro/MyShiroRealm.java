package com.cqttx.blog.web.shiro;

import com.cqttx.blog.domain.entities.User;
import com.cqttx.blog.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author admin
 * @see Shiro认证授权
 */
public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;

	/*
	 * 认证信息，验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户输入的账号.
		String userName = token.getPrincipal().toString();
		// 获取用户输入的密码
		String password = new String((char[])token.getCredentials());
		
		User user = userService.getUserByUserName(userName);
		if (null == user) {
			throw new AccountException("用户名或密码不正确！");
		} else if (user.getStatus() == 2) {
			// throw new DisabledAccountException("帐号已经禁止登录！");
			throw new LockedAccountException("帐号已经禁止登录！");
		} else {
			user.setLoginTime(new Date());
			user.setLoginNumber(user.getLoginNumber()+1);
			userService.updateUser(user, null);
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),
				user.getPassword(), getName());
		simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getUserName()));
		return simpleAuthenticationInfo;
	}

	/**
	 * 权限资源角色
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		User user = userService.getUserByUserName(username);
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRoles(userService.getUserRoleValues(user.getId()));
		simpleAuthorizationInfo.addStringPermissions(userService.getUserPermissionValues(user.getId()));
		return simpleAuthorizationInfo;
	}
}
