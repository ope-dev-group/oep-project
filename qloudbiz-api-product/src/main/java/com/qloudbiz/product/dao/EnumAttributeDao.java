package com.qloudbiz.product.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.product.pojo.EnumAttribute;
import com.qloudbiz.product.vo.EnumAttributeVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class EnumAttributeDao extends BaseDao{
	
	private final static Logger logger = LoggerFactory.getLogger(EnumAttributeDao.class);
	
    private String save_enum_sql = "{CALL ATTRIBUTEENUM_INSERT_PROCEDURE(?,?,?)}";
	
	private String delete_enum_sql = "{CALL ATTRIBUTEENUM_DELETE_PROCEDURE(?)}";
		
	private String query_list_sql = "{CALL ATTRIBUTEENUM_LISTALL_PROCEDURE(?,?)}";
	
	
	
	/**
	 * 保存
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void save(Callback<EnumAttribute> callback, EnumAttributeVO vo) throws GenericException {
		
		logger.debug(">>>>>>>>EnumAttributeDao Save Method The Param Is {}",vo);
		        //调用保存的存储过程		
		int rownum=super.callProcUpdate(
				save_enum_sql,
				vo.getEnumId(),
				vo.getEnumAttributeId(),
				vo.getAttrributeValues());
		EnumAttribute enumAttribute=super.callProcQuerySingle(EnumAttribute.class, save_enum_sql,vo.getEnumId());
		callback.accept(enumAttribute);	
	}
	
	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	public void delete(Callback<Integer> callback, EnumAttributeVO vo) throws GenericException {
		Integer rownum = super.callProcUpdate(delete_enum_sql,
				vo.getEnumId());
		callback.accept(rownum);
	}
	
	
	
//	public void listall()
//	

}
