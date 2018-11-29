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
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudbiz.product.service.ProductTestService;
import com.qloudbiz.product.service.impl.ProductTestServiceImpl;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;


/**
 * 主物料微服务
 *
 * @author Kezx
 *
 */
@RequestMapping("/products")
public class ProductMaterialsServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductMaterialsServiceEndpoint.class);
	  
	private final static String PATH_ADD_PRODUCT_JSON="com/qloudfin/qloudbiz/apidef/products/product-create.json";//添加品牌json
	
	private final static String PATH_UPDATE_DELETE="com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";//新增或者修改json
	private final static String PATH_LIST_PRODUCT_JSON="com/qloudfin/qloudbiz/apidef/products/product-list.json";//添加品牌json

	private final static String PATH_QUERY_PRODUCT_DETAIL_JSON="com/qloudfin/qloudbiz/apidef/products/product-detail.json";//添加品牌json

	
	/**
	 * 添加主物料
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials",method=RequestMethod.POST)
	public void addMaterials(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add materials param is:{}",body);
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_ADD_PRODUCT_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 修改主物料
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials/{lineId}",method=RequestMethod.PUT)
	public void updateMaterials(Callback<Object> callback,@PathVariable("lineId") String lineId,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>Update materials :materialId is:{},param is {}",lineId,body);
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	/**
	 * 删除主物料
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials/{materialId}",method=RequestMethod.DELETE)
	public void deleteMaterials(Callback<Object> callback,@PathVariable("materialId") String materialId){
		

		//调试日志
		logger.debug(">>>>>>>>>Update materials :materialId is:{},param is {}",materialId);
	
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	/**
	 * 查询主物料列表
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials",method=RequestMethod.GET)
	public void queryMaterials(Callback<Object> callback,@RequestParam("productCode")String productCode,@RequestParam("productName")String productName,@RequestParam("upcCode")String upcCode,@RequestParam("status")String status){
		
		
		//调试日志
		logger.debug("Query materials list ");

		//读取json数据
		String content=FileUtils.getResourceContent(PATH_LIST_PRODUCT_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 查询主物料详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials/{materialId}",method=RequestMethod.GET)
	public void queryMaterial(Callback<Object> callback,@PathVariable("materialId")String materialId){
		
		
		//调试日志
		logger.debug(">>>>>>>Query material Detail materialId is {}",materialId);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_QUERY_PRODUCT_DETAIL_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
}
