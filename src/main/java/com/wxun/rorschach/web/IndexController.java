package com.wxun.rorschach.web;

import com.wxun.rorschach.config.WebSecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zhuwx
 * @since 2018-05-29 15:42
 */
@Controller
public class IndexController {

	/**
	 * 登录
	 *
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String login(String account, String password, HttpSession session) {
		if (!"123456".equals(password)) {
			return "redirect:/login";
		}

		// 设置session
		session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
		return "index";
	}

	@GetMapping(value = "/login")
	public String doLogin() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 移除session
		session.removeAttribute(WebSecurityConfig.SESSION_KEY);
		return "redirect:/login";
	}
}
