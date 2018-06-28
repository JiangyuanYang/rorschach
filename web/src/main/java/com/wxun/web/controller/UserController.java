package com.wxun.web.controller;

import com.wxun.web.model.ResponseData;
import com.wxun.web.model.UserPO;
import com.wxun.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Zhuwx
 * @since 2018-06-10 14:45
 */

@Api(protocols = "http", tags = "用户信息")
@Slf4j
@RestController
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@GetMapping("users")
	@Cacheable(value = "users", keyGenerator = "keyGeneratorStatic")
	public ResponseData<List<UserPO>> users() {
		List<UserPO> users = userService.allUsers();
		log.info(users.toString());

		ResponseData<List<UserPO>> response = new ResponseData<>();
		response.setData(users);
		response.setStatus(true);
		response.setCode(200);
		response.setMessage("成功");
		return response;
	}


	@ApiOperation(value = "新增用户", notes = "新增用户")
	@PostMapping("users")
	@CacheEvict(value = "users", keyGenerator = "keyGeneratorStatic")
	public ResponseData<UserPO> addUser(@Validated @RequestBody UserPO user, BindingResult bindingResult) {
		ResponseData<UserPO> response = new ResponseData<>();
		if (bindingResult.hasErrors()) {
			ObjectError objectError = bindingResult.getAllErrors().get(0);
			response.setStatus(false);
			response.setCode(500);
			response.setMessage(((FieldError) objectError).getField() + ":" + objectError.getDefaultMessage());
			return response;
		}
		log.info(user.toString());
		response.setStatus(true);
		response.setCode(201);
		response.setMessage("添加成功");
		response.setData(user);
		return response;
	}
}
