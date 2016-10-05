package com.cqttx.blog.service;

import com.cqttx.blog.domain.BlogDomainApplication;
import com.cqttx.blog.domain.entities.Nav;
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
public class NavServiceTests {

	@Autowired
	private INavService navService;

	@Test
	@Rollback
	public void testAddUser() {
		Nav nav = new Nav();
		nav.setText("系统模块");
		nav.setState("closed");
		nav.setIconCls("icon-system");
		nav.setNid(0);
		navService.save(nav);
		
		String result = navService.getNavByNid(1).get(0).getState();
		assertThat(result).isEqualTo("closed");
		// assertThat("stc4").isEqualTo("stc4");
	}
}
