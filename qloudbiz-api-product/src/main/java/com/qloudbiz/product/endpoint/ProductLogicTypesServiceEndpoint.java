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
 * 产品分类逻辑服务
 * @author LCY
 *
 */
@RequestMapping("/products")
public class ProductLogicTypesServiceEndpoint {
	
	
	private final static Logger logger = LoggerFactory.getLogger(ProductLogicTypesServiceEndpoint.class);
	
	private final static String PATH_ADD_LOGIC_TYPES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-logic-type-create.json";
	
	private final static String PATH_QUERY_LOGIC_TYPES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-logic-type-query.json";
	
	private final static String PATH_QUERY_LOGIC_TYPES_TREE_JSON = "com/qloudfin/qloudbiz/apidef/products/product-logic-type-query-tree.json";
	
	private final static String PATH_QUERY_LOGIC_TYPES_INF_JSON = "com/qloudfin/qloudbiz/apidef/products/product-logic-type-query-inf.json";
	
	private final static String PATH_UPDATE_OR_DELETE = "com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";
	
	
	/**
	 * 添加产品逻辑分类
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/logicTypes",method=RequestMethod.POST)
	public void addLogicTypes(Callback<Object> callback,Map<String,String> body) {
		
		//日子调试
		logger.debug("========Add logicTypes parm is:{}", body);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_ADD_LOGIC_TYPES_JSON);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
	}
	
	
	
	/**
	 * 修改产品逻辑分类
	 * @param callback
	 * @param typeId
	 * @param body
	 */
	@RequestMapping(value = "/logicTypes/{typeId}", method=RequestMethod.PUT)
	public void updateLogicTypes(Callback<Object> callback,@PathVariable("typeId") String typeId, Map<String,String> body) {
		
		
		//调试日志
		logger.debug("=======Update logicTypes : typeId is :{},param is {}", typeId, body);
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);
        
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
		
	}
	
	
	
	/**
	 * 删除产品逻辑分类
	 * @param callback
	 * @param typeId
	 */
	@RequestMapping(value="/logicTypes/{typeId}",method=RequestMethod.DELETE)
	public void deleteLogicTypes(Callback<Object> callback, @PathVariable("typeId") String typeId) {
		
		
		        //调试日志
				logger.debug("=======Update logicTypes : typeId is :{},param is {}", typeId);
				
				//读取json数据
				String content=FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);
				
		        Object resultObj = JSON.parse(content);
				
				callback.accept(resultObj);
	}
	

	
	/**
	 * 查询产品逻辑分类列表
	 * @param callback
	 */
	@RequestMapping(value="/logicTypes/rootNode", method=RequestMethod.GET)
	public void queryLogicTypes(Callback<Object> callback) {
		
		
		//调试日志
		logger.debug("Query logicTypes");
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_QUERY_LOGIC_TYPES_JSON);
		
		Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
		
	}
	
	
	
	
	/**
	 * 查询产品逻辑分类树
	 * @param callback
	 * @param logicTypeId
	 */
	@RequestMapping(value = "/logicTypes/rootNode/{logicTypeId}", method=RequestMethod.GET)
	public void queryLogicTypesTree(Callback<Object> callback,@PathVariable("logicTypeId")String logicTypeId) {
		
		
		//调试日志
		logger.debug("Query logicTypesTree");
		
		//读取json数据
		String content=FileUtils.getResourceContent(PATH_QUERY_LOGIC_TYPES_TREE_JSON);
		
	    Object resultObj = JSON.parse(content);
		
		callback.accept(resultObj);
		
	
		
	}
	
	
	
	/**
	 * 查询产品逻辑分类详情
	 * @param callback
	 * @param logicTypeId
	 */
	@RequestMapping(value="/logicTypes/{logicTypeId}", method=RequestMethod.GET)
	public void queryLogicTypesINF(Callback<Object> callback,@PathVariable("logicTypeId")String logicTypeId) {
		
		
		        //调试日志
				logger.debug("Query logicTypesINF");
				
				//读取json数据
				String content=FileUtils.getResourceContent(PATH_QUERY_LOGIC_TYPES_INF_JSON);
			
				Object resultObj = JSON.parse(content);
				
				callback.accept(resultObj);
	}
	
	
}
