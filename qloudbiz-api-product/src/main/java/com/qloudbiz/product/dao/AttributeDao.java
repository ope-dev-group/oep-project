package com.qloudbiz.product.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.product.pojo.Attribute;
import com.qloudbiz.product.pojo.AttributeGroup;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.vo.AttributeGroupVO;
import com.qloudbiz.product.vo.AttributeVO;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class AttributeDao extends BaseDao{
	
	private final static Logger logger = LoggerFactory.getLogger(AttributeDao.class);
	
	private String save_attribute_sql = "{CALL ATTRIBUTE_INSERT_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private String query_attributeList_sql = "{CALL QLOUDFLOW_PRODUCTLINE_LISTALL_PROCEDURE(?,?,?,?,?,?,?,?)}";
	
	private String query_attributeInfo_sql = "{CALL QLOUDFLOW_PRODUCTLINE_INFO_PROCEDURE(?)}";
	
	private String delete_attribute_sql = "{CALL QLOUDFLOW_PRODUCTLINE_DELETE_PROCEDURE(?)}";
	
	private String update_attribute_sql = "{CALL QLOUDFLOW_PRODUCTLINE_UPDATE_PROCEDURE(?,?,?,?,?,?,?,?,?)}";
	
	
	
	
	
	/**
	 * 保存
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void save(Callback<Attribute> callback, AttributeVO vo) throws GenericException {

		logger.debug(">>>>>>>>AttributeDao Save Method The Param Is {}",vo);

		//调用保存的存储过程		
		int rownum=super.callProcUpdate(save_attribute_sql,
										vo.getAttributeId(),
										vo.getAttributeName(),
										vo.getAttributeCode(),
										vo.getProdTypeId(),
										vo.getAttributeType(),
										vo.getIsRequired(),
										vo.getAttributeContraint(),
										vo.getMaxStringLength(),
										vo.getDateTimeFormat(),
										vo.getNumberFormat(),
										vo.getNumberUnit(),
										vo.getSort(),
										vo.getStatus(),
										vo.getIsSearchForMiddlePlatform(),
										vo.getIsSearchForShoppingMall(),
										vo.getDescription(),
										vo.getCreatedTime(),
										vo.getCreatorId(),
										vo.getCreatorName(),
										vo.getModifyTime(),
										vo.getModifierId(),
										vo.getModifierName(),
										vo.getLastModifyTime(),
										vo.getTenantId(),
										vo.getAttributeGroupId());		

		Attribute attribute=super.callProcQuerySingle(Attribute.class, save_attribute_sql,vo.getAttributeId());
		callback.accept(attribute);
	}
	
	
	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void delete(Callback<Integer> callback,AttributeVO vo) throws GenericException {
			
		Integer rownum = super.callProcUpdate(delete_attribute_sql,
				vo.getAttributeId());
		callback.accept(rownum);
	}
	
	
	
	/**
	 * 查询详情
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void queryById(Callback<Attribute> callback,AttributeVO vo) throws GenericException {
			
			Attribute attribute = super.callProcQuerySingle(Attribute.class,
					query_attributeInfo_sql,
					vo.getAttributeId());
			callback.accept(attribute);
		}
	

}
