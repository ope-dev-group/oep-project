package com.qloudbiz.product.service;

import java.util.List;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.ProductLogicType;
import com.qloudbiz.product.vo.ProductLogicTypeVO;
import com.qloudfin.qloudbus.reactive.Callback;



public interface ProductLogicTypeService {
	
	    //添加
		public void add(Callback<ProductLogicType> callback,ProductLogicTypeVO vo)throws GenericException;
		
		//删除
		public void remote(Callback<Integer> callback,ProductLogicTypeVO vo)throws GenericException;
		
		//修改
		public void modify(Callback<Integer> callback,ProductLogicTypeVO vo)throws GenericException;
		
		//查询树
		public void seachTree(Callback<List<ProductLogicType>> callback,ProductLogicTypeVO vo)throws GenericException;
		
		//查询详情
		public void seachById(Callback<ProductLogicType> callback,ProductLogicTypeVO vo)throws GenericException;
	
		//查询列表
	    public void seachList(Callback<PageResultData<ProductLogicType>> callback,ProductLogicTypeVO vo)throws GenericException;

}
