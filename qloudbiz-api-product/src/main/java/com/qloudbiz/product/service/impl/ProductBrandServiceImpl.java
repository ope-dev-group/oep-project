package com.qloudbiz.product.service.impl;






import io.advantageous.boon.core.reflection.BeanUtils;

import java.sql.Timestamp;
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
import com.qloudbiz.product.dao.ProductBrandDao;
import com.qloudbiz.product.dao.ProductDao;
import com.qloudbiz.product.dao.ProductTestDao;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.service.ProductBrandService;
import com.qloudbiz.product.service.ProductTestService;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.reactive.CountDownAsyncLatch;




public class ProductBrandServiceImpl implements ProductBrandService{
	private final static Logger logger = LoggerFactory.getLogger(ProductBrandServiceImpl.class);

	private ProductBrandDao productBrandDao=new ProductBrandDao();
	
	

	@Override
	public void queryList(Callback<PageResultData<ProductBrand>> callback,ProductBrandVO vo) throws GenericException {
		logger.debug(">>>>>>>>>>Service Method :queryList  start");

		//业务数据验证，如果业务数据有问题，抛出异常
		//ExceptionUtils.throwsGenericException("400");
	
		
		
		productBrandDao.listall(result->{
		
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Service Method querList Callback");
			
 		}, vo);
	}


	@Override
	public void save(Callback<ProductBrand> callback, ProductBrandVO vo) throws GenericException{
		logger.debug(">>>>>>>>>>Service Method :save  Start");
		
		vo.setBrandId(PKUtils.genPK());
		
		//设置创建人信息
		vo.setCreatorId("1");
		vo.setCreatorName("admin");
		vo.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		vo.setTenantId("1");
		
		productBrandDao.save(result->{
			logger.debug(">>>>>>>>>>Service Method :save  Callback");
			callback.accept(result);
		}, vo);
	}


	@Override
	public void delete(Callback<Integer> callback, ProductBrandVO vo)throws GenericException {
		
		//同步计数器
		CountDownLatch latch=new CountDownLatch(1);
		
		ProductBrand prod=new ProductBrand();
	
		
		//查询product，验证是否存在此记录
		productBrandDao.queryById(brand->{
			latch.countDown();
			if(null!=brand){
				BeanUtils.copyProperties(brand, prod);
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
		if(null!=prod && StringUtils.isNotEmpty(prod.getBrandId())){
			
			productBrandDao.delete(rownum->{
				callback.accept(rownum);
			}, vo);
		}else{
			
			//记录不存在抛出异常
			ExceptionUtils.throwsGenericException("408");
		}
	}


	@Override
	public void update(Callback<Integer> callback, ProductBrandVO vo)throws GenericException {
		
		//同步计数器
		CountDownLatch latch=new CountDownLatch(1);
		
		ProductBrand prod=new ProductBrand();
	
		
		//查询product，验证是否存在此记录
		productBrandDao.queryById(brand->{
			latch.countDown();
			if(null!=brand){
				BeanUtils.copyProperties(brand, prod);
			}
			
		}, vo);
		
		
		try {
			//同步等待
			latch.await();
		} catch (InterruptedException e1) {
			
		}
		
		
		
		//记录存在则更新
		vo.setModifierId("1");
		vo.setModifierName("admin");
		vo.setModifyTime(new Timestamp(System.currentTimeMillis()));
		vo.setLastModifyTime(vo.getModifyTime());
		
		if(null!=prod && StringUtils.isNotEmpty(prod.getBrandId())){
			
			productBrandDao.update(rownum->{
				callback.accept(rownum);
			}, vo);
		}else{
			
			//记录不存在抛出异常
			ExceptionUtils.throwsGenericException("408");
		}
	
	}


	@Override
	public void queryDetail(Callback<ProductBrand> callback,ProductBrandVO vo) throws GenericException {
		productBrandDao.queryById(brand->{
			callback.accept(brand);
		}, vo);
	}
	
	

	
}
