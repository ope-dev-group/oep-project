package com.qloudbiz.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import com.qloudbiz.core.exception.GenericException;
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
		resultData.setMsg(MessageUtils.getMessage(SUCCESS_CODE));
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
		resultData.setMsg(MessageUtils.getMessage(SUCCESS_CODE));
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
			data.setMsg(MessageUtils.getMessage(SUCCESS_CODE));
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
		resultData.setMsg(MessageUtils.getMessage(code));
		resultData.setResult(data);
		
		return resultData;
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
		resultData.setMsg(MessageUtils.getMessage(code, placeholders));
		resultData.setResult(data);
		
		return resultData;
	}
	
	
	/**
	 * 接收失败的消息,且没有返回数据
	 * @param code
	 * @return
	 */
	public static <T> BaseResult error(String code,String[] placeholders){
		ResultData<T> resultData=new ResultData<T>();

		resultData.setResultCode(code);
		resultData.setMsg(MessageUtils.getMessage(code,placeholders));
		
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
		resultData.setMsg(MessageUtils.getMessage(code));
		
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
			data.setMsg(MessageUtils.getMessage(code,placeholders));
		}
		
		
		return data;
	}
	
	
	
	
}
