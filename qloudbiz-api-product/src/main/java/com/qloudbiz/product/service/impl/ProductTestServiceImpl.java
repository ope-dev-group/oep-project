package com.qloudbiz.product.service.impl;






import java.util.UUID;

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
		
		//查询product，验证是否存在
		productDao.queryById(product->{
			if(null!=product){
				
					try {
						productDao.delete(rownum->{
							try {
								callback.accept(rownum);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								ExceptionUtils.throwsGenericException("408");
							}
						}, vo);
					} catch (GenericException e) {
						ExceptionUtils.throwsGenericException(e.getCode(),e.getData());
					}
				
			}else{
				ExceptionUtils.throwsGenericException("408");
			}
		}, vo.getProductId());
		
		
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
		}, vo.getProductId());
	}
	
	

	
}
