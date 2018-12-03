package com.qloudbiz.product.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.dao.ProductLineDao;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.service.ProductLineService;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductLineServiceImpl implements ProductLineService{
	
	private final static Logger logger = LoggerFactory.getLogger(ProductLineServiceImpl.class);
	
	private ProductLineDao productLineDao = new ProductLineDao();
	

	@Override
	public void queryList(Callback<PageResultData<ProductLine>> callback,
			ProductLineVO vo) throws GenericException {
		
		logger.debug(">>>>>>>>>>>>>> service query method start");
		
		productLineDao.listall(result->{
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> queryList is error in dao");
		},vo);
		
		
	}

}
