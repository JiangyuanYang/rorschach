package com.wxun.rorschach.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Zhuwx
 * @since 2018-06-04 17:21
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {
	/**
	 * 登录session key
	 */
	public final static String SESSION_KEY = "rorschach";

	@Bean
	public SecurityInterceptor getSecurityInterceptor() {
		return new SecurityInterceptor();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

		// 排除配置
		addInterceptor.excludePathPatterns("/index");
		addInterceptor.excludePathPatterns("/error");
		addInterceptor.excludePathPatterns("/login**");
		addInterceptor.excludePathPatterns("/css/**");
		addInterceptor.excludePathPatterns("/js/**");
		addInterceptor.excludePathPatterns("/image/**");

		// 拦截配置
		addInterceptor.addPathPatterns("/**");
	}

	private class SecurityInterceptor extends HandlerInterceptorAdapter {

		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			HttpSession session = request.getSession();
			if (session.getAttribute(SESSION_KEY) != null) {
				return true;
			}

			// 跳转登录
			String url = "/login";
			response.sendRedirect(url);
			return false;
		}
	}
}
