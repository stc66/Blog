package com.cqttx.blog.domain.mapper;

import com.cqttx.blog.domain.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserMapperTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	@Rollback
	public void testFindUserByUserName() {
		User user = userMapper.findUserByUserName("stc");
		assertThat(user.getUserName()).isEqualTo("stc");
	}

	@Test
	@Rollback
	public void testFindByNameAndPassword() {
		Integer id = userMapper.findByNameAndPassword("stc4", "22345");
		assertThat(id).isEqualTo(2);
	}
	
	@Test
	@Rollback
	public void testFindById(){
		User user = userMapper.findById(2);
		assertThat(user.getUserName()).isEqualTo("stc4");
	}
}
