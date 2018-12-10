package com.qloudbiz.product.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
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
import com.qloudbiz.core.utils.PKUtils;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.pojo.ProductSku;
import com.qloudbiz.product.pojo.ProductSkuAttribute;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductSkuVO;
import com.qloudbiz.product.vo.ProductSkusVO;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductSkuDao extends BaseDao {
	private final static Logger logger = LoggerFactory
			.getLogger(ProductSkuDao.class);

	private String save_productsku_sql = "{CALL PRODUCTSKU_INSERT_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	private String save_productskuattribute_sql = "{CALL PRODUCTSKUATTRIBUTE_INSERT_PROCEDURE(?,?,?,?,?,?)}";

	public void save(Callback<List<ProductSku>> callback, ProductSkusVO vo) throws GenericException {

		logger.debug(">>>>>>>>ProductAttributeDao Save Method The Param Is {}",vo);
		
		//call PRODUCTBRAND_INSERT_PROCEDURE(1,"APPLE","苹果","1","手动添加",1,"1",SYSDATE(),"User_1","张三",SYSDATE(),"User_2","李四",SYSDATE(),"O1","T1");

		//调用保存的存储过程	
		
		List<ProductSku> productSkus=new ArrayList<ProductSku>();
		List<ProductSkuVO> skus=vo.getSkus();
		for(ProductSkuVO sku:skus){
			sku.setSkuId(PKUtils.genPK());
			sku.setCreatorId("1");
			sku.setCreatorName("admin");
			sku.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			sku.setTenantId("1");
			logger.debug("Sku >>>>>>>>>>>>>>"+sku);
			logger.debug("SkuId >>>>>>>>>>>>>>"+sku.getSkuId());
			//保存sku信息
			super.callProcUpdate(save_productsku_sql,sku.getSkuId(),
					sku.getProductId(),
					sku.getSkuCode(),
					sku.getSkuName(),
				    sku.getBarCode(),
				    sku.getStatus(),
				    sku.getStockQty(),
				    sku.getStockAmount(),
				    sku.getCreatedTime(),
				    sku.getCreatorId(),
				    sku.getCreatorName(),
				    sku.getModifyTime(),
				    sku.getModifierId(),
				    sku.getModifierName(),
				    sku.getLastModifyTime(),
				    sku.getTenantId());	
			
			//保存sku 属性值
			List<ProductSkuAttribute> skuAttributes=sku.getSkuAttributes();
			
			if(null!=skuAttributes && !skuAttributes.isEmpty()){
				for(ProductSkuAttribute skuAttribute:skuAttributes){
					skuAttribute.setSkuAttributeId(PKUtils.genPK());
					skuAttribute.setSkuId(sku.getSkuId());
					super.callProcUpdate(save_productskuattribute_sql,
							
							skuAttribute.getSkuAttributeId(),
							skuAttribute.getSkuId(),
							skuAttribute.getAttributeId(),
							skuAttribute.getAttributeName(),
							skuAttribute.getEnumValue(),
							skuAttribute.getEnumText());
				}	    
			}
			
			ProductSku psku=new ProductSku();
			psku.setSkuId(sku.getSkuId());
		}
		
		
		
		//查询保存后的产品Sku数据
		/*ProductSku sku=super.callProcQuerySingle(ProductSku.class, listone_sql,vo.getBrandId());
		callback.accept(product);
		callback.accept(product);*/
		callback.accept(productSkus);

	}

	/*private String update_sql = "{CALL PRODUCTBRAND_UPDATE_PROCEDURE(?,?,?,?,?,?,?,?,?,?,?)}";

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

	delete product
	private String delete_sql = "{CALL PRODUCTBRAND_DELETE_PROCEDURE(?)}";
 */
	/**
	 * 删除
	 * 
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 *//*
	

 

	
	/**
	 * 删除
	 * @param callback
	 * @param vo
	 * @throws GenericException
	 */
	private String delete_sql = "{CALL PRODUCTSKU_DELETE_PROCEDURE(?)}";

	//call PRODUCTBRAND_DELETE_PROCEDURE(1);
	public void delete(Callback<Integer> callback, ProductSkuVO vo)
			throws GenericException {

		Integer rownum = super.callProcUpdate(delete_sql, vo.getSkuId());
		callback.accept(rownum);
	}
	
	/**
	 * 查询详情
	 */
	//call PRODUCTBRAND_INFO_PROCEDURE(1);
	private String listskuone_sql = "{CALL PRODUCTSKU_INFO_PROCEDURE(?)}";
	private String listskuattrone_sql = "{CALL PRODUCTSKUATTTIBUTE_INFO_PROCEDURE(?)}";

	public void queryById(Callback<ProductSku> callback, ProductSku vo)
			throws GenericException {

		//查询sku
		ProductSku productSku = super.callProcQuerySingle(ProductSku.class,
				listskuone_sql, vo.getSkuId());

		
		if(null!=productSku){
			//查询sku属性
			List<ProductSkuAttribute> skuAttributes=callProcQueryList(ProductSkuAttribute.class, listskuattrone_sql, vo.getSkuId());
			productSku.setSkuAttributes(skuAttributes);
		}
		
		callback.accept(productSku);	
	}
}
