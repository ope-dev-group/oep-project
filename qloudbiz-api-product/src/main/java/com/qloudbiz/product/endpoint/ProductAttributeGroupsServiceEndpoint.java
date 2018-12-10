package com.qloudbiz.product.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.pojo.AttributeGroup;
import com.qloudbiz.product.service.AttributeGroupService;
import com.qloudbiz.product.service.ProductTypeService;
import com.qloudbiz.product.service.impl.AttributeGroupServiceImpl;
import com.qloudbiz.product.service.impl.ProductTypeServiceImpl;
import com.qloudbiz.product.vo.AttributeGroupVO;
import com.qloudbiz.product.vo.ProductTypeVO;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.security.util.StringUtils;

/**
 * 产品属性组管理服务，提供属性组的增、删、改
 * @author LCY
 *
 */
@RequestMapping("/products")
public class ProductAttributeGroupsServiceEndpoint {
	
	
	private final static String PATH_ADD_ATTRIBUTE_GROUP_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-group-create.json";
	
	private final static String PATH_QUERY_ATTRIBUTE_GROUPS_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-group-query.json";
	
	private final static String PATH_QUERY_ATTRIBUTE_GROUP_INF_JSON = "com/qloudfin/qloudbiz/apidef/products/product-attributes-group-query-inf.json";
	
	private final static String PATH_UPDATE_OR_DELETE = "com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";

	private final static Logger logger = LoggerFactory.getLogger(ProductAttributeGroupsServiceEndpoint.class);
	
    AttributeGroupService service=ServiceProxyFactory.createProxy(AttributeGroupServiceImpl.class);

    
    ProductTypeService productTypeService=ServiceProxyFactory.createProxy(ProductTypeServiceImpl.class);

	
	/**
	 * 添加产品属性组
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/attributegroups", method=RequestMethod.POST)
	public void addAttributeGroups(Callback<Object> callback,AttributeGroupVO vo) {
		
		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add Attributegroups param is:{}", vo);

		 
		try {

			
			//请求参数非空验证
			if(null==vo){
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			
			
			
			//参数非空验证
			if(StringUtils.isEmpty(vo.getGroupName())){
				callback.accept(ResultDataUtils.error("401",new String[]{"groupName"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getProductTypeId())){
				callback.accept(ResultDataUtils.error("401",new String[]{"productTypeId"}));
				return;
			}
			
			if(StringUtils.isEmpty(vo.getSort())){
				callback.accept(ResultDataUtils.error("401",new String[]{"sort"}));
				return;
			}
			
			if(StringUtils.isEmpty(vo.getStatus())){
				callback.accept(ResultDataUtils.error("401",new String[]{"status"}));
				return;
			}
			
			
			//验证产品分类是否存在
			ProductTypeVO typevo=new ProductTypeVO();
			typevo.setTypeId(vo.getProductTypeId());
			productTypeService.seachById(productType->{
				if(null==productType){
					callback.accept(ResultDataUtils.error("411",new String[]{"productType"}));
					return;
				}
			}, typevo);
			
			
		
			//状态验证
			if(!("Y".equals(vo.getStatus()) || "N".equals(vo.getStatus()))){
				callback.accept(ResultDataUtils.error("410",new String[]{"status"}));
				return;
			}
			

			//调用Save
			service.save(attributeGroup->{
				
				String jsonStr=JSON.toJSONString(ResultDataUtils.success(attributeGroup),new SimplePropertyPreFilter(AttributeGroup.class,"groupId"));
				
				callback.accept(JSON.parse(jsonStr));
			},vo);
			
			
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	}
	
	
	
	
	/**
	 * 修改产品属性组
	 * @param callback
	 * @param groupId
	 * @param body
	 */
	@RequestMapping(value = "/attributegroups/{groupId}", method=RequestMethod.PUT)
	public void updateAttributeGroups(Callback<Object> callback, @PathVariable("groupId")String groupId, AttributeGroupVO vo) {
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Update attributeGroups the groupId is {} , param is:{}",groupId,vo);
		

		  
		try {
			

			//请求参数验证
			if(null==vo){
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			
			//请求参数验证
			if(StringUtils.isEmpty(groupId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"groupId"}));
				return;
			}
			
			
		    vo.setGroupId(groupId);

			if(StringUtils.isEmpty(vo.getGroupName())){
				callback.accept(ResultDataUtils.error("401",new String[]{"groupName"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getProductTypeId())){
				callback.accept(ResultDataUtils.error("401",new String[]{"productTypeId"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getSort())){
				callback.accept(ResultDataUtils.error("401",new String[]{"sort"}));
				return;
			}
			
			if(StringUtils.isEmpty(vo.getStatus())){
				callback.accept(ResultDataUtils.error("401",new String[]{"status"}));
				return;
			}
			
		

			//验证产品分类是否存在
			ProductTypeVO typevo=new ProductTypeVO();
			typevo.setTypeId(vo.getProductTypeId());
			productTypeService.seachById(productType->{
				if(null==productType){
					callback.accept(ResultDataUtils.error("411",new String[]{"productType"}));
					return;
				}
			}, typevo);
			
			//状态验证
			if(!("Y".equals(vo.getStatus()) || "N".equals(vo.getStatus()))){
				callback.accept(ResultDataUtils.error("410",new String[]{"status"}));
				return;
			}

		
			

			//调用update
			service.update(rownum->{				
				if(null!=rownum && rownum.intValue()==1){
					callback.accept(ResultDataUtils.success());
				}else{
					callback.accept(ResultDataUtils.error("406"));
				}
			},vo);
		}catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	
}
	
	
	
	
	/**
	 * 删除产品属性组
	 * @param callback
	 * @param groupId
	 * @param body
	 */
	@RequestMapping(value = "/attributegroups/{groupId}", method=RequestMethod.DELETE)
	public void deleteAttributeGroups(Callback<Object> callback, @PathVariable("groupId")String groupId) {
		
		//调试日志
		logger.debug("Delete attributeGroups : groupId is :{}", groupId);
		
		try {
			//请求参数验证
		
			if(StringUtils.isEmpty(groupId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"groupId"}));
				return;
			}
			
			
			AttributeGroupVO vo=new AttributeGroupVO();

			vo.setGroupId(groupId);
			
			
			//调用delete方法
			
			service.delete(rownum->{				
				
				if(null!=rownum && rownum.intValue()==1){
					callback.accept(ResultDataUtils.success());
				}else{
					callback.accept(ResultDataUtils.error("407"));
				}
				
			},vo);
			
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
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
		

		 // 调试日志
		logger.debug(">>>>>>>>>>>>>Query attributeGroup Detail ,the groupId is {},",groupId);

		try {
			

			//请求参数验证
			if(StringUtils.isEmpty(groupId)){
				callback.accept(ResultDataUtils.error("402",new String[]{"groupId"}));
				return;
			}
			
			
			//设置参数
			AttributeGroupVO vo=new AttributeGroupVO(); 
			vo.setGroupId(groupId);
			
			//调用查询详情方法
			service.queryDetail(attributeGroup->{
				if(null!=attributeGroup){
					String jsonStr=JSON.toJSONString(ResultDataUtils.success(attributeGroup));
					
					callback.accept(JSON.parse(jsonStr));
				}else{
					callback.accept(ResultDataUtils.error("404"));
				}
			}, vo);
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	}
	
	
	
}
