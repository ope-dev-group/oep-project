package com.qloudbiz.core.result;

import lombok.Data;

/**
 * 返回消息对象
 * @author Administrator
 *
 * @param <T>
 */
@Data
public class ResultData<T> {
	private String resultStatus; //返回的状态码200,500
	private String resultCode;  //返回消息编码
	private String msg;   //返回消息主体
	private T data;		  //返回的数据
}
