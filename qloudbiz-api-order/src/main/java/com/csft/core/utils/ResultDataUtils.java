package com.csft.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Properties;

import com.alibaba.fastjson.JSON;
import com.csft.core.exception.GenericException;
import com.csft.core.result.BaseResult;
import com.csft.core.result.PageResultData;
import com.csft.core.result.ResultData;


/**
 * 结果工具类
 * @author Administrator
 *
 */
public class ResultDataUtils {
	private final static String SUCCESS_CODE="0";//成功请求的code
	
	private final static String UNKNOW_ERROR_CODE="400";
	

	
	/**
	 * 获取成功的消息,且返回数据
	 * @param code 消息编码
	 * @param data 返回的数据
	 * @return
	 */
	public static <T>  ResultData success(T data){
		ResultData<T> resultData=new ResultData<T>();
		
		resultData.setResultCode(SUCCESS_CODE);
		resultData.setResultMsg(MessageUtils.getMessage(SUCCESS_CODE));
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
		resultData.setResultMsg(MessageUtils.getMessage(SUCCESS_CODE));
		return resultData;
	}
	

	
	
	
	
	
	
	/**
	 * 获取成功的分页消息，且不接收数据
	 * @param code 消息编码
	 * @param data 返回的数据
	 * @return
	 */
	public static  PageResultData success(PageResultData data){
		
		if(null!=data){
			data.setResultCode(SUCCESS_CODE);
			data.setResultMsg(MessageUtils.getMessage(SUCCESS_CODE));
		}
		
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
		resultData.setResultMsg(MessageUtils.getMessage(code));
		resultData.setResult(data);
		

		String objstr=JSON.toJSONString(resultData);
		return JSON.parseObject(objstr, resultData.getClass());
		
	}
	
	
	
	
	
	
	/**
	 * 获取失败的消息,接收数据
	 * @param code  消息编码
	 * @param data  返回的数据
	 * @return
	 */
	public static <T> BaseResult error(String code,T data,String[] placeholders){
		ResultData<T> resultData=new ResultData<T>();
	
		
		resultData.setResultCode(code);
		resultData.setResultMsg(MessageUtils.getMessage(code, placeholders));
		resultData.setResult(data);
		String objstr=JSON.toJSONString(resultData);
		return JSON.parseObject(objstr, resultData.getClass());
		
	}
	
	
	/**
	 * 接收失败的消息,且没有返回数据
	 * @param code
	 * @return
	 */
	public static <T> BaseResult error(String code,String[] placeholders){
		ResultData<T> resultData=new ResultData<T>();

		resultData.setResultCode(code);
		resultData.setResultMsg(MessageUtils.getMessage(code,placeholders));
		
		return resultData;
	}
	
	/**
	 * 接收失败的消息,且没有返回数据
	 * @param code
	 * @return
	 */
	public static BaseResult error(String code){
		ResultData resultData=new ResultData();

		resultData.setResultCode(code);
		resultData.setResultMsg(MessageUtils.getMessage(code));
		
		return resultData;
	}
	
	
	/**
	 * 返回异常
	 * @param exception
	 * @return
	 */
	public static BaseResult error(Exception exception,String[] placeholders){
		
		if(null!=exception ){
			if(exception instanceof GenericException){
				return error(((GenericException)exception).getCode());
			}
			
			
			UndeclaredThrowableException e=null;
			
			if(exception instanceof InvocationTargetException ){
				Throwable throwable=((InvocationTargetException) exception).getTargetException();
				if(throwable instanceof GenericException){
					return error(((GenericException)throwable).getCode(),placeholders);
				}
			}
			
		}
		return error(UNKNOW_ERROR_CODE);
	}
	
	/**
	 * 返回异常
	 * @param exception
	 * @return
	 */
	public static BaseResult error(Exception exception){
		
		if(null!=exception ){
			if(exception instanceof GenericException){
				return error(((GenericException)exception).getCode());
			}
			
			if(exception instanceof InvocationTargetException ){
				Throwable throwable=((InvocationTargetException) exception).getTargetException();
				if(throwable instanceof GenericException){
					return error(((GenericException)throwable).getCode());
				}
			}
			
		}
		return error(UNKNOW_ERROR_CODE);
	}
	
	
	
	/**
	 * 接收失败的消息,且没有返回数据
	 * @param code
	 * @return
	 */
	public static PageResultData error(String code,PageResultData data,String[] placeholders){
		
		if(null!=data){
			data.setResultCode(code);
			data.setResultMsg(MessageUtils.getMessage(code,placeholders));
		}
		
		
		return data;
	}
	
	
	
	
}
