package com.wxun.web.exception;

import com.wxun.web.model.ResponseData;
import com.wxun.web.model.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * controller统一处理
 *
 * @author Zhuwx
 * @since 2018-06-28 19:30
 */
@ControllerAdvice
@Slf4j
public class UnifiedExceptionHandler {
	/**
	 * 统一异常处理：业务异常
	 */
	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	public ResponseData<UserPO> handleBusinessException(Exception exception) {

		log.error(exception.getMessage());
		ResponseData<UserPO> response = new ResponseData<>();
		response.setStatus(false);
		response.setCode(-1);
		response.setMessage("操作失败:" + exception.getMessage());
		return response;
	}

	/**
	 * 统一异常处理：运行时异常
	 */
	@ExceptionHandler(value = RuntimeException.class)
	@ResponseBody
	public ResponseData<UserPO> handleRuntimeException(Exception exception) {

		log.error(exception.getMessage());
		ResponseData<UserPO> response = new ResponseData<>();
		response.setStatus(false);
		response.setCode(-1);
		response.setMessage("操作失败");
		return response;
	}

	/**
	 * 统一异常处理：其它非运行时异常。
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResponseData<UserPO> handleException(Exception exception) {

		log.error(exception.getMessage());
		ResponseData<UserPO> response = new ResponseData<>();
		response.setStatus(false);
		response.setCode(-1);
		response.setMessage("系统错误");
		return response;
	}

	/**
	 * 统一异常处理：其它异常，包括error，异常不做处理，尝试做日志记录
	 */
	@ExceptionHandler(value = Throwable.class)
	@ResponseBody
	public void handleThrowable(Throwable throwable) throws Throwable {
		log.error("重大异常：", throwable.getStackTrace());
		//日志记录，告警等
		throw throwable;
	}

}
