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
 * 产品属性组管理服务
 * @author LCY
 *
 */
@RequestMapping("/products")
public class ProductAttributeGroupsServiceEndpoint {
	
	
	private final static String PATH_ADD_ATTRIBUTE_GROUP_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-group-create.json";
	
	private final static String PATH_QUERY_ATTRIBUTE_GROUP_INF_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-group-query-inf.json";
	
	private final static String PATH_UPDATE_OR_DELETE = "com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";

	private final static Logger logger = LoggerFactory.getLogger(ProductAttributeGroupsServiceEndpoint.class);
	
	
	
	/**
	 * 添加产品属性组
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/attribute/groups", method=RequestMethod.POST)
	public void addAttributeGroups(Callback<Object> callback, Map<String,String> body) {
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add attributeGroups param is:{}",body);
		
		//读取数据
		String content = FileUtils.getResourceContent(PATH_ADD_ATTRIBUTE_GROUP_JSON);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);	
	}
	
	
	
	
	/**
	 * 修改产品属性组
	 * @param callback
	 * @param groupId
	 * @param body
	 */
	@RequestMapping(value = "/attributegroups/{groupId}", method=RequestMethod.PUT)
	public void updateAttributeGroups(Callback<Object> callback, @PathVariable("groupId")String groupId, Map<String,String> body) {
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Update attributeGroups param is:{}",body);
		
		//读取数据
		String content = FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
	}
	
	
	
	
	
	/**
	 * 删除产品属性组
	 * @param callback
	 * @param groupId
	 * @param body
	 */
	@RequestMapping(value = "/attributegroups/{groupId}", method=RequestMethod.DELETE)
	public void deleteAttributeGroups(Callback<Object> callback, @PathVariable("groupId")String groupId, Map<String,String> body) {
		
		//调试日志
		logger.debug("Delete attributeGroups : groupId is :{}", groupId);
		
		//读取数据
		String content = FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
	}
	
	
	
	
	/**
	 * 查询产品属性组详情
	 * @param callback
	 * @param groupId        
	 */             
	@RequestMapping(value = "/attributegroups/{groupId}", method=RequestMethod.GET)
	public void queryAttributeGroupsINF(Callback<Object> callback, @PathVariable("groupId") String groupId) {
		
		//调试日志
		logger.debug("Query attributeGroups info");
		
		//读取数据
		String content = FileUtils.getResourceContent(PATH_QUERY_ATTRIBUTE_GROUP_INF_JSON);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);	
	}
	
	
	
}
