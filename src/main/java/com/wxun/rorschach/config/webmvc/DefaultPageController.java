package com.wxun.rorschach.config.webmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Zhuwx
 * @since 2018-05-29 15:42
 */
@Controller
public class DefaultPageController {

	/**
	 * 欢迎页面
	 * @return
	 */
	@GetMapping(value = "/welcome")
	public String welcome() {
		return "welcome";
	}
}
