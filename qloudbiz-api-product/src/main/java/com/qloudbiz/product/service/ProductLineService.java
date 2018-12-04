package com.qloudbiz.product.service;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudfin.qloudbus.reactive.Callback;

public interface ProductLineService {
	
	//添加
	public void save(Callback<ProductLine> callback,ProductLineVO vo)throws GenericException;
	
	//删除
	public void delete(Callback<Integer> callback,ProductLineVO vo)throws GenericException;
	
	//修改
	public void update(Callback<Integer> callback,ProductLineVO vo)throws GenericException;
	
	//查询列表
	public void queryList(Callback<PageResultData<ProductLine>> callback,ProductLineVO vo) throws GenericException;
	
	//查询详情
	public void queryInfo(Callback<ProductLine> callback, ProductLineVO vo) throws GenericException;
	
}
