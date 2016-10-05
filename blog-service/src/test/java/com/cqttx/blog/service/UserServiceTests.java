package com.cqttx.blog.service;

import com.cqttx.blog.domain.BlogDomainApplication;
import com.cqttx.blog.domain.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author stc
 * @see 类加上@Transactional，注解做测试不会真实写入数据库
 * @see 方法加上@Rollback，测试结束回滚数据，保证测试单元每次运行的数据环境独立
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BlogServiceApplication.class, BlogDomainApplication.class })
@Transactional
public class UserServiceTests {

	@Autowired
	private IUserService userService;

	@Test
	@Rollback
	public void testAddUser() {
		User user = new User();
		user.setUserName("stc");
		user.setPassword("a71a28174dea1e7c59cbef16423cd4fd");
		Boolean result = userService.addUser(user,null);

		assertThat(result).isTrue();
		// assertThat("stc4").isEqualTo("stc4");
	}

	@Test
	@Rollback
	public void testLogin() {
		User result = userService.userLogin("stc", "a71a28174dea1e7c59cbef16423cd4fd");
		//assertThat(result).isTrue();
		assertThat(result.getUserName()).isXmlEqualTo("stc");
	}
}
