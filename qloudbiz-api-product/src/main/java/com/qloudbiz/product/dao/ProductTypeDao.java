package com.qloudbiz.product.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.pojo.ProductType;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudbiz.product.vo.ProductTypeVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductTypeDao extends BaseDao{
	
	private final static Logger logger = LoggerFactory.getLogger(ProductTypeDao.class);
	
	private String save_productType_sql = "{CALL QLOUDFLOW_PRODUCTTYPE_INSERT_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private String update_productType_sql = "{CALL QLOUDFLOW_PRODUCTTYPE_UPDATE_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private String delete_productType_sql = "{CALL QLOUDFLOW_PRODUCTTYPE_DELETE_PROCEDURE(?)}";
	
	private String query_productTypeInfo_sql = "{CALL QLOUDFLOW_PRODUCTTYPE_INFO_PROCEDURE(?)}";
	
	private String query_productTypeTree_sql = "{CALL QLOUDFLOW_PRODUCTTYPE_TREE_PROCEDURE(?)}";
	
	
	/**
	 * 添加
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void save(Callback<ProductType> callback,ProductTypeVO vo) throws GenericException{
		int rownum = super.callProcUpdate(save_productType_sql,
				vo.getTypeId(),
				vo.getTypeCode(),
				vo.getTypeName(),
				vo.getParentId(),
				vo.isPhysical(),
				vo.isToTransport(),
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
				vo.getTenantId()
				);
		ProductType productTtype = super.callProcQuerySingle(ProductType.class,
				query_productTypeInfo_sql,
				vo.getTypeId());
		callback.accept(productTtype);
	}
	
	
	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void delete(Callback<Integer> callback,ProductTypeVO vo) throws GenericException {
		
		Integer rownum = super.callProcUpdate(delete_productType_sql,
				vo.getTypeId());
		callback.accept(rownum);
	}
	
	
	
	/**
	 * 修改
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
    public void update(Callback<Integer> callback,ProductTypeVO vo) throws GenericException {
		
		Integer rownum = super.callProcUpdate(update_productType_sql,
				vo.getTypeId(),
				vo.getTypeCode(),
				vo.getTypeName(),
				vo.isPhysical(),
				vo.isToTransport(),
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
     * 查询树
     * @param callback
     * @param vo
     * @throws GenericException
     */
	public void queryTree(Callback<List<ProductType>> callback,ProductTypeVO vo) throws GenericException {
		
		List<ProductType> productType = super.callProcQueryList(
				ProductType.class,
				query_productTypeTree_sql,
				vo.getTypeName());
		callback.accept(productType);
	}
	
	

	
	
	
    /**
     * 详情
     * @param callback
     * @param vo
     * @throws GenericException
     */
	public void queryById(Callback<ProductType> callback,ProductTypeVO vo) throws GenericException {
		
		ProductType productTtype = super.callProcQuerySingle(ProductType.class,
				query_productTypeInfo_sql,
				vo.getTypeId());
		callback.accept(productTtype);
	}
	
}
