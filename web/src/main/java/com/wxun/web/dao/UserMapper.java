package com.wxun.web.dao;

import com.wxun.web.model.UserPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zhuwx
 * @date 2018-06-10 14:43
 */
@Repository
public interface UserMapper {

	/**
	 * 获取所有的用户信息
	 *
	 * @return 所有用户的信息
	 */
	List<UserPO> getAllUsers();
}
