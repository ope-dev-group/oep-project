package com.qloudbiz.product.endpoint;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;


/**
 * 产品线服务
 *
 * @author Kezx
 *
 */
@RequestMapping("/products")
public class ProductLinesServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductLinesServiceEndpoint.class);
	
	private final static String PATH_ADD_LINES_JSON="com/qloudfin/qloudbiz/apidef/products/productlines-create.json";	
	
	
	
	/**
	 * 添加产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines",method=RequestMethod.POST)
	public void addLines(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add lines param is:{}",body);
	
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_ADD_LINES_JSON);
		
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 修改产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines/{lineId}",method=RequestMethod.PUT)
	public void updateLines(Callback<Object> callback,@PathVariable("lineId") String lineId,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>Update lines :lineId is:{},param is {}",lineId,body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/productlines-create.json");		
		
		
		
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
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//业务处理
		sb.toString().replaceAll("\t","");
		Object resultObj=JSON.parse(sb.toString());
	
		
		callback.accept(null);	
	}
	
	/**
	 * 删除产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines/{lineId}",method=RequestMethod.DELETE)
	public void deleteLines(Callback<Object> callback,@PathVariable("lineId") String lineId){
		
		
		//调试日志
		logger.debug("Delete lines :lineId is:{}",lineId);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/productlines-create.json");		
		
		
		
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
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//业务处理
		sb.toString().replaceAll("\t","");
		Object resultObj=JSON.parse(sb.toString());
	
		
		callback.accept(null);	
	}
	
	/**
	 * 查询产品线信息
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines",method=RequestMethod.GET)
	public void queryLines(Callback<Object> callback){
		
		
		//调试日志
		logger.debug("Query lines");
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/productlines-create.json");		
		
		
		
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
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//业务处理
		sb.toString().replaceAll("\t","");
		Object resultObj=JSON.parse(sb.toString());
	
		
		callback.accept(resultObj);	
	}
}
