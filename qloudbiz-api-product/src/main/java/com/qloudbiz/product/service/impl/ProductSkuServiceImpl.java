package com.qloudbiz.product.service.impl;






import io.advantageous.boon.core.reflection.BeanUtils;

import java.sql.Timestamp;
import java.util.List;
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
import com.qloudbiz.product.dao.ProductSkuDao;
import com.qloudbiz.product.dao.ProductTestDao;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.pojo.ProductSku;
import com.qloudbiz.product.service.ProductBrandService;
import com.qloudbiz.product.service.ProductSkuService;
import com.qloudbiz.product.service.ProductTestService;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductSkuVO;
import com.qloudbiz.product.vo.ProductSkusVO;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.reactive.CountDownAsyncLatch;




public class ProductSkuServiceImpl implements ProductSkuService{
	private final static Logger logger = LoggerFactory.getLogger(ProductSkuServiceImpl.class);

	private ProductSkuDao productSkuDao=new ProductSkuDao();
	
	



	@Override
	public void save(Callback<ProductSku> callback, ProductSkusVO vo) throws GenericException{
		logger.debug(">>>>>>>>>>Service Method :save  Start");
		productSkuDao.save(result->{
			logger.debug(">>>>>>>>>>Service Method :save  Callback");
			callback.accept(result);
		}, vo);
	}


	@Override
	public void delete(Callback<Integer> callback, ProductSkuVO vo)throws GenericException{
		
		productSkuDao.delete(rownum->{
			callback.accept(rownum);
		}, vo);
		
	}


	@Override
	public void queryDetail(Callback<ProductSku> callback, ProductSkuVO vo)throws GenericException {
		productSkuDao.queryById(brand->{
			callback.accept(brand);
		}, vo);
	}



}
