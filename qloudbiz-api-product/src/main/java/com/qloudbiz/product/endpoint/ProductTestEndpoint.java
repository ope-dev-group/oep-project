package com.qloudbiz.product.endpoint;


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
	 * 查询产品
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
			service.query(page->{
				
				if(null!=page){					
					callback.accept(ResultDataUtils.success(page));
				}
			},vo);
			
			
		} catch (Exception e) {
			logger.error(">>>>>>>>>>query exception ");
			callback.accept(ResultDataUtils.error(e));
		}
	}
}
