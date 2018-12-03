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
		logger.debug(">>>>>>>>>>service query method start");

		//业务数据验证，如果业务数据有问题，抛出异常
		//ExceptionUtils.throwsGenericException("400");
	
		
		
		productDao.listall(result->{
		
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>SERVICE CALLBACK");
			
 		}, vo);
	}


	@Override
	public void save(Callback<Product> callback, ProductVO vo) throws GenericException{
		
		//设置主键
		vo.setProductId(UUID.randomUUID().toString().replaceAll("-",""));
		
		productDao.save(result->{
			callback.accept(result);
		}, vo);
		
		
	}


	@Override
	public void delete(Callback<Integer> callback, ProductVO vo)throws GenericException {
		
		//同步计数器
		CountDownLatch latch=new CountDownLatch(1);
		
		Product prod=new Product();
	
		
		//查询product，验证是否存在此记录
		productDao.queryById(product->{
			latch.countDown();
			if(null!=product){
				BeanUtils.copyProperties(product, prod);
			}
			
		}, vo);
		
		
		try {
			//同步等待
			latch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//记录存在则删除
		if(null!=prod && StringUtils.isNotEmpty(prod.getProductId())){
			
			productDao.delete(rownum->{
				callback.accept(rownum);
			}, vo);
		}else{
			
			//记录不存在抛出异常
			ExceptionUtils.throwsGenericException("408");
		}
	}


	@Override
	public void update(Callback<Integer> callback, ProductVO vo)throws GenericException {
		
		//同步计数器
		CountDownLatch latch=new CountDownLatch(1);
		
		Product prod=new Product();
	
		
		//查询product，验证是否存在此记录
		productDao.queryById(product->{
			latch.countDown();
			if(null!=product){
				BeanUtils.copyProperties(product, prod);
			}
			
		}, vo);
		
		
		try {
			//同步等待
			latch.await();
		} catch (InterruptedException e1) {
			
		}
		
		
		
		//记录存在则更新
		if(null!=prod && StringUtils.isNotEmpty(prod.getProductId())){
			
			productDao.update(rownum->{
				callback.accept(rownum);
			}, vo);
		}else{
			
			//记录不存在抛出异常
			ExceptionUtils.throwsGenericException("408");
		}
	
	}


	@Override
	public void queryDetail(Callback<Product> callback,ProductVO vo) throws GenericException {
		productDao.queryById(product->{
			callback.accept(product);
		}, vo);
	}
	
	

	
}
