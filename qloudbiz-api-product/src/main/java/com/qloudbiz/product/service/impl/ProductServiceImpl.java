package com.qloudbiz.product.service.impl;

import com.qloudbiz.product.dao.ProductDao;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.service.ProductServiceInterface;




public class ProductServiceImpl implements ProductServiceInterface{
	
	private ProductDao productDao=new ProductDao();
	@Override
	public void save() {
		System.out.println("save successful");
		Product product=new Product();
		try {
			productDao.save(result->{},product);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		System.out.println("update successful");
		
	}

	@Override
	public void query() {
		System.out.println("query successful");
		
	}

	
}
