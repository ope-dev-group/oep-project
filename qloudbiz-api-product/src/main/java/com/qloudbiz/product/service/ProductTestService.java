package com.qloudbiz.product.service;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;


public interface ProductTestService {
	
	/**
	 * 保存
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void save(Callback<Product> callback,ProductVO vo) throws GenericException;

	

	//查询列表
	public void queryList(Callback<PageResultData<Product>> callback,ProductVO vo)throws GenericException;

	
	//查询详情
	public void queryDetail(Callback<Product> callback,ProductVO vo)throws GenericException;
	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void delete(Callback<Integer> callback,ProductVO vo)throws Exception;
	
	/**
	 * 修改
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void update(Callback<Integer> callback,ProductVO vo)throws GenericException;
	
	

}
