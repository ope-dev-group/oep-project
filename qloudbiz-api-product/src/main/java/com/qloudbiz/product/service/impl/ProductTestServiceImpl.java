package com.qloudbiz.product.service.impl;






import io.advantageous.boon.core.reflection.BeanUtils;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;








import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;








import com.csft.product.dao.test.ProductDaoTest;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.ExceptionUtils;
import com.qloudbiz.core.utils.PKUtils;
import com.qloudbiz.product.dao.ProductDao;
import com.qloudbiz.product.dao.ProductTestDao;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.service.ProductTestService;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.reactive.CountDownAsyncLatch;




public class ProductTestServiceImpl implements ProductTestService{
	private final static Logger logger = LoggerFactory.getLogger(ProductTestServiceImpl.class);

	private ProductTestDao productDao=new ProductTestDao();
	
	

	@Override
	public void queryList(Callback<PageResultData<Product>> callback,ProductVO vo) throws GenericException {
		logger.debug(">>>>>>>>>>Service Method :queryList  start");

		//业务数据验证，如果业务数据有问题，抛出异常
		//ExceptionUtils.throwsGenericException("400");
	
		
		
		productDao.listall(result->{
		
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Service Method querList Callback");
			
 		}, vo);
	}


	@Override
	public void save(Callback<Product> callback, ProductVO vo) throws GenericException{
		logger.debug(">>>>>>>>>>Service Method :save  Start");
		//设置主键
		vo.setProductId(PKUtils.genPK());
		
		productDao.save(result->{
			logger.debug(">>>>>>>>>>Service Method :save  Callback");
			callback.accept(result);
		}, vo);
	}


	@Override
	public void delete(Callback<Integer> callback, ProductVO vo)throws GenericException {
		
		
			
		productDao.delete(rownum->{
			callback.accept(rownum);
		}, vo);
		
	}


	@Override
	public void update(Callback<Integer> callback, ProductVO vo)throws GenericException {
		
		
		productDao.update(rownum->{
			callback.accept(rownum);
		}, vo);
		
	}


	@Override
	public void queryDetail(Callback<Product> callback,ProductVO vo) throws GenericException {
		productDao.queryById(product->{
			callback.accept(product);
		}, vo);
	}
	
	

	
}
