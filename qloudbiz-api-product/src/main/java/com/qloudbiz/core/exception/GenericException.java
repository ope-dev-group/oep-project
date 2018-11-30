package com.qloudbiz.core.exception;

import lombok.Data;

/**
 * 通用异常处理类，自定义异常的基类
 * @author Administrator
 *
 */
@Data
public class GenericException extends Exception{
	String code;//异常编码
	String msg;//异常消息
	Object data;//数据
	
	public GenericException(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	
	public GenericException(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
}
