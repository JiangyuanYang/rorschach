package com.wxun.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * 如果继承WebMvcConfigurationSupport，会使SpringBoot yml的自动配置失效，需要手动配置静态资源等路径
 * 实现WebMvcConfigurer接口不需要
 *
 * @author Zhuwx
 * @since 2018-06-28 15:44
 */
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {


	/**
	 * 设置webmvc返回对象序列化方式
	 *
	 * @param converters
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addSerializer(new ToStringSerializer(Long.TYPE));
		module.addSerializer(new ToStringSerializer(Long.class));
		module.addSerializer(new ToStringSerializer(BigInteger.class));
		objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
				jsonGenerator.writeString("");
			}
		});
		objectMapper.registerModule(module);
		converter.setObjectMapper(objectMapper);

		//这里是fastJSON的配置方式，更多的内容可以查看SerializerFeature
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        converter.setFeatures(SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
//                SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty);
		converters.add(converter);
	}
}