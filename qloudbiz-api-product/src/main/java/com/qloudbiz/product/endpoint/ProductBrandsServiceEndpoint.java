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
public class ProductBrandsServiceEndpoint {
	private final static String PATH_ADD_BRANDS_JSON="com/qloudfin/qloudbiz/apidef/products/productbrands-create.json";//添加品牌json
	
	private final static String PATH_UPDATE_DELETE="com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";//新增或者修改json
	private final static String PATH_LIST_BRANDS_JSON="com/qloudfin/qloudbiz/apidef/products/productbrands-list.json";//添加品牌json

	private final static String PATH_QUERY_BRANDS_DETAIL_JSON="com/qloudfin/qloudbiz/apidef/products/productbrands-detail.json";//添加品牌json

	
	
	private final static Logger logger=LoggerFactory.getLogger(ProductBrandsServiceEndpoint.class);
	
	/**
	 * 添加品牌接口
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/brands",method=RequestMethod.POST)
	public void addBrands(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add brands param is:{}",body);
		
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_ADD_BRANDS_JSON);
		
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 修改、品牌
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/brands/{brandId}",method=RequestMethod.PUT)
	public void updateBrand(Callback<Object> callback,@PathVariable("brandId") String brandId,Map<String,String> body){
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Update brands param is:{}",body);

		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
		
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	/**
	 * 删除品牌
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/brands/{brandId}",method=RequestMethod.DELETE)
	public void deleteBrand(Callback<Object> callback,@PathVariable("brandId") String brandId){
		logger.debug(">>>>>>>>>>>>>Delete brands brandId is:{}");

		//调试日志
		  
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
		
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 查询品牌列表
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/brands",method=RequestMethod.GET)
	public void queryBrands(Callback<Object> callback, @RequestParam("brandCode" )String brandCode,@RequestParam("brandName" )String brandName,@RequestParam("brandType" )String brandType,@RequestParam("status" )String status){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Query brands list  ,the brandCode is {},brandName is {},brandType is {},status is {}",brandCode,brandName,brandType,status);

		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_LIST_BRANDS_JSON);
		
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 查询品牌详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/brands/{brandId}",method=RequestMethod.GET)
	public void queryBrandDetail(Callback<Object> callback,@PathVariable("brandId" )String brandId){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Query brands Detail ,the brandId is {},",brandId);

		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_QUERY_BRANDS_DETAIL_JSON);
		
		Object resultObj=JSON.parse(content);
	
		callback.accept(resultObj);	
	}
}
