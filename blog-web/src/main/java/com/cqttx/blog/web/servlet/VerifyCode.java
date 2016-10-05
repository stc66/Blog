package com.cqttx.blog.web.servlet;

import com.cqttx.blog.web.util.VerifyCodeBuild;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/tools/code.stc", description = "验证码")
public class VerifyCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);

		VerifyCodeBuild instance = new VerifyCodeBuild();

		// Cookie cookie = new Cookie("scaptcha", instance.getCode());
		// cookie.setMaxAge(1800);
		// response.addCookie(cookie);

		request.getSession(true).setAttribute("verifyCode", instance.getCode());
		instance.write(response.getOutputStream());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
