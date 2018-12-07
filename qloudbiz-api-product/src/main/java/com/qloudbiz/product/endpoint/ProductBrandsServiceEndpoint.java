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
import com.qloudbiz.core.result.ResultData;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.service.ProductBrandService;
import com.qloudbiz.product.service.ProductTestService;
import com.qloudbiz.product.service.impl.ProductBrandServiceImpl;
import com.qloudbiz.product.service.impl.ProductTestServiceImpl;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.security.util.StringUtils;

/**
 * 品牌微服务
 *
 * @author Kezx
 *
 */
@RequestMapping("/products")
public class ProductBrandsServiceEndpoint {
	private final static String PATH_ADD_BRANDS_JSON = "com/qloudfin/qloudbiz/apidef/products/productbrands-create.json";// 添加品牌json
	private final static String PATH_UPDATE_DELETE = "com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";// 新增或者修改json
	private final static String PATH_LIST_BRANDS_JSON = "com/qloudfin/qloudbiz/apidef/products/productbrands-list.json";// 添加品牌json

	private final static String PATH_QUERY_BRANDS_DETAIL_JSON = "com/qloudfin/qloudbiz/apidef/products/productbrands-detail.json";// 添加品牌json

	private final static Logger logger = LoggerFactory
			.getLogger(ProductBrandsServiceEndpoint.class);

    ProductBrandService service=ServiceProxyFactory.createProxy(ProductBrandServiceImpl.class);

	/**
	 * 添加品牌接口
	 * 
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value = "/brands", method = RequestMethod.POST)
	public void addBrands(Callback<Object> callback, ProductBrandVO vo) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add brands param is:{}", vo);

		 
		try {

			
			//请求参数非空验证
			if(null==vo){
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			
			if(StringUtils.isEmpty(vo.getBrandCode())){
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
			}
			
		
			
			
		
			//状态验证
			if(!("Y".equals(vo.getStatus()) || "N".equals(vo.getStatus()))){
				callback.accept(ResultDataUtils.error("410",new String[]{"status"}));
				return;
			}
			

			//调用Save
			service.save(brand->{
				
				String jsonStr=JSON.toJSONString(ResultDataUtils.success(brand),new SimplePropertyPreFilter(ProductBrand.class,"brandId","brandCode"));
				
				callback.accept(JSON.parse(jsonStr));
			},vo);
			
			
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	}

	/**
	 * 修改、品牌
	 * 
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value = "/brands/{brandId}", method = RequestMethod.PUT)
	public void updateBrand(Callback<Object> callback,
			@PathVariable("brandId") String brandId, ProductBrandVO vo) {

	
		logger.debug(">>>>>>>>>>>>>>>>>Update brand  the brandId is {}, the param is {}",brandId,vo);
		
		 
		try {
			

			//请求参数验证
			if(null==vo){
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			
			//请求参数验证
			if(StringUtils.isEmpty(brandId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"brandId"}));
				return;
			}
			
			
		    vo.setBrandId(brandId);
			
			
			if(StringUtils.isEmpty(vo.getBrandCode())){
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
			}
			
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
			
			
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	}

	/**
	 * 删除品牌
	 * 
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value = "/brands/{brandId}", method = RequestMethod.DELETE)
	public void deleteBrand(Callback<Object> callback,
			@PathVariable("brandId") String brandId) {
		/*logger.debug(">>>>>>>>>>>>>Delete brands brandId is:{}");

		// 调试日志

		// 读取json数据
		String content = FileUtils.getResourceContent(PATH_UPDATE_DELETE);

		Object resultObj = JSON.parse(content);

		callback.accept(resultObj);*/
		
		logger.debug(">>>>>>>>>>>>>>>>>Delete productBrand the brandId is {}",brandId);
		
		 
		try {
			//请求参数验证
		
			if(StringUtils.isEmpty(brandId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"productId"}));
				return;
			}
			
			
			ProductBrandVO vo=new ProductBrandVO();

			vo.setBrandId(brandId);
			
			
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
	 * 查询品牌列表
	 * 
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value = "/brands", method = RequestMethod.GET)
	public void queryBrands(Callback<Object> callback,
			@RequestParam("brandCode") String brandCode,
			@RequestParam("brandName") String brandName,
			@RequestParam("brandType") String brandType,
			@RequestParam("status") String status,
			@RequestParam("currentNum")int currentNum,
			@RequestParam("pagePerNum")int pagePerNum) {
		// 调试日志
		logger.debug(
				">>>>>>>>>>>>>Query brands list  ,the brandCode is {},brandName is {},brandType is {},status is {}",
				brandCode, brandName, brandType, status);

		try {
			
			
			//请求参数验证
			if(currentNum<=0){
				callback.accept(ResultDataUtils.error("401",new String[]{"currentNum"}));
				return;
			}
			
			if(pagePerNum<=0){
				callback.accept(ResultDataUtils.error("401",new String[]{"pagePerNum"}));
				return;
			}
			
			
			//参数设置
			ProductBrandVO vo=new ProductBrandVO();
			vo.setBrandName(brandName);
			vo.setCurrentNum(currentNum);
			vo.setPagePerNum(pagePerNum);
			vo.setBrandCode(brandCode);
			vo.setBrandType(brandType);
			vo.setStatus(status);
		
			

			//调用分页查询方法
			service.queryList(page->{
				
				if(null!=page){	
					String jsonStr=JSON.toJSONString(ResultDataUtils.success(page));
					
					callback.accept(JSON.parse(jsonStr));
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
	 * 查询品牌详情
	 * 
	 * @param callback
	 * @param corpId
	 * @param token
	 */

	@RequestMapping(value = "/brands/{brandId}", method = RequestMethod.GET)
	public void queryBrandDetail(Callback<Object> callback,
			@PathVariable("brandId") String brandId) {

		 // 调试日志
		logger.debug(">>>>>>>>>>>>>Query brands Detail ,the brandId is {},",brandId);

		try {
			

			//请求参数验证
			if(StringUtils.isEmpty(brandId)){
				callback.accept(ResultDataUtils.error("402",new String[]{"brandId"}));
				return;
			}
			
			
			//设置参数
			ProductBrandVO vo=new ProductBrandVO(); 
			vo.setBrandId(brandId);
			
			//调用查询详情方法
			service.queryDetail(brand->{
				if(null!=brand){
					String jsonStr=JSON.toJSONString(ResultDataUtils.success(brand));
					
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
