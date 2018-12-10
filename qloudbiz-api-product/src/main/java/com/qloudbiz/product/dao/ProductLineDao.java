package com.qloudbiz.product.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductLineDao extends BaseDao{
	
	private final static Logger logger = LoggerFactory.getLogger(ProductLineDao.class);
	
	private String save_productLine_sql = "{CALL QLOUDFLOW_PRODUCTLINE_INSERT_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private String query_productLineList_sql = "{CALL QLOUDFLOW_PRODUCTLINE_LISTALL_PROCEDURE(?,?,?,?,?,?)}";
	
	private String query_prductLineInfo_sql = "{CALL QLOUDFLOW_PRODUCTLINE_INFO_PROCEDURE(?)}";
	
	private String delete_productLine_sql = "{CALL QLOUDFLOW_PRODUCTLINE_DELETE_PROCEDURE(?)}";
	
	private String update_productLine_sql = "{CALL QLOUDFLOW_PRODUCTLINE_UPDATE_PROCEDURE(?,?,?,?,?,?,?,?,?)}";
	
	/**
	 * 添加
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void save(Callback<ProductLine> callback,ProductLineVO vo) throws GenericException {
		
		int rownum = super.callProcUpdate(save_productLine_sql,
				vo.getLineId(),
				vo.getLineCode(),
				vo.getLineName(),
				vo.getParentId(),
				vo.getSort(),
				vo.getStatus(),
				vo.getCreatedTime(),
				vo.getCreatorId(),
				vo.getCreatorName(),
				vo.getModifyTime(),
				vo.getModifierId(),
				vo.getModifierName(),
				vo.getLastModifyTime(),
				vo.getOwnerCompanyCode(),
				vo.getTenantId());
		
		ProductLine line = super.callProcQuerySingle(ProductLine.class,
				query_prductLineInfo_sql,
				vo.getLineId());
		
		callback.accept(line);
	}
	
	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void delete(Callback<Integer> callback,ProductLineVO vo) throws GenericException {
		
		Integer rownum = super.callProcUpdate(delete_productLine_sql,
				vo.getLineId());
		callback.accept(rownum);
	}
	
	
	
	/**
	 * 修改
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void update(Callback<Integer> callback,ProductLineVO vo) throws GenericException {
		
		Integer rownum = super.callProcUpdate(update_productLine_sql,
				vo.getLineId(),
				vo.getLineName(),
				vo.getParentId(),
				vo.getSort(),
				vo.getStatus(),
				vo.getModifierId(),
				vo.getModifierName(),
				vo.getModifyTime(),
				vo.getLastModifyTime());
		callback.accept(rownum);
	}
	
	
	
	
	/**
	 * 查询列表
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void listall(Callback<PageResultData<ProductLine>> callback,ProductLineVO vo) throws GenericException {
		
		logger.debug("listall startRow = {}, pagePerNum = {}",
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
	
	
	
    /**
     * 查询详情
     * @param callback
     * @param vo
     * @throws GenericException
     */
	public void queryById(Callback<ProductLine> callback,ProductLineVO vo) throws GenericException {
		
		ProductLine productLine = super.callProcQuerySingle(ProductLine.class,
				query_prductLineInfo_sql,
				vo.getLineId());
		callback.accept(productLine);
	}
	
	
	

}
