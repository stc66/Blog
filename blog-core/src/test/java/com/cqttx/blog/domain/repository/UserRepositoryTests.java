package com.cqttx.blog.domain.repository;

import com.cqttx.blog.domain.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * @author stc
 * @see 类加上@Transactional，注解做测试不会真实写入数据库
 * @see 方法加上@Rollback，测试结束回滚数据，保证测试单元每次运行的数据环境独立
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Rollback
	public void testAddUser() throws Exception {
		User user = new User();
		user.setUserName("stc4");
		user.setPassword("22345");
		userRepository.save(user);
		//User restUser = this.userRepository.getUserByUserName("stc4");

		//assertThat(restUser.getPassword()).isEqualTo("22345");
	}

}
