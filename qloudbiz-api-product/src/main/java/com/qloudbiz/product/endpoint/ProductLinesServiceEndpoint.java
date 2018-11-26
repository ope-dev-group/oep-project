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
	
	private final static String PATH_UPDATE_DELETE="com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";//新增或者修改json
	private final static String PATH_LIST_LINES_JSON="com/qloudfin/qloudbiz/apidef/products/productlines-list.json";//添加品牌json
	private final static String PATH_QUERY_LINES_DETAIL_JSON="com/qloudfin/qloudbiz/apidef/products/productlines-detail.json";//添加品牌json

	
	
	
	/**
	 * 新增产品线
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
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_ADD_LINES_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
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
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);		
	}
	
	/**
	 * 查询产品线列表
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines",method=RequestMethod.GET)
	public void queryLines(Callback<Object> callback){
		
		
		//调试日志
		logger.debug("Query lines");
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_LIST_LINES_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 查询产品线详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines/{lineId}",method=RequestMethod.GET)
	public void queryLinesDetail(Callback<Object> callback,@PathVariable("lineId")String lineId){
		
		
		//调试日志
		logger.debug("Query lines");
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_QUERY_LINES_DETAIL_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
}
