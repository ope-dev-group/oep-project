package com.qloudbiz.product.service;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudfin.qloudbus.reactive.Callback;

public interface ProductLineService {
	
	public void queryList(Callback<PageResultData<ProductLine>> callback,ProductLineVO vo) throws GenericException;
	

}
