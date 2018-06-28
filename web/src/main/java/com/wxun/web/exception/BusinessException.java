package com.wxun.web.exception;

/**
 * 业务异常
 *
 * @author Zhuwx
 * @since 2018-06-28 19:50
 */
public class BusinessException extends RuntimeException{


	public BusinessException(String message) {
		super(message);
	}


}
