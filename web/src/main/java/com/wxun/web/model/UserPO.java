package com.wxun.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Zhuwx
 * @since 2018-06-28 13:30
 */
@Data
@ApiModel(value = "用户信息")
public class UserPO {
	@ApiModelProperty(value = "用户id",required = true)
	@Min(value = 0,message = "用户id不合法")
	private Long pkUserId;

	@ApiModelProperty(value = "用户姓名",required = true)
	@NotBlank(message = "用户名不为空")
	private String username;

	@ApiModelProperty(value = "用户密码",required = true)
	@NotBlank(message = "用户密码不为空")
	private String password;
}
