package com.qloudbiz.product.endpoint;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.pojo.ProductLogicType;
import com.qloudbiz.product.pojo.ProductType;
import com.qloudbiz.product.service.ProductLogicTypeService;
import com.qloudbiz.product.service.impl.ProductLineServiceImpl;
import com.qloudbiz.product.service.impl.ProductLogicTypeServiceImpl;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudbiz.product.vo.ProductLogicTypeVO;
import com.qloudbiz.product.vo.ProductTypeVO;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.security.util.StringUtils;


/**
 * 产品逻辑分类管理服务
 * @author LCY
 *
 */
@RequestMapping("/products")
public class ProductLogicTypesServiceEndpoint {
		
	private final static Logger logger = LoggerFactory.getLogger(ProductLogicTypesServiceEndpoint.class);
	
	private ProductLogicTypeService service = (ProductLogicTypeService) ServiceProxyFactory.createProxy(ProductLogicTypeServiceImpl.class);
	
	/**
	 * 添加产品逻辑分类
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/logictypes", method=RequestMethod.POST)
	public void addLogicTypes(Callback<Object> callback, ProductLogicTypeVO vo) {
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add logictypes param is:{}",vo);
		
		try {
			if(null ==vo) {
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			if(StringUtils.isEmpty(vo.getTypeCode())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"typeCode"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getTypeName())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"typeName"}));
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
			//调用service
			service.add(productLogicType -> {
				String jsonStr = JSON.toJSONString(ResultDataUtils.success(productLogicType),new SimplePropertyPreFilter(ProductLogicType.class,"typeId","typeCode"));
				callback.accept(JSON.parse(jsonStr));
			}, vo);
		} catch(Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
		
		
		
	}
	
	
	
	/**
	 * 修改产品逻辑分类
	 * @param callback
	 * @param typeId
	 * @param body
	 */
	@RequestMapping(value = "/logictypes/{typeId}", method=RequestMethod.PUT)
	public void updateLogicTypes(Callback<Object> callback, @PathVariable("typeId")String typeId, ProductLogicTypeVO vo) {
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Update logictypes param is:{}",vo);
		
		try {
			if (null == vo) {
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			if (StringUtils.isEmpty(typeId)) {
				callback.accept(ResultDataUtils.error("401",new String[]{"typeId"}));
				return;
			}
			
			vo.setTypeId(typeId);
			
			if (StringUtils.isEmpty(vo.getTypeName())) {
				callback.accept(ResultDataUtils.error("401",new String[]{"typeName"}));
				return;
			}
			if (StringUtils.isEmpty(vo.getTypeCode())) {
				callback.accept(ResultDataUtils.error("401",new String[]{"typeCode"}));
				return;
			}
			if (StringUtils.isEmpty(vo.getSort())) {
				callback.accept(ResultDataUtils.error("401",new String[]{"sort"}));
				return;
			}
			if (StringUtils.isEmpty(vo.getStatus())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"status"}));
				return;
			}
			//状态验证
			if(!("Y".equals(vo.getStatus()) || "N".equals(vo.getStatus()))){
				callback.accept(ResultDataUtils.error("410",new String[]{"status"}));
				return;
			}
			//调用service
			service.modify(rownum -> {
				if (null != rownum && rownum.intValue()==1) {
					callback.accept(ResultDataUtils.success());
				}else {
					callback.accept(ResultDataUtils.error("406"));
					}
				},vo);	
			} catch (Exception e) {
				logger.error("the exception is {}",e);
				callback.accept(ResultDataUtils.error(e));
			}
	}
	
	
	
	/**
	 * 删除产品逻辑分类
	 * @param callback
	 * @param typeId
	 */
	@RequestMapping(value = "/logictypes/{typeId}", method=RequestMethod.DELETE)
	public void deleteLogicTypes(Callback<Object> callback, @PathVariable("typeId") String typeId) {
		
		//调试日志
		logger.debug("=======Delete LogicTypes : typeId is :{}", typeId);
				
		try {
			//请求参数验证
			if(StringUtils.isEmpty(typeId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"typeId"}));
				return;
			}
			ProductLogicTypeVO vo=new ProductLogicTypeVO();
			vo.setTypeId(typeId);
			//调用delete方法
			service.remote(rownum->{				
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
	 * 查询产品逻辑分类列表
	 * @param callback
	 */
	@RequestMapping(value = "/logictypes/rootnode")
	public void queryLogicTypes(Callback<Object> callback,
			@RequestParam("currentNum")int currentNum,
			@RequestParam("pagePerNum")int pagePerNum,
			@RequestParam("logicTypeCode")String logicTypeCode,
			@RequestParam("logicTypeName")String logicTypeName,
			@RequestParam("status")String status,
			@RequestParam("lineId")String lineId) {
		
		//调试日志
		logger.debug("Query LogicTypes list, logicTypeCode is {}, logicTypeName is {}, status is {}, lineId is {}",logicTypeCode,logicTypeName,status,lineId);
try {
			
			if(currentNum <= 0) {
				callback.accept(ResultDataUtils.error("401",new String[]{"currentNum"}));
				return;
			}
			if(pagePerNum <= 0) {
				callback.accept(ResultDataUtils.error("401",new String[]{"pagePerNum"}));
				return;
			}
			
			//参数设置
			ProductLogicTypeVO vo = new ProductLogicTypeVO(); 
			vo.setCurrentNum(currentNum);
			vo.setPagePerNum(pagePerNum);
			vo.setTypeCode(logicTypeCode);
			vo.setTypeName(logicTypeName);
			vo.setProdLineId(lineId);
			
			service.seachList(page -> {
				if(null!=page) {
					callback.accept(ResultDataUtils.success(page));
				} else {
					callback.accept(ResultDataUtils.error("409"));
				}
			},vo);
			
			
		} catch(Exception e) {
			logger.error(">>>>>>>>>>query productLogicType listall exception ");
			callback.accept(ResultDataUtils.error(e));
		}
		
	}
	
	
	
	/**
	 * 查询产品逻辑分类树
	 * @param callback
	 * @param logicTypeId
	 */
	@RequestMapping(value = "/logictypes/rootnode/{logicTypeId}")
	public void queryLogicTypesTree(Callback<Object> callback, 
			@PathVariable("logicTypeId")String logicTypeId, 
			@RequestParam("logicTypeName")String logicTypeName) {
		
		//调试日志
		logger.debug("Query LogicTypesTree , logicTypeId is :{}, logicTypeName is {}",logicTypeId,logicTypeName);
		try {
	       	ProductLogicTypeVO vo=new ProductLogicTypeVO();

				//调用分页查询方法
				service.seachTree(result->{
					if(null!=result){					
						callback.accept(ResultDataUtils.success(result));
					}else{
						callback.accept(ResultDataUtils.error("409"));
					}
				},vo);
				
			} catch (Exception e) {
				logger.error(">>>>>>>>>>query exception ");
				callback.accept(ResultDataUtils.error(e));
			}
		
	}
	
	
	
	/**
	 * 
	 * @param callback
	 * @param logicTypeId
	 */
	@RequestMapping(value = "/logictypes/{logicTypeId}")
	public void queryLogicTypesINF(Callback<Object> callback, @PathVariable("logicTypeId")String logicTypeId) {
		
		//调试日志
		logger.debug("Query LogicTypesINF , logicTypeId is :{}",logicTypeId);
try {
			
			//验证参数
			if(StringUtils.isEmpty(logicTypeId)) {
				callback.accept(ResultDataUtils.error("402",new String[]{"lineId"}));
				return;
			}
			//设置参数
			ProductLogicTypeVO vo = new ProductLogicTypeVO();
			vo.setTypeId(logicTypeId);
			//调用查询详情
			service.seachById(result->{
				if (null != result) {
                   String jsonStr=JSON.toJSONString(ResultDataUtils.success(result));
					
					callback.accept(JSON.parse(jsonStr));
				} else {
					callback.accept(ResultDataUtils.error("404"));	
				}
			}, vo);
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
		
		
	}
	
	
}
