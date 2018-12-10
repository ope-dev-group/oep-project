package com.qloudbiz.product.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.AttributeGroup;
import com.qloudbiz.product.vo.AttributeGroupVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class AttributeGroupDao extends BaseDao {
	private final static Logger logger = LoggerFactory
			.getLogger(AttributeGroupDao.class);

	private String save_product_sql = "{CALL ATTRIBUTEGROUP_INSERT_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	public void save(Callback<AttributeGroup> callback, AttributeGroupVO vo) throws GenericException {

		logger.debug(">>>>>>>>AttributeGroupDao Save Method The Param Is {}",vo);
		
		//call ATTRIBUTEGROUP_INSERT_PROCEDURE(1,"APPLE","苹果","1","手动添加",1,"1",SYSDATE(),"User_1","张三",SYSDATE(),"User_2","李四",SYSDATE(),"O1","T1");

		//调用保存的存储过程		
		int rownum=super.callProcUpdate(save_product_sql,
										vo.getGroupId(),
										vo.getGroupName(),
										vo.getProductTypeId(),
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
		
		
		//查询保存后的品牌数据
		AttributeGroup product=super.callProcQuerySingle(AttributeGroup.class, listone_sql,vo.getGroupId());
		callback.accept(product);

	}

	private String update_sql = "{CALL ATTRIBUTEGROUP_UPDATE_PROCEDURE(?,?,?,?,?,?,?,?,?)}";

	// update product
	//call ATTRIBUTEGROUP_UPDATE_PROCEDURE(1,"Lenvo","联想","1","修改","2","Y","3","赵四",SYSDATE(),SYSDATE());
	public void update(Callback<Integer> callback, AttributeGroupVO vo)
			throws GenericException {

		Integer rownum = super.callProcUpdate(update_sql, 
											  vo.getGroupId(),
				                              vo.getGroupName(),
				                              vo.getProductTypeId(),
				                              vo.getSort(),
				                              vo.getStatus(),
				                              vo.getModifierId(),
				                              vo.getModifierName(),
				                              vo.getModifyTime(),
				                              vo.getLastModifyTime());
		callback.accept(rownum); 
	}

	// delete product
	private String delete_sql = "{CALL ATTRIBUTEGROUP_DELETE_PROCEDURE(?)}";

	/**
	 * 删除
	 * 
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	//call ATTRIBUTEGROUP_DELETE_PROCEDURE(1);
	public void delete(Callback<Integer> callback, AttributeGroupVO vo)
			throws GenericException {

		Integer rownum = super.callProcUpdate(delete_sql, vo.getGroupId());
		callback.accept(rownum);

	}

	private String listall_sql = "{CALL ATTRIBUTEGROUP_LISTALL_PROCEDURE(?,?,?,?,?,?,?)}";

	
	/**
	 * 查询分页列表
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	//call ATTRIBUTEGROUP_LISTALL_PROCEDURE(-1,10,"App","1","苹果","1","T1");
	public void listall(Callback<PageResultData<AttributeGroup>> callback,
			AttributeGroupVO vo) throws GenericException {

	/*	logger.debug("listall startRow = {} , pageSize = {}",
				);

		PageResultData<ATTRIBUTEGROUP> page = null;

		page = super.callProcQueryPage(ATTRIBUTEGROUP.class, listall_sql,
				                       vo.getCurrentNum(), 
				                       vo.getPagePerNum(),
				                     
				                       vo.getBrandType(),
				                       voName(),
				                       vo.getStatus(),
				                       vo.getTenantId());

		callback.accept(page);*/
	}

	/**
	 * 查询详情
	 */
	//call ATTRIBUTEGROUP_INFO_PROCEDURE(1);
	private String listone_sql = "{CALL ATTRIBUTEGROUP_INFO_PROCEDURE(?)}";
	public void queryById(Callback<AttributeGroup> callback, AttributeGroupVO vo)
			throws GenericException {

		AttributeGroup attributeGroup = super.callProcQuerySingle(AttributeGroup.class,
				listone_sql, vo.getGroupId());

		callback.accept(attributeGroup);
	}
}
