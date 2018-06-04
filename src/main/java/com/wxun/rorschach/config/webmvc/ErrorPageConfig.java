package com.wxun.rorschach.config.webmvc;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 错误页
 *
 * @author Zhuwx
 * @since 2018-05-29 15:25
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {
	@Override
	public void registerErrorPages(ErrorPageRegistry registry) {
		//错误类型为404，找不到网页的，默认显示404.html网页
		ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
		//错误类型为500，表示服务器响应错误，默认显示500.html网页
		ErrorPage e500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
		registry.addErrorPages(e404, e500);
	}

}
