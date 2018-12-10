package com.csft.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties资源文件工具类
 * @author Administrator
 *
 */
public class PropertiesUtils {
	
	/**
	 * 获取资源问家n
	 * @param fullpath
	 * @return
	 */
	public static Properties getProperties(String fullpath){
		Properties properties=new Properties();
		InputStream in=ClassLoader.getSystemResourceAsStream(fullpath);
		try {
			properties.load(in);
		
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	
		return properties;
	}
	
	public static String getProperty(Properties properties,String key){
		String value=null;
		try {
			if(null!=properties && null!=key && !key.isEmpty()){
				value=properties.getProperty(key);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static String getProperties(Properties properties,String key,String ... params){
		
		String value=null;
		try {
			value = getProperty(properties,key);
			
			if(null!=value && !value.isEmpty() && null!=params && params.length>0){
				for(int i=0;i<params.length;i++){
					value=value.replace("{"+i+"}", params[i]);
				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
}
