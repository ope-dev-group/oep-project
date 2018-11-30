package com.qloudbiz.core.utils;

import io.netty.util.internal.StringUtil;

import java.util.Properties;

import com.qloudbiz.core.enums.MessageLangEnum;

public class MessageUtils {
	
	
	private final static String ZH_MSG_PATH="com/qloudfin/qloudbiz/message/zh_message.properties";
	
	private final static String EN_MSG_PATH="com/qloudfin/qloudbiz/message/en_message.properties";

	
	//中文资源文件
	private final static Properties zh_messageProperties=PropertiesUtils.getProperties(ZH_MSG_PATH);
	
	//英文资源文件
	private final static Properties en_messageProperties=PropertiesUtils.getProperties(EN_MSG_PATH);

	
	//根据消息的key获取消息对象的内容
	public static String getMessage(String code){
		if(StringUtil.isNullOrEmpty(code))return null;
		
		return zh_messageProperties.getProperty(code);
	}
	
	
	//根据消息的key获取消息对象的内容
	/**
	 * 
	 * @param code      消息编码
	 * @param lang  语言
	 * @return
	 */
	public static String getMessage(String code,MessageLangEnum lang){
		if(StringUtil.isNullOrEmpty(code))return null;
		
		if(null!=lang && lang.getCode().equals("ZH")){
			return zh_messageProperties.getProperty(code);
		}
		if(null!=lang && lang.getCode().equals("EN")){
			return en_messageProperties.getProperty(code);
		}
		
		return zh_messageProperties.getProperty(code);
	}
	
	
	    //根据消息的key获取消息对象的内容
		public static String getMessage(String code,String... placeholders){
			if(StringUtil.isNullOrEmpty(code))return null;
			
			return PropertiesUtils.getProperties(zh_messageProperties, code, placeholders);
		}
		
		
		//根据消息的key获取消息对象的内容
		/**
		 * 
		 * @param code      消息编码
		 * @param lang  语言
		 * @return
		 */
		public static String getMessage(String code,MessageLangEnum lang,String...placeholders){
			if(StringUtil.isNullOrEmpty(code))return null;
			
			if(null!=lang && lang.getCode().equals(MessageLangEnum.MSG_LAGN_ZH.getCode())){
				return PropertiesUtils.getProperties(zh_messageProperties, code, placeholders);
			}
			if(null!=lang && lang.getCode().equals(MessageLangEnum.Msg_LANGE_EN.getCode())){
				return PropertiesUtils.getProperties(en_messageProperties, code, placeholders);
			}
			
			return PropertiesUtils.getProperties(zh_messageProperties, code, placeholders);
		}
		
		
		
}
