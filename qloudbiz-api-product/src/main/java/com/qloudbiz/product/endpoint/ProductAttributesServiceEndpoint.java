package com.qloudbiz.product.endpoint;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.service.AttributeService;
import com.qloudbiz.product.service.impl.AttributeServiceImpl;
import com.qloudbiz.product.vo.AttributeVO;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.security.util.StringUtils;

/**
 * 产品属性管理服务
 * @author LCY
 *
 */
@RequestMapping("/products")
public class ProductAttributesServiceEndpoint {
	
//	private final static String PATH_ADD_ATTRIBUTES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-create.json";
//	
//	private final static String PATH_QUERY_ATTRIBUTES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-query.json";
//	
//	private final static String PATH_QUERY_ATTRIBUTES_INF_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-query-inf.json";
//	
//	private final static String PATH_UPDATE_OR_DELETE = "com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";
	
	private final static Logger logger = LoggerFactory.getLogger(ProductAttributesServiceEndpoint.class);
	
	private AttributeService service = ServiceProxyFactory.createProxy(AttributeServiceImpl.class);
	
	
	/**
	 * 添加产品属性
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value="/attributes", method=RequestMethod.POST)
	public void addAttributes(Callback<Object> callback, AttributeVO vo) {
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add attributes param is:{}",vo);
		
		try {
			//请求参数验证
			if(null ==vo) {
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			if(StringUtils.isEmpty(vo.getAttributeCode())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"AttributeCode"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getAttributeName())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"AttributeName"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getProdTypeId())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"ProdTypeId"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getAttributeType())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"AttributeType"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getAttributeContraint())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"AttributeContraint"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getSort())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"sort"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getStatus())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"status"}));
				return;
			}
			//状态验证
			if(!("Y".equals(vo.getStatus()) || "N".equals(vo.getStatus()))){
				callback.accept(ResultDataUtils.error("410",new String[]{"status"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getAttributeGroupId())) {
				callback.accept(ResultDataUtils.error("410",new String[]{"AttributeGroupId"}));
				return;
			}
			//调用service
//			service.
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
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
		
//		//读取json数据
//		String content=FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);
//		
//		Object resultObj = JSON.parse(content);
//		
//		callback.accept(resultObj);
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
//		
//		//读取json数据
//		String content=FileUtils.getResourceContent(PATH_UPDATE_OR_DELETE);
//		
//		Object resultObj = JSON.parse(content);
//		
//		callback.accept(resultObj);
	}
	
	
	
	
	/**
	 * 查询产品属性列表
	 * @param callback
	 */
	@RequestMapping(value = "/attributes", method=RequestMethod.GET)
	public void queryAttributes(Callback<Object> callback,
			@RequestParam("productTypeId")String productTypeId, 
			@RequestParam("attributeCode")String attributeCode,
			@RequestParam("attributeName")String attributeName,
			@RequestParam("isRequired")String isRequired,
			@RequestParam("attributeType")String attributeType,
			@RequestParam("attributeGroupId")String attributeGroupId) {
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Query attributes list  ,the productTypeId is {},attributeCode is {},attributeName is {},isRequired is {}, attributeType is {}, attributeGroupId is {}",productTypeId,attributeCode,attributeName,isRequired,attributeType,attributeGroupId);

		//读取json数据
//		String content = FileUtils.getResourceContent(PATH_QUERY_ATTRIBUTES_JSON);
//		
//		Object resultObj = JSON.parse(content);
//		
//		callback.accept(resultObj);	
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
//		String content = FileUtils.getResourceContent(PATH_QUERY_ATTRIBUTES_INF_JSON);
//		
//		Object resultObj = JSON.parse(content);
//		
//		callback.accept(resultObj);
	}
	
}
