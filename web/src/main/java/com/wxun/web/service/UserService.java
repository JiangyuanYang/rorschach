package com.wxun.web.service;

import com.wxun.web.dao.UserMapper;
import com.wxun.web.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zhuwx
 * @since 2018-06-28 13:13
 */
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	@Transactional(rollbackFor = RuntimeException.class)
	public List<UserPO> allUsers() {
		List<UserPO> allUsers = userMapper.getAllUsers();
		return allUsers;
	}
}
