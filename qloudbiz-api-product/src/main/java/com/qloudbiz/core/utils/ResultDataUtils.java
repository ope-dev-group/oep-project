package com.qloudbiz.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.qloudbiz.core.result.BaseResult;
import com.qloudbiz.core.result.ResultData;

/**
 * 结果工具类
 * @author Administrator
 *
 */
public class ResultDataUtils {
	
	private final static String ZH_MSG_PATH="com/qloudfin/qloudbiz/message/en_message.properties";
	
	//资源文件单例模式
	private final static Properties messageProperties=PropertiesUtils.getProperties(ZH_MSG_PATH);

	
	/**
	 * 获取成功的消息
	 * @param code 消息编码
	 * @param data 返回的数据
	 * @return
	 */
	public static <T> BaseResult success(String code,T data){
		ResultData<T> resultData=new ResultData<T>();
		
		resultData.setResultCode(code);
		resultData.setMsg(getMessageByCode(code));
		resultData.setResult(data);
		
		return resultData;
	}
	
	
	/**
	 * 获取失败的消息
	 * @param code  消息编码
	 * @param data  返回的数据
	 * @return
	 */
	public static <T> BaseResult error(String code,T data){
		ResultData<T> resultData=new ResultData<T>();
	
		
		resultData.setResultCode(code);
		resultData.setMsg(getMessageByCode(code));
		resultData.setResult(data);
		
		return resultData;
	}
	
	
	//根据消息的key获取消息对象的内容
	private static String getMessageByCode(String code){
		if(null==code || code.isEmpty())return null;
		return messageProperties.getProperty(code);
	}
}
