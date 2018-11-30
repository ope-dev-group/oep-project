package com.qloudbiz.product.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductTestDao extends BaseDao {
	private final static Logger logger = LoggerFactory.getLogger(ProductTestDao.class);
	
	
	private String save_product_sql = "{CALL QLOUDFLOW_PRODUCT_INSERT_PROCEDURE(?,?,?)}";

	
	public void save(Callback<Product> callback, ProductVO entity) throws GenericException {

		//调用存储过程		
		int rownum=super.callProcUpdate(save_product_sql, entity.getProductId(),entity.getCode(),entity.getName());		
		
		//
		Product product=super.callProcQuerySingle(Product.class, listone_sql,entity.getProductId());
		callback.accept(product);

	}

	private String update_sql="{CALL QLOUDFLOW_PRODUCT_UPDATE_PROCEDURE(?,?,?,?)}";

	//update product
	public void update(Callback<Object> callback, Product entity) throws GenericException {

		
		
	}

	
	//delete product
	private String delete_sql="{CALL QLOUDFLOW_PRODUCT_DELETE_PROCEDURE(?,?)}";
	
	public void delete(Callback<Object> callback, Product entity) throws GenericException {
		
	}

	private String listall_sql = "{CALL QLOUDFLOW_PRODUCT_LISTALL_PROCEDURE(?,?,?)}";

	public void listall(Callback<PageResultData<Product>> callback,ProductVO vo)throws GenericException {
		
	
	
		logger.debug("listall startRow = {} , pageSize = {}",
				vo.getCurrentNum(), vo.getPagePerNum());


		PageResultData<Product> page=null;

	
		page = super.callProcQueryPage(Product.class,
				listall_sql, vo.getCurrentNum(), vo.getPagePerNum(),
				vo.getName());
		
		callback.accept(page);
	}
	
	
	private String listone_sql = "{CALL QLOUDFLOW_PRODUCT_SINGLE_PROCEDURE(?)}";

	public void queryById(Callback<Product> callback,String id)throws GenericException {
		
	
		super.callProcQuerySingle(Product.class, listone_sql,id);
	}
}
