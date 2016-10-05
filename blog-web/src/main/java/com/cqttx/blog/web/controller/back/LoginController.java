package com.cqttx.blog.web.controller.back;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = {"", "/", "/login"}, method = RequestMethod.GET)
    public String login() {
        return "back/login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginPost(String userName, String password, @RequestParam(value = "rememberMe", defaultValue = "false") Boolean rememberMe, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

        Map<String, Object> result = new HashMap<String, Object>(2);
        String msg = "";
        Boolean flag = false;
        try {
            token.setRememberMe(rememberMe);
            subject.login(token);
            flag = true;
        } catch (UnknownAccountException e) {
            msg = "用户名出错!";
            logger.info(userName + ":用户名出错!");
        } catch (IncorrectCredentialsException e) {
            msg = "用户名或密码不正确!";
            logger.info(userName + ":密码出错!");
        } catch (LockedAccountException lae) {
            msg = "账户已锁定!";
            logger.info(userName + ":账户已锁定!");
        } catch (ExcessiveAttemptsException eae) {
            msg = "用户名或密码错误次数过多!";
            logger.info("用户名或密码错误次数过多!");
        } catch (AuthenticationException e) {
            msg = e.getMessage();
            logger.info(e.getMessage());
        }

        // 使用以下方法登录之后再去登录账户验证失败，仍然能正常进入
//		if (subject.isAuthenticated()) {
//			flag = true;
//		} else {
//			token.clear();
//			flag = false;
//		}

        result.put("msg", msg);
        result.put("flag", flag);
        return result;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletResponse response) {
        SecurityUtils.getSubject().logout();
        return "redirect:/admin/login";
    }

    @RequestMapping(value = "/unauthor", method = RequestMethod.GET)
    public String unauthor(HttpServletResponse response) {
        return "back/unauthor";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main() {
        return "back/main";
    }
}
