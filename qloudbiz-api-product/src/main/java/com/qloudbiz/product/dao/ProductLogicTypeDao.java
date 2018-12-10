package com.qloudbiz.product.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.pojo.ProductLogicType;
import com.qloudbiz.product.pojo.ProductType;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudbiz.product.vo.ProductLogicTypeVO;
import com.qloudbiz.product.vo.ProductTypeVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductLogicTypeDao extends BaseDao{
	
private final static Logger logger = LoggerFactory.getLogger(ProductLineDao.class);
	
	private String save_productLogicType_sql = "{CALL QLOUDFLOW_PRODUCTLOGICTYPE_INSERT_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private String query_productLogicTypeList_sql = "{CALL QLOUDFLOW_PRODUCTLOGICTYPE_LISTALL_PROCEDURE(?,?,?,?,?,?)}";
	
	private String query_productLogicTypeTree_sql = "{CALL QLOUDFLOW_PRODUCTLOGICTYPE_TREE_PROCEDURE(?,?)}";
	
	private String query_productLogicTypeInfo_sql = "{CALL QLOUDFLOW_PRODUCTLOGICTYPE_INFO_PROCEDURE(?)}";
	
	private String delete_productLogicType_sql = "{CALL QLOUDFLOW_PRODUCTLOGICTYPE_DELETE_PROCEDURE(?)}";
	
	private String update_productLogicType_sql = "{CALL QLOUDFLOW_PRODUCTLOGICTYPE_UPDATE_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	
	
	/**
	 * 新增
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
    public void save(Callback<ProductLogicType> callback,ProductLogicTypeVO vo) throws GenericException {
		
		int rownum = super.callProcUpdate(save_productLogicType_sql,
				vo.getTypeId(),
				vo.getTypeCode(),
				vo.getTypeName(),
				vo.getParentId(),
				vo.isPhysical(),
				vo.getProdLineId(),
				vo.getSort(),
				vo.getStatus(),
				vo.getRemark(),
				vo.getCreatedTime(),
				vo.getCreatorId(),
				vo.getCreatorName(),
				vo.getModifyTime(),
				vo.getModifierId(),
				vo.getModifierName(),
				vo.getLastModifyTime(),
				vo.getTenantId());
		
		ProductLogicType logicType = super.callProcQuerySingle(ProductLogicType.class,
				query_productLogicTypeInfo_sql,
				vo.getTypeId());
		
		callback.accept(logicType);
	}
	
	
    
    /**
     * 删除
     * @param callback
     * @param vo
     * @throws GenericException
     */
    public void delete(Callback<Integer> callback,ProductLogicTypeVO vo) throws GenericException {
		
		Integer rownum = super.callProcUpdate(delete_productLogicType_sql,
				vo.getTypeId());
		callback.accept(rownum);
	}
    
    
    
    /**
     * 修改
     * @param callback
     * @param vo
     * @throws GenericException
     */
    public void update(Callback<Integer> callback,ProductLogicTypeVO vo) throws GenericException {
		
		Integer rownum = super.callProcUpdate(update_productLogicType_sql,
				vo.getTypeId(),
				vo.getTypeCode(),
				vo.getTypeName(),
				vo.getParentId(),
				vo.getSort(),
				vo.getStatus(),
				vo.getProdLineId(),
				vo.getTenantId(),
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
	public void listall(Callback<PageResultData<ProductLogicType>> callback,ProductLogicTypeVO vo) throws GenericException {
		
		logger.debug("listall startRow = {}, pagePerNum = {}",
				vo.getCurrentNum(),
				vo.getPagePerNum());
		
		PageResultData<ProductLogicType> page = null;
		
		page = super.callProcQueryPage(
				ProductLogicType.class,
				query_productLogicTypeList_sql,
				vo.getCurrentNum(),
				vo.getPagePerNum(),
				vo.getTypeCode(),
				vo.getTypeName(),
				vo.getStatus(),
				vo.getProdLineId());
		
		callback.accept(page);
	}
    
	
	
	/**
	 * 查询详情
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
    public void queryById(Callback<ProductLogicType> callback,ProductLogicTypeVO vo) throws GenericException {
		
    	ProductLogicType productLogicType = super.callProcQuerySingle(ProductLogicType.class,
				query_productLogicTypeInfo_sql,
				vo.getTypeId());
		callback.accept(productLogicType);
	}
    
	
    
    /**
     * 查询树
     * @param callback
     * @param vo
     * @throws GenericException
     */
    public void queryTree(Callback<List<ProductLogicType>> callback,ProductLogicTypeVO vo) throws GenericException {
		
		List<ProductLogicType> productLogicType = super.callProcQueryList(
				ProductLogicType.class,
				query_productLogicTypeTree_sql,
				vo.getTypeId(),
				vo.getTypeName());
		callback.accept(productLogicType);
	}
	
	
	
	

}
