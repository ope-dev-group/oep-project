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
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.pojo.ProductSku;
import com.qloudbiz.product.pojo.ProductSkuAttribute;
import com.qloudbiz.product.service.ProductBrandService;
import com.qloudbiz.product.service.ProductSkuService;
import com.qloudbiz.product.service.impl.ProductBrandServiceImpl;
import com.qloudbiz.product.service.impl.ProductSkuServiceImpl;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductSkuVO;
import com.qloudbiz.product.vo.ProductSkusVO;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.security.util.StringUtils;


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
    ProductSkuService service=ServiceProxyFactory.createProxy(ProductSkuServiceImpl.class);

    
	
	/**
	 * 添加产品skuCode
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes",method=RequestMethod.POST)
	public void addSkucodes(Callback<Object> callback,ProductSkusVO vo){
		try{
		
			//调试日志
			logger.debug(">>>>>>>>>>>>>Add Product Skucodes param is:{}",vo);
			//请求参数非空验证
			if(null==vo){
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			
			/*if(StringUtils.isEmpty(vo.get)){
				callback.accept(ResultDataUtils.error("401",new String[]{"brandCode"}));
				return;
			}*/
			if(null==vo.getSkus() || vo.getSkus().isEmpty()){
				callback.accept(ResultDataUtils.error("401",new String[]{"skus"}));
				return;
			}
			
			for(ProductSkuVO sku:vo.getSkus()){
				if(StringUtils.isEmpty(sku.getSkuCode())){
					callback.accept(ResultDataUtils.error("401",new String[]{"skuCode"}));
					return;
				}
				
				if(StringUtils.isEmpty(sku.getSkuName())){
					callback.accept(ResultDataUtils.error("401",new String[]{"skuName"}));
					return;
				}
				
				if(StringUtils.isEmpty(sku.getProductId())){
					callback.accept(ResultDataUtils.error("401",new String[]{"productId"}));
					return;
				}
				
				
				//TODO 产品ID验证
				
			
				
				if(StringUtils.isEmpty(sku.getStatus())){
					callback.accept(ResultDataUtils.error("401",new String[]{"status"}));
					return;
				}
				
				if(null==sku.getSkuAttributes() ||sku.getSkuAttributes().isEmpty()){
					callback.accept(ResultDataUtils.error("401",new String[]{"skuAttributes"}));
					return;
				}
				for(ProductSkuAttribute skuAttr:sku.getSkuAttributes()){
					
					if(StringUtils.isEmpty(skuAttr.getAttributeId())){
						callback.accept(ResultDataUtils.error("401",new String[]{"attributeId"}));
						return;
					}
					//TODO 产品属性ID 验证
					if(StringUtils.isEmpty(skuAttr.getAttributeName())){
						callback.accept(ResultDataUtils.error("401",new String[]{"attributeName"}));
						return;
					}
					
					//TODO 枚举 值验证
					if(StringUtils.isEmpty(skuAttr.getEnumValue())){
						callback.accept(ResultDataUtils.error("401",new String[]{"enumValue"}));
						return;
					}
					if(StringUtils.isEmpty(skuAttr.getEnumText())){
						callback.accept(ResultDataUtils.error("401",new String[]{"enumText"}));
						return;
					}
				}
				
			}
			
			
			/*if(StringUtils.isEmpty(vo.getBrandCode())){
				callback.accept(ResultDataUtils.error("401",new String[]{"brandCode"}));
				return;
			}
			
			if(StringUtils.isEmpty(vo.getBrandName())){
				callback.accept(ResultDataUtils.error("401",new String[]{"brandName"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getSort())){
				callback.accept(ResultDataUtils.error("401",new String[]{"sort"}));
				return;
			}
			
			if(StringUtils.isEmpty(vo.getStatus())){
				callback.accept(ResultDataUtils.error("401",new String[]{"status"}));
				return;
			}*/
			
		
			
			
		
			//状态验证
			/*if(!("Y".equals(vo.getStatus()) || "N".equals(vo.getStatus()))){
				callback.accept(ResultDataUtils.error("410",new String[]{"status"}));
				return;
			}*/
			
	
			//调用Save
			service.save(result->{
				
				String jsonStr=JSON.toJSONString(ResultDataUtils.success(result));
				
				callback.accept(JSON.parse(jsonStr));
			},vo);

		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
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
		
		
		 
		try {
			//请求参数验证
		
			if(StringUtils.isEmpty(skuId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"skuId"}));
				return;
			}
			
			
			ProductSkuVO vo=new ProductSkuVO();

			vo.setSkuId(skuId);
			
			
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
	public void querySkucode(Callback<Object> callback,@PathVariable("skuId")String skuId){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>Query  Product sku detail,skuId is {}",skuId);
		
		try {
			

			//请求参数验证
			if(StringUtils.isEmpty(skuId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"skuId"}));
				return;
			}
			
			
			//设置参数
			ProductSkuVO vo=new ProductSkuVO(); 
			vo.setSkuId(skuId);
			
			//调用查询详情方法
			service.queryDetail(sku->{
				if(null!=sku){
					String jsonStr=JSON.toJSONString(ResultDataUtils.success(sku));
					
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
