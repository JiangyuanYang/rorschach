package com.wxun.web.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * redis缓存配置管理
 *
 * @author Zhuwx
 * @since 2018-05-29 15:25
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

	/** 缓存管理器 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		@SuppressWarnings("unchecked")
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		RedisCacheConfiguration redisCacheConfigurationDefault = RedisCacheConfiguration
				.defaultCacheConfig()
				//60秒过期时间
				.entryTtl(Duration.ofSeconds(60))
				//value序列化方式，默认使用的是JdkSerializationRedisSerializer()，需要对象实现序列化接口;这边是用jackson序列化
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer));

		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(redisCacheConfigurationDefault)
				.build();

	}


	/**
	 * 生成静态的key
	 *
	 */
	@Bean("keyGeneratorStatic")
	public KeyGenerator keyGeneratorStatic() {

		return (target, method, params) -> "REDIS_STATIC_KEY";
	}

	/**
	 * 根据方法生成缓存key
	 *
	 */
	@Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(target.getClass().getName());
			sb.append(method.getName());
			for (Object obj : params) {
				sb.append(obj.toString());
			}
			return sb.toString();
		};
	}

	/**
	 * RedisTemplate 配置序列化方式
	 * @param factory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		//定义key序列化方式
//		Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		//定义value的序列化方式
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setKeySerializer(redisSerializer);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

}

