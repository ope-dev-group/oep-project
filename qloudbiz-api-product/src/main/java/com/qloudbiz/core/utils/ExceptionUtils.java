package com.qloudbiz.core.utils;

import java.util.Properties;

import com.qloudbiz.core.exception.GenericException;

/**
 * 异常工具类
 * @author Administrator
 *
 */
public class ExceptionUtils {
	
	private final static String ZH_MSG_PATH="com/qloudfin/qloudbiz/message/en_message.properties";
	

	/**
	 * 抛出通用的异常,接收错误编码
	 * @param code
	 */
	public static void throwsGenericException(String code){
		//根据code获取消息
		String message=MessageUtils.getMessage(code);
		
		try {
			throw new GenericException(code,message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//抛出通用的异常，接收错误编码和数据
	public static void throwsGenericException(String code,Object data){
		//根据code获取消息
		String message=MessageUtils.getMessage(code);
		
		try {
			throw new GenericException(code,message,data);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
}
