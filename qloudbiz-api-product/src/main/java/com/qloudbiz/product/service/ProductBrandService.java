package com.qloudbiz.product.service;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;


public interface ProductBrandService {
	
	/**
	 * 保存
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void save(Callback<ProductBrand> callback,ProductBrandVO vo) throws GenericException;

	

	//查询列表
	public void queryList(Callback<PageResultData<ProductBrand>> callback,ProductBrandVO vo)throws GenericException;

	
	//查询详情
	public void queryDetail(Callback<ProductBrand> callback,ProductBrandVO vo)throws GenericException;
	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void delete(Callback<Integer> callback,ProductBrandVO vo)throws GenericException;
	
	/**
	 * 修改
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void update(Callback<Integer> callback,ProductBrandVO vo)throws GenericException;
	
	

}
