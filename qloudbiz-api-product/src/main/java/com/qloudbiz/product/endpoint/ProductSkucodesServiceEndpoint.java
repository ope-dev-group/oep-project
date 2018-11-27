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
 * 产品skuCode微服务
 *主物料加上一组SKU属性值，构成该产品的最小计算单位，产品的库存、销售计算的最小单位，产品的SKU属性往往决定产品的价格、影响产品的销售（例如颜色）。
 *产品SKU编码管理提供对于产品SKU编码进行增查改删的操作。
 *
 */
@RequestMapping("/products")
public class ProductSkucodesServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductSkucodesServiceEndpoint.class);
		  
	private final static String PATH_ADD_SKU_JSON="com/qloudfin/qloudbiz/apidef/products/skucode-create.json";//添加品牌json
	
	private final static String PATH_UPDATE_DELETE="com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";//新增或者修改json
	private final static String PATH_LIST_SKU_JSON="com/qloudfin/qloudbiz/apidef/products/skucode-list.json";//添加品牌json

	private final static String PATH_QUERY_SKU_DETAIL_JSON="com/qloudfin/qloudbiz/apidef/products/skucode-detail.json";//添加品牌json

	
	/**
	 * 添加产品skuCode
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes",method=RequestMethod.POST)
	public void addSkucodes(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add Product Skucodes param is:{}",body);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_ADD_SKU_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 修改产品Skucodes
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes/{skuId}",method=RequestMethod.PUT)
	public void updateSkucodes(Callback<Object> callback,@PathVariable("skuId") String skuId,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>Update skucodes :skuId is:{},param is {}",skuId,body);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	/**
	 * 删除产品Skucode
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes/{skuId}",method=RequestMethod.DELETE)
	public void deleteSkucode(Callback<Object> callback,@PathVariable("skuId") String skuId){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>>>Delete skuCode :skuId is:{}",skuId);
		
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	
	/**
	 * 查询产品skucode列表
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes",method=RequestMethod.GET)
	public void querySkucodes(Callback<Object> callback,@RequestParam("typeId")String typeId,@RequestParam("productCode") String  productCode,@RequestParam("skuName")String skuName,@RequestParam("status")String status){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>Query  Product Skucodes list the typeId is {},the productCode is {},the skuName is {},the status is {}",typeId,productCode,skuName,status);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_LIST_SKU_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 查询产品skucode详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes/{skuId}",method=RequestMethod.GET)
	public void querySkucode(Callback<Object> callback,@PathVariable("skuId")String skuCode){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>Query  Product sku detail,skuCode is {}",skuCode);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_QUERY_SKU_DETAIL_JSON);
				
		//业务处理
		Object resultObj=JSON.parse(content);
	
		
		callback.accept(resultObj);	
	}
}
