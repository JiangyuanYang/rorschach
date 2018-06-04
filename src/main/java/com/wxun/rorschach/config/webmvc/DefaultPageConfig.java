package com.wxun.rorschach.config.webmvc;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Zhuwx
 * @since 2018-05-29 13:48
 */
@Component
public class DefaultPageConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/welcome");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

}