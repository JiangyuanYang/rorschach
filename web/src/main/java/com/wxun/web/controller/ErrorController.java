package com.wxun.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Zhuwx
 * @since 2018-05-29 15:36
 */
@Controller
@RequestMapping("/error")
@ApiIgnore
public class ErrorController {

	/**
	 * 404页面
	 */
	@GetMapping(value = "/404")
	public String error404() {
		return "error/404";
	}

	/**
	 * 500页面
	 */
	@GetMapping(value = "/500")
	public String error500() {
		return "error/500";
	}

}