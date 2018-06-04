package com.wxun.rorschach.config;

import lombok.Data;

/**
 * @author Zhuwx
 * @since 2018-05-29 13:08
 */
@Data
public class ResponseData {
	private boolean status;
	private int code;
	private String message;
	private Object data;
	private Inner inner;

	@Data
	public static class Inner{
		private String nam;
	}
}
