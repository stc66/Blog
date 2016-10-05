package com.cqttx.blog.web.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 * @author admin
 * @see 服务启动时执行
 */
//@Component
@Order(value = 1)
public class StartBootRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println(Arrays.asList(args));
		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
	}
}
