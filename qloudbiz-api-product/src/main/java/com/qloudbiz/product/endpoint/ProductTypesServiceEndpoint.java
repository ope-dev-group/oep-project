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
 * 产品分类微服务
 *产品类型包括物理类型和逻辑类型两种，一个产品必须属于某一个物理分类，产品可以属于多个逻辑分类。逻辑分类犹如tag，一个产品可以打上多个tag。产品分类可以建立树状结构的分类，分类数据可以自相关。某一类产品，例如电子书等不需要运输，其他的都需要运输。
 *产品类型API提供对于产品类型的增查改删的功能接口。
 * @author Kezx
 *
 */
@RequestMapping("/products")
public class ProductTypesServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductTypesServiceEndpoint.class);
	
	private final static String PATH_ADD_TYPE_JSON = "com/qloudfin/qloudbiz/apidef/products/product-type-create.json";
	
	private final static String PATH_QUERYINF_TYPE_JSON = "com/qloudfin/qloudbiz/apidef/products/product-type-query-inf.json";
	
	private final static String PATH_UPDATE_OR_DELETE = "com/qloudfin/qloudbiz/apidef/products/product-update_or_delete.json";
	
	
	
	
	/**
	 * 添加产品分类
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types",method=RequestMethod.POST)
	public void addTypes(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug("Add types param is:{}",body);
		
		//读取json数据
		String content = FileUtils.getResourceContent(PATH_ADD_TYPE_JSON);		
		
		
		//业务处理
		Object resultObj = JSON.parse(content);
		
		callback.accept(content);
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
		logger.debug("Update types :typeId is:{},param is {}",typeId,body);
		
		//读取json数据
		String content = FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);	
	
		
		//业务处理
		Object resultObj = JSON.parse(content);
		
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
		logger.debug("Delete types :typeId is:{}",typeId);
		
		//读取json数据
		String content = FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);	
		
		//业务处理
		Object resultObj = JSON.parse(content);
	
		
		callback.accept(null);	
	}
	
	/**
	 * 查询产品分类详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types/{typeId}",method=RequestMethod.GET)
	public void queryLinesINF(Callback<Object> callback, @PathVariable("typeId") String typeId){
		
		
		//调试日志
		logger.debug("Query Types List");
		
		//读取json数据
		String content = FileUtils.getResourceContent(PATH_QUERYINF_TYPE_JSON);	
		
		//业务处理
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);	
	}
}
