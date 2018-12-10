package com.qloudbiz.product.service;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.AttributeGroup;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.vo.AttributeGroupVO;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;


public interface AttributeGroupService {
	
	/**
	 * 保存
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void save(Callback<AttributeGroup> callback,AttributeGroupVO vo) throws GenericException;

	

	//查询列表
/*	public void queryList(Callback<PageResultData<AttributeGroup>> callback,AttributeGroupVO vo)throws GenericException;
*/
	
	//查询详情
	public void queryDetail(Callback<AttributeGroup> callback,AttributeGroupVO vo)throws GenericException;
	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void delete(Callback<Integer> callback,AttributeGroupVO vo)throws GenericException;
	
	/**
	 * 修改
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void update(Callback<Integer> callback,AttributeGroupVO vo)throws GenericException;
	
	

}
