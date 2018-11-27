package com.qloudbiz.core.result;

import lombok.Data;

/**
 * 返回消息对象
 * @author Administrator
 *
 * @param <T>
 */
@Data
public class BaseResult{
	
	private String resultCode;  //返回消息编码
	private String msg;   //返回消息主体
	
	
}
