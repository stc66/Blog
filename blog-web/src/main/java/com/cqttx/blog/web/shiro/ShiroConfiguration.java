package com.cqttx.blog.web.shiro;

import com.cqttx.blog.web.shiro.filter.ResourceCheckFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author admin
 * @see Shiro配置
 */
@Configuration
public class ShiroConfiguration {	
	
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean(name = "cacheManager")
	public EhCacheManager cacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return cacheManager;
	}

	@Bean(name = "hashedCredentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");
		credentialsMatcher.setHashIterations(2);
		credentialsMatcher.setStoredCredentialsHexEncoded(true);
		return credentialsMatcher;
	}

	@Bean(name = "myShiroRealm")
	@DependsOn("lifecycleBeanPostProcessor")
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm realm = new MyShiroRealm();
		realm.setCacheManager(cacheManager());
		realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}

	/**
	 * cookie对象;
	 * 
	 * @return
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		// 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		// 记住我cookie生效时间30天 ,单位秒
		//simpleCookie.setMaxAge(259200);
		simpleCookie.setMaxAge(-1);
		return simpleCookie;
	}

	/**
	 * cookie管理对象;
	 * 
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm());
		// 注入缓存管理器
		securityManager.setCacheManager(cacheManager());
		// 注入记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());

		return securityManager;
	}

	/**
	 * URL权限认证,千万不能乱添加Bean注解 
	 * 
	 * @return
	 */
	public ResourceCheckFilter resourceCheckFilter() {
		ResourceCheckFilter rcf = new ResourceCheckFilter();
		rcf.setErrorUrl("admin/unauthor");
		return rcf;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		// 设置登录页
		shiroFilterFactoryBean.setLoginUrl("/admin/login");
		// 登录成功跳转页
		shiroFilterFactoryBean.setSuccessUrl("/admin/main");
		shiroFilterFactoryBean.setUnauthorizedUrl("/admin/unauthor");

		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/admin/login");
		filters.put("logout", logoutFilter);
		//filters.put("resourceCheckFilter", resourceCheckFilter());
		shiroFilterFactoryBean.setFilters(filters);

		Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
		filterChainDefinitionManager.put("/admin/login", "anon");
		filterChainDefinitionManager.put("/admin/unauthor", "anon");
		filterChainDefinitionManager.put("/admin/logout", "logout");
		//filterChainDefinitionManager.put("/admin/**", "authc,resourceCheckFilter");
		filterChainDefinitionManager.put("/admin/**", "authc");
		filterChainDefinitionManager.put("/**", "anon");	
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

		return shiroFilterFactoryBean;
	}

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter")); 
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/admin/**"); 
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }	
	
	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager());
		return aasa;
	}
}
