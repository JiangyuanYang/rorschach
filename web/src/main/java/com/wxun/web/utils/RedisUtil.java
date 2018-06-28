package com.wxun.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis访问工具类
 *
 * @author Zhuwx
 * @since 2018-06-27 10:59
 */
@Component
public class RedisUtil<K,V> {
	@Autowired
	private StringRedisTemplate  redisTemplate;





}
