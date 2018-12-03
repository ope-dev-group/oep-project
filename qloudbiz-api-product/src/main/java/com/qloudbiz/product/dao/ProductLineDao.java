package com.qloudbiz.product.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductLineDao extends BaseDao{
	
	private final static Logger logger = LoggerFactory.getLogger(ProductLineDao.class);
	
	private String save_productLine_sql = "{CALL QLOUDFLOW_PRODUCTLINE_INSERT_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private String query_productLineList_sql = "{CALL QLOUDFLOW_PRODUCTLINE_LISTALL_PROCEDURE(?,?,?,?,?,?)}";
	
	
	public void listall(Callback<PageResultData<ProductLine>> callback,ProductLineVO vo) throws GenericException {
		
		logger.debug("listall currentNum = {}, pagePerNum = {}",
				vo.getCurrentNum(), vo.getPagePerNum());
		
		PageResultData<ProductLine> page = null;
		
		page = super.callProcQueryPage(
				ProductLine.class,
				query_productLineList_sql,
				vo.getCurrentNum(),
				vo.getPagePerNum(),
				vo.getLineCode(),
				vo.getLineName(),
				vo.getStatus(),
				vo.getTenantId());
		
		callback.accept(page);
	}
	
	
	

}
