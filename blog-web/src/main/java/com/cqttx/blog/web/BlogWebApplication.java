package com.cqttx.blog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author stc
 * @see blog-web启动入口
 */
@SpringBootApplication
@ComponentScan(basePackages={"com.cqttx.blog.domain","com.cqttx.blog.service","com.cqttx.blog.web"})
@ServletComponentScan
public class BlogWebApplication {
	public static void main(String[] args) {
		//SpringApplication.run(BlogWebApplication.class, new String[]{"hello","stc"});
		SpringApplication.run(BlogWebApplication.class, new String[]{"hello","stc"});
	}
}
