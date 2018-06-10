package com.wxun.rorschach.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Zhuwx
 * @create 2018-06-10 14:43
 */
@Repository
public interface UserMapper {

	List<Map> getOrdersByUser();
}
