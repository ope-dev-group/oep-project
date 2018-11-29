package com.qloudbiz.product.service;

import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;


public interface ProductTestService {
	
	/*public void save();

	
	public void update() ;*/
	
	public void query(Callback<PageResultData<Product>> callback,ProductVO vo)throws Exception;

}
