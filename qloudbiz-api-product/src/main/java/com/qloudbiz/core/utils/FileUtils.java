package com.qloudbiz.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * 文件工具类
 * @author Administrator
 *
 */
public class FileUtils {
	public static String getResourceContent(String filePath){
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream(filePath);		

		
		StringBuffer sb=new StringBuffer();
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(in,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader=new BufferedReader(isr);
		String  line=null;
		try {
			while((line=reader.readLine())!=null){
				sb.append(line);
				
			}
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(isr!=null){
				try {
					isr.close();
				} catch (IOException e) {
					if(isr!=null){
						isr=null;
					}
					
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					if(in!=null){
						in=null;
					}
				}
			}
		}
		
		//业务处理
		return sb.toString();
	
	}
}
