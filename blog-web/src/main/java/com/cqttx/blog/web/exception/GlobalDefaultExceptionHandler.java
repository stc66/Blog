package com.cqttx.blog.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 滔天蟹
 * 
 *         全局异常捕捉
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletRequest req, Exception e) {
		e.printStackTrace();
		System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");
	}
}
