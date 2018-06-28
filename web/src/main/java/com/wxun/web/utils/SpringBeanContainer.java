package com.wxun.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Zhuwx
 * @since 2018-06-27 11:18
 */
public final class SpringBeanContainer implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	private SpringBeanContainer() {

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanContainer.applicationContext = applicationContext;
	}

	public static <T> T getBean(Class<T> clz) {
		return applicationContext.getBean(clz);
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}