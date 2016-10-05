package com.cqttx.blog.web;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogWebApplicationTests {

	private MockMvc mvc;

	// @Before
	// public void setUp() throws Exception {
	// mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	// }
	//
	// @Test
	// public void contextLoads() throws Exception {
	// mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
	// .andExpect(content().string(equalTo("Hello World")));
	// }

	@Test
	public void testEncod(){
		System.out.println(new Md5Hash("123456","stc",2).toHex());
	}
}
