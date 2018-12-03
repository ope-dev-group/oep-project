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

	
	public void save(Callback<Product> callback, ProductVO vo) throws GenericException {

		//调用存储过程		
		int rownum=super.callProcUpdate(save_product_sql, vo.getProductId(),vo.getCode(),vo.getName());		
		
		//
		Product product=super.callProcQuerySingle(Product.class, listone_sql,vo.getProductId());
		callback.accept(product);

	}

	private String update_sql="{CALL QLOUDFLOW_PRODUCT_UPDATE_PROCEDURE(?,?,?)}";

	//update product
	public void update(Callback<Integer> callback, ProductVO vo) throws GenericException {

		
		Integer rownum=super.callProcUpdate(update_sql,vo.getProductId(),vo.getCode(),vo.getName());
		callback.accept(rownum);
	}

	
	//delete product
	private String delete_sql="{CALL QLOUDFLOW_PRODUCT_DELETE_PROCEDURE(?)}";
	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void delete(Callback<Integer> callback,ProductVO vo) throws GenericException {
		
		
		Integer rownum=super.callProcUpdate(delete_sql,vo.getProductId());
		callback.accept(rownum);
		
	
		
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

	public void queryById(Callback<Product> callback,ProductVO vo)throws GenericException {
		Product product=super.callProcQuerySingle(Product.class, listone_sql,vo.getProductId());
		
		callback.accept(product);
	}
}
