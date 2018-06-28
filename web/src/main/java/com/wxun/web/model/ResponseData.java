package com.wxun.web.model;

import lombok.Data;

/**
 * @author Zhuwx
 * @since 2018-05-29 13:08
 */
@Data
public class ResponseData<T> {
	private boolean status;
	private int code;
	private String message;
	private T data;
}
