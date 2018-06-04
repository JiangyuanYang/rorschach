package com.wxun.rorschach.domain;

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
}
