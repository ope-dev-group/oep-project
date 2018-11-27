package com.qloudbiz.product.endpoint;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;

/**
 * 产品属性管理服务
 * @author LCY
 *
 */
@RequestMapping("/products")
public class ProductAttributesServiceEndpoint {
	
	private final static String PATH_ADD_ATTRIBUTES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-create.json";
	
	private final static String PATH_QUERY_ATTRIBUTES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-query.json";
	
	private final static String PATH_QUERY_ATTRIBUTES_INF_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-query-inf.json";
	
	private final static String PATH_UPDATE_OR_DELETE = "com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";
	
	private final static Logger logger = LoggerFactory.getLogger(ProductAttributesServiceEndpoint.class);
	
	
	
	/**
	 * 添加产品属性
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value="/attributes", method=RequestMethod.POST)
	public void addAttributes(Callback<Object> callback, Map<String,String> body) {
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add attributes param is:{}",body);
		
		//读取json数据
		String content = FileUtils.getResourceContent(PATH_ADD_ATTRIBUTES_JSON);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
	}
	
	
	
	
	/**
	 * 修改产品属性
	 * @param callback
	 * @param attributeId
	 * @param body
	 */
	@RequestMapping(value = "/attributes/{attributeId}", method=RequestMethod.PUT)
	public void updateAttributes(Callback<Object> callback,@PathVariable("attributeId") String attributeId, Map<String,String> body) {
		
		//调试日志
		logger.debug("=======Update attributes : attributeId is :{},param is {}", attributeId, body);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
	}
	
	
	
	
	/**
	 * 删除产品属性
	 * @param callback
	 * @param attributeId
	 */
	@RequestMapping(value = "/attributes/{attributeId}", method=RequestMethod.DELETE)
	public void deleteAttributes(Callback<Object> callback, @PathVariable("attributeId") String attributeId) {
		
		//调试日志
		logger.debug("=======Delete attributes : attributeId is :{}", attributeId);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
	}
	
	
	
	
	/**
	 * 查询产品属性列表
	 * @param callback
	 */
	@RequestMapping(value = "/attributes", method=RequestMethod.GET)
	public void queryAttributes(Callback<Object> callback) {
		
		//调试日志
		logger.debug("Query attributes");
		
		//读取json数据
		String content = FileUtils.getResourceContent(PATH_QUERY_ATTRIBUTES_JSON);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);	
	}
	
	
	
	
	/**
	 * 查询产品属性详情
	 * @param callback
	 * @param attributeId
	 */
	@RequestMapping(value="/attributes/{attributeId}", method=RequestMethod.GET)
	public void queryAttributesINF(Callback<Object> callback, @PathVariable("attributeId")String attributeId) {
		
		//调试日志
		logger.debug("Query attributesINF");
		
		//读取json数据
		String content = FileUtils.getResourceContent(PATH_QUERY_ATTRIBUTES_INF_JSON);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
	}
	
}
