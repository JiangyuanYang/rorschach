package com.wxun.rorschach.controller;

import com.wxun.rorschach.dao.UserMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhuwx
 * @since 2018-06-10 14:45
 */
@RestController
@Api(protocols = "http")
public class UserController {
	@Autowired
	UserMapper userser;

	@GetMapping("users")
	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user", required = true, paramType = "query", dataType = "String", allowableValues = "a,b,c,2")
	})
	public String getUser(String user) {
		System.out.println("44443");
		System.out.println(user);
		return user;
	}

	@PostMapping("users")
	@ApiOperation(value = "添加", notes = "添加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user", required = true, paramType = "form", dataType = "String")
	})
	public String addUser(String user) {
		System.out.println("44443");
		System.out.println(user);
		return user;
	}
}
