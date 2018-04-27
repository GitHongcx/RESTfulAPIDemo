package com.hcx.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hcx.exception.UserNotExistException;

@ControllerAdvice //只负责处理异常
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UserNotExistException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleUserNotExistException(UserNotExistException ex){
		Map<String, Object> result = new HashMap<>();
		//把需要的信息放到异常中
		result.put("id", ex.getId());
		result.put("message", ex.getMessage());
		return result;
	}

}
