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
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductBrandDao extends BaseDao {
	private final static Logger logger = LoggerFactory
			.getLogger(ProductBrandDao.class);

	private String save_product_sql = "{CALL PRODUCTBRAND_INSERT_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

	public void save(Callback<ProductBrand> callback, ProductBrandVO vo) throws GenericException {

		logger.debug(">>>>>>>>ProductBrandDao Save Method The Param Is {}",vo);
		
		//call PRODUCTBRAND_INSERT_PROCEDURE(1,"APPLE","苹果","1","手动添加",1,"1",SYSDATE(),"User_1","张三",SYSDATE(),"User_2","李四",SYSDATE(),"O1","T1");

		//调用保存的存储过程		
		int rownum=super.callProcUpdate(save_product_sql,
										vo.getBrandId(),
										vo.getBrandCode(),
										vo.getBrandName(),
										vo.getBrandType(),
										vo.getRemark(),
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
		ProductBrand product=super.callProcQuerySingle(ProductBrand.class, listone_sql,vo.getBrandId());
		callback.accept(product);

	}

	private String update_sql = "{CALL PRODUCTBRAND_UPDATE_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?)}";

	// update product
	//call PRODUCTBRAND_UPDATE_PROCEDURE(1,"Lenvo","联想","1","修改","2","Y","3","赵四",SYSDATE(),SYSDATE());
	public void update(Callback<Integer> callback, ProductBrandVO vo)
			throws GenericException {

		Integer rownum = super.callProcUpdate(update_sql, 
											  vo.getBrandId(),
				                              vo.getBrandCode(),
				                              vo.getBrandName(),
				                              vo.getBrandType(),
				                              vo.getRemark(),
				                              vo.getSort(),
				                              vo.getStatus(),
				                              vo.getModifierId(),
				                              vo.getModifierName(),
				                              vo.getModifyTime(),
				                              vo.getLastModifyTime());
		callback.accept(rownum);
	}

	// delete product
	private String delete_sql = "{CALL PRODUCTBRAND_DELETE_PROCEDURE(?)}";

	/**
	 * 删除
	 * 
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	//call PRODUCTBRAND_DELETE_PROCEDURE(1);
	public void delete(Callback<Integer> callback, ProductBrandVO vo)
			throws GenericException {

		Integer rownum = super.callProcUpdate(delete_sql, vo.getBrandId());
		callback.accept(rownum);

	}

	private String listall_sql = "{CALL PRODUCTBRAND_LISTALL_PROCEDURE(?,?,?,?,?,?,?)}";

	
	/**
	 * 查询分页列表
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	//call PRODUCTBRAND_LISTALL_PROCEDURE(-1,10,"App","1","苹果","1","T1");
	public void listall(Callback<PageResultData<ProductBrand>> callback,
			ProductBrandVO vo) throws GenericException {

		logger.debug("listall startRow = {} , startRowstartRow = {}",
				vo.getCurrentNum(), vo.getPagePerNum());

		PageResultData<ProductBrand> page = null;

		page = super.callProcQueryPage(ProductBrand.class, listall_sql,
				                       vo.getCurrentNum(), 
				                       vo.getPagePerNum(),
				                       vo.getBrandCode(),
				                       vo.getBrandType(),
				                       vo.getBrandName(),
				                       vo.getStatus(),
				                       vo.getTenantId());

		callback.accept(page);
	}

	/**
	 * 查询详情
	 */
	//call PRODUCTBRAND_INFO_PROCEDURE(1);
	private String listone_sql = "{CALL PRODUCTBRAND_INFO_PROCEDURE(?)}";
	public void queryById(Callback<ProductBrand> callback, ProductBrandVO vo)
			throws GenericException {

		ProductBrand product = super.callProcQuerySingle(ProductBrand.class,
				listone_sql, vo.getBrandId());

		callback.accept(product);
	}
}
