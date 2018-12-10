package com.qloudbiz.product.service.impl;






import io.advantageous.boon.core.reflection.BeanUtils;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

















import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

















import com.csft.product.dao.test.ProductDaoTest;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.ExceptionUtils;
import com.qloudbiz.core.utils.PKUtils;
import com.qloudbiz.product.dao.AttributeGroupDao;
import com.qloudbiz.product.dao.ProductBrandDao;
import com.qloudbiz.product.dao.ProductDao;
import com.qloudbiz.product.dao.ProductTestDao;
import com.qloudbiz.product.pojo.AttributeGroup;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.product.pojo.ProductBrand;
import com.qloudbiz.product.service.AttributeGroupService;
import com.qloudbiz.product.service.ProductBrandService;
import com.qloudbiz.product.service.ProductTestService;
import com.qloudbiz.product.vo.AttributeGroupVO;
import com.qloudbiz.product.vo.ProductBrandVO;
import com.qloudbiz.product.vo.ProductVO;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.reactive.CountDownAsyncLatch;




public class AttributeGroupServiceImpl implements AttributeGroupService{
	private final static Logger logger = LoggerFactory.getLogger(AttributeGroupServiceImpl.class);

	private AttributeGroupDao attributeGroupDao=new AttributeGroupDao();
	
	

	/*@Override
	public void queryList(Callback<PageResultData<AttributeGroup>> callback,AttributeGroupVO vo) throws GenericException {
		logger.debug(">>>>>>>>>>Service Method :queryList  start");

		//业务数据验证，如果业务数据有问题，抛出异常
		//ExceptionUtils.throwsGenericException("400");
	
		
		
		productBrandDao.listall(result->{
		
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Service Method querList Callback");
			
 		}, vo);
	}*/


	@Override
	public void save(Callback<AttributeGroup> callback, AttributeGroupVO vo) throws GenericException{
		logger.debug(">>>>>>>>>>Service Method :save  Start");
		
		vo.setGroupId(PKUtils.genPK());
		
		//设置创建人信息
		vo.setCreatorId("1");
		vo.setCreatorName("admin");
		vo.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		vo.setTenantId("1");
		vo.setOwnerCompanyCode("1");
		
		
		attributeGroupDao.save(result->{
			logger.debug(">>>>>>>>>>Service Method :save  Callback");
			callback.accept(result);
		}, vo);
	}


	@Override
	public void delete(Callback<Integer> callback, AttributeGroupVO vo)throws GenericException {
		
		
			
		attributeGroupDao.delete(rownum->{
			callback.accept(rownum);
		}, vo);
		
	}


	@Override
	public void update(Callback<Integer> callback, AttributeGroupVO vo)throws GenericException {
		
		
		
		
		//记录存在则更新
		vo.setModifierId("1");
		vo.setModifierName("admin");
		vo.setModifyTime(new Timestamp(System.currentTimeMillis()));
		vo.setLastModifyTime(vo.getModifyTime());
	
			
		attributeGroupDao.update(rownum->{
			callback.accept(rownum);
		}, vo);
		
	
	}


	@Override
	public void queryDetail(Callback<AttributeGroup> callback,AttributeGroupVO vo) throws GenericException {
		attributeGroupDao.queryById(brand->{
			callback.accept(brand);
		}, vo);
	}
	
	

	
}
