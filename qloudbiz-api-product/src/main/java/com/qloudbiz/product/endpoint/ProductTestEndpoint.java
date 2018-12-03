package com.qloudbiz.product.endpoint;


import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.dao.ProductDao;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.service.ProductTestService;
import com.qloudbiz.product.service.impl.ProductTestServiceImpl;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;


/**
 * 产品测试微服务
 *
 * @author Kezx
 *
 */
@RequestMapping("/")
public class ProductTestEndpoint {
	
	
	private final static Logger logger=LoggerFactory.getLogger(ProductTestEndpoint.class);
	
	//创建Service
	ProductTestService service=ServiceProxyFactory.createProxy(ProductTestServiceImpl.class);
	
	/**
	 * 查询产品列表
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public void queryProducttest(Callback<Object> callback,@RequestParam("name") String name,@RequestParam("currentNum")int currentNum,@RequestParam("pagePerNum")int pagePerNum){
		logger.debug(">>>>>>>>>>endpoint query product test method start");
	
		
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
			ProductVO vo=new ProductVO();
			vo.setName(name);
			vo.setCurrentNum(currentNum);
			vo.setPagePerNum(pagePerNum);
			
		
			

			//调用Service
			service.queryList(page->{
				
				if(null!=page){					
					callback.accept(ResultDataUtils.success(page));
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
	 * 查询详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/test/{productId}",method=RequestMethod.GET)
	public void queryProduct(Callback<Object> callback,@PathVariable("productId")String productId){
		logger.debug(">>>>>>>>>>Query productId is {}",productId);
	
		 
		try {
			

			//请求参数验证
			if(null==productId || productId.isEmpty()){
				callback.accept(ResultDataUtils.error("402",new String[]{"productId"}));
				return;
			}
			
			ProductVO vo=new ProductVO(); 
			vo.setProductId(productId);
			service.queryDetail(result->{
				if(null!=result){
					callback.accept(ResultDataUtils.success(result));
				}else{
					callback.accept(ResultDataUtils.error("404"));
				}
			}, vo);
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	}
	
	/**
	 * 保存产品
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/test",method=RequestMethod.POST)
	public void saveProducttest(Callback<Object> callback,final ProductVO vo){
		logger.debug(">>>>>>>>>>save product the param is {}",vo);
	
		 
		try {

			
			//请求参数验证
			if(null==vo){
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			
			if(null==vo.getCode() || vo.getCode().isEmpty()){
				callback.accept(ResultDataUtils.error("401",new String[]{"code"}));
				return;
			}
			
			if(null==vo.getName() || vo.getName().isEmpty()){
				callback.accept(ResultDataUtils.error("401",new String[]{"name"}));
				return;
			}


			//调用Service
			service.save(product->{				
				callback.accept(ResultDataUtils.success(product));
			},vo);
			
			
		} catch (Exception e) {
			
			callback.accept(ResultDataUtils.error(e));
		}
	}
	
	
	
	/**
	 * 删除
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/test/{productId}",method=RequestMethod.DELETE)
	public void deleteProducttest(Callback<Object> callback,@PathVariable("productId")String productId){
		logger.debug(">>>>>>>>>>delete product the productId is {}",productId);
	
		 
		try {
			
			
			
			
			//请求参数验证
		
			if(null==productId ||productId.isEmpty()){
				callback.accept(ResultDataUtils.error("401",new String[]{"productId"}));
				return;
			}
			
			
			ProductVO vo=new ProductVO();

			vo.setProductId(productId);
			
			
			//查询product，验证是否存在
			
			service.delete(rownum->{				
				try {
					if(null!=rownum && rownum.intValue()==1){
						callback.accept(ResultDataUtils.success());
					}else{
						callback.accept(ResultDataUtils.error("407"));
					}
				} catch (Exception e) {
					logger.error("the exception is {}",e);
					callback.accept(ResultDataUtils.error(e));
				}
			},vo);
	
		
		
			
			
			
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	}
	
	
	
	/**
	 * 修改
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/test/{productId}",method=RequestMethod.PUT)
	public void updateProducttest(Callback<Object> callback,@PathVariable("productId")String productId,ProductVO vo){
		logger.debug(">>>>>>>>>>update productId is {}, the param is {}",productId,vo);
	
		 
		try {
			

			//请求参数验证
			if(null==productId || productId.isEmpty()){
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			
			
			if(null==vo.getCode() || vo.getCode().isEmpty()){
				callback.accept(ResultDataUtils.error("401",new String[]{"code"}));
				return;
			}
			
			if(null==vo.getName() || vo.getName().isEmpty()){
				callback.accept(ResultDataUtils.error("401",new String[]{"name"}));
				return;
			}

			vo.setProductId(productId);

			//调用update Service
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
}
