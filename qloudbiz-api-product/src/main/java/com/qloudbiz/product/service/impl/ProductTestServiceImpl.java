package com.qloudbiz.product.service.impl;

import java.util.concurrent.CountDownLatch;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.qloudbiz.core.result.PageResultData;
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
	public void query(Callback<PageResultData<Product>> callback,ProductVO vo) throws Exception {
		logger.debug(">>>>>>>>>>service query method start");
		final PageResultData<Product> page=new PageResultData<Product>();
		
		productDao.listall(result->{
		
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SERVICE CALLBACK");
			
 		}, vo);
	}

	
}
