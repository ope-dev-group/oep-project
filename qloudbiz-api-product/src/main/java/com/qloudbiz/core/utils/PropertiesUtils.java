package com.qloudbiz.core.utils;

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
}
