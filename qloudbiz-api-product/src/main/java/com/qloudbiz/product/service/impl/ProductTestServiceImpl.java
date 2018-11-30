package com.qloudbiz.product.service.impl;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.ExceptionUtils;
import com.qloudbiz.product.dao.ProductDao;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.service.ProductTestService;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;




public class ProductTestServiceImpl implements ProductTestService{
	private final static Logger logger = LoggerFactory.getLogger(ProductTestServiceImpl.class);

	private ProductDao productDao=new ProductDao();
	
	
	/*@Override
	public void save() {
		System.out.println("save successful");
		Product product=new Product();
		try {
			productDao.save(result->{},product);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		System.out.println("update successful");
		
	}*/

	@Override
	public void query(Callback<PageResultData<Product>> callback,ProductVO vo) throws GenericException {
		logger.debug(">>>>>>>>>>service query method start");

		//业务数据验证，如果业务数据有问题，抛出异常
		//ExceptionUtils.throwsGenericException("400");
	
		
		
		productDao.listall(result->{
		
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SERVICE CALLBACK");
			
 		}, vo);
	}

	
}
