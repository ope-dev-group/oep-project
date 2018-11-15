package com.csft.product.sdk;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;


/**
 * 产品分类微服务
 *产品类型包括物理类型和逻辑类型两种，一个产品必须属于某一个物理分类，产品可以属于多个逻辑分类。逻辑分类犹如tag，一个产品可以打上多个tag。产品分类可以建立树状结构的分类，分类数据可以自相关。某一类产品，例如电子书等不需要运输，其他的都需要运输。
 *产品类型API提供对于产品类型的增查改删的功能接口。
 * @author Kezx
 *
 */
@RequestMapping("/products")
public class ProductTypesServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductTypesServiceEndpoint.class);
	
	/**
	 * 添加产品分类
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types",method=RequestMethod.POST)
	public void addTypes(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add types param is:{}",body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-type-create.json");		
		
		
		
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
			e.printStackTrace();
		}
		
		//业务处理
		sb.toString().replaceAll("\t","");
		Object resultObj=JSON.parse(sb.toString());
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 修改产品分类
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types/{typeId}",method=RequestMethod.PUT)
	public void updateTypes(Callback<Object> callback,@PathVariable("typeId") String typeId,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>Update types :typeId is:{},param is {}",typeId,body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-type-create.json");		
		
		
		
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
	 * 删除产品分类
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types/{typeId}",method=RequestMethod.DELETE)
	public void deleteType(Callback<Object> callback,@PathVariable("typeId") String typeId){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>>>Delete types :typeId is:{}",typeId);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-type-create.json");		
		
		
		
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
	 * 查询产品分类
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types",method=RequestMethod.GET)
	public void queryLines(Callback<Object> callback){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>Query Types List");
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-type-create.json");		
		
		
		
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
