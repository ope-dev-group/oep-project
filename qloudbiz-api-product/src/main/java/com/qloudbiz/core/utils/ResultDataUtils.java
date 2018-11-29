package com.qloudbiz.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.qloudbiz.core.result.BaseResult;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.result.ResultData;

/**
 * 结果工具类
 * @author Administrator
 *
 */
public class ResultDataUtils {
	
	private final static String SUCCESS_CODE="0";//成功请求的code
	
	private final static String ZH_MSG_PATH="com/qloudfin/qloudbiz/message/en_message.properties";
	
	//资源文件单例模式
	private final static Properties messageProperties=PropertiesUtils.getProperties(ZH_MSG_PATH);

	
	/**
	 * 获取成功的消息,且返回数据
	 * @param code 消息编码
	 * @param data 返回的数据
	 * @return
	 */
	public static <T>  ResultData success(T data){
		ResultData<T> resultData=new ResultData<T>();
		
		resultData.setResultCode(SUCCESS_CODE);
		resultData.setMsg(getMessageByCode(SUCCESS_CODE));
		resultData.setResult(data);
		
		return resultData;
	}
	
	/**
	 * 获取成功的消息对象，且不接收数据，增、删、改、可以用此返回对象
	 * @param code 消息编码
	 * @param data 返回的数据
	 * @return
	 */
	public static BaseResult success(){
		BaseResult resultData=new BaseResult();
		
		resultData.setResultCode(SUCCESS_CODE);
		resultData.setMsg(getMessageByCode(SUCCESS_CODE));
		return resultData;
	}
	
	
	/**
	 * 获取成功的分页消息，且不接收数据
	 * @param code 消息编码
	 * @param data 返回的数据
	 * @return
	 */
	public static  PageResultData successPage(PageResultData data){
		data.setResultCode(SUCCESS_CODE);
		data.setMsg(getMessageByCode(SUCCESS_CODE));
		return data;
	}
	
	
	/**
	 * 获取失败的消息,接收数据
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
	
	
	/**
	 * 接收失败的消息,且没有返回数据
	 * @param code
	 * @return
	 */
	public static <T> BaseResult error(String code){
		ResultData<T> resultData=new ResultData<T>();
	
		
		resultData.setResultCode(code);
		resultData.setMsg(getMessageByCode(code));
		
		return resultData;
	}
	
	/**
	 * 接收失败的消息,且没有返回数据
	 * @param code
	 * @return
	 */
	public static PageResultData errorPage(String code,PageResultData data){
		
	
		
		data.setResultCode(code);
		data.setMsg(getMessageByCode(code));
		
		return data;
	}
	
	
	
	//根据消息的key获取消息对象的内容
	private static String getMessageByCode(String code){
		if(null==code || code.isEmpty())return null;
		return messageProperties.getProperty(code);
	}
}
