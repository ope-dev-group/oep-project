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
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.qloudbiz.core.dao.DbInit;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.pojo.ProductType;
import com.qloudbiz.product.service.ProductLineService;
import com.qloudbiz.product.service.ProductTypeService;
import com.qloudbiz.product.service.impl.ProductLineServiceImpl;
import com.qloudbiz.product.service.impl.ProductTypeServiceImpl;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudbiz.product.vo.ProductTypeVO;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.security.util.StringUtils;


/**
 * 产品分类微服务
 *产品类型包括物理类型和逻辑类型两种，一个产品必须属于某一个物理分类，产品可以属于多个逻辑分类。逻辑分类犹如tag，一个产品可以打上多个tag。产品分类可以建立树状结构的分类，分类数据可以自相关。某一类产品，例如电子书等不需要运输，其他的都需要运输。
 *产品类型API提供对于产品类型的增查改删的功能接口。
 * @author Kezx
 *
 */
@RequestMapping("/products")
public class ProductTypesServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductTypesServiceEndpoint.class);
	
	private static ProductTypeService service = ServiceProxyFactory.createProxy(ProductTypeServiceImpl.class);
	
//	private final static String PATH_ADD_TYPES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-type-create.json";
//	
//	private final static String PATH_QUERYINF_TYPES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-type-query-inf.json";
//	
//	private final static String PATH_QUERY_TYPES_JSON = "com/qloudfin/qloudbiz/apidef/products/product-type-query.json";
//	
//	private final static String PATH_UPDATE_OR_DELETE = "com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";
	
	
	/**
	 * 添加产品分类
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types",method=RequestMethod.POST)
	public void addTypes(Callback<Object> callback,ProductTypeVO vo){
		
		//调试日志
		logger.debug("Add types param is:{}",vo);
		
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
			service.add(productType -> {
				String jsonStr = JSON.toJSONString(ResultDataUtils.success(productType),new SimplePropertyPreFilter(ProductType.class,"typeId","typeCode"));
				callback.accept(JSON.parse(jsonStr));
			}, vo);
		} catch(Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	}
	
	
	/**
	 * 修改产品分类
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types/{typeId}",method=RequestMethod.PUT)
	public void updateTypes(Callback<Object> callback,@PathVariable("typeId") String typeId,ProductTypeVO vo){
		
		//调试日志
		logger.debug("Update types :typeId is:{},param is {}",typeId,vo);
		
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
	 * 删除产品分类
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types/{typeId}",method=RequestMethod.DELETE)
	public void deleteType(Callback<Object> callback,@PathVariable("typeId") String typeId){
		
		//调试日志
		logger.debug("Delete types :typeId is:{}",typeId);
		try {
			//请求参数验证
			if(StringUtils.isEmpty(typeId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"typeId"}));
				return;
			}
			ProductTypeVO vo=new ProductTypeVO();
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
	 * 获取产品分类树
	 * @param callback
	 */
	@RequestMapping(value = "/types", method=RequestMethod.GET)
	public void queryTypes(Callback<Object> callback,
			@RequestParam("typeName")String typeName) {
		
		//调试日志
		logger.debug("Query  Types Tree ,typeName is {}",typeName);
		
        try {
       	ProductTypeVO vo=new ProductTypeVO();

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
	 * 查询产品分类详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/types/{typeId}",method=RequestMethod.GET)
	public void queryTypesINF(Callback<Object> callback, @PathVariable("typeId") String typeId){
		
		//调试日志
		logger.debug("Query Types info, typeId is {}",typeId);	
	    try {
			//验证参数
			if(StringUtils.isEmpty(typeId)) {
				callback.accept(ResultDataUtils.error("402",new String[]{"typeId"}));
				return;
			}
			//设置参数
			ProductTypeVO vo = new ProductTypeVO();
			vo.setTypeId(typeId);
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
	
//	public static void main(String[] args) throws GenericException {
//		ProductTypeVO vo=new ProductTypeVO();
//		
//		String cfg = "{\"db\":{\n" + "       \"driver\":\"org.mariadb.jdbc.MariaDbDataSource\",\n"
//				+ "       \"ip\":\"192.168.44.5\",\n" + "       \"port\": 3306,\n" + "       \"dbname\":\"productdb\",\n"
//				+ "       \"username\":\"root\",\n" + "       \"password\":\"666666\",\n"
//				+ "       \"testQuery\":\"select 1\",\n" + "       \"maxPoolSize\": 5,\n"
//				+ "       \"minPoolSize\": 5\n" + "    } }";
//		System.setProperty("QLOUD_APPLICATION_CONFIG", cfg);
//		DbInit.init();
//		
//		//调用分页查询方法
//		service.seachTree(result->{
//			
//			if(null!=result){					
//				
//			}else{
//				
//			}
//		},vo);
//}
	
	
	
}
