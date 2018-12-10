package com.qloudbiz.product.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.utils.PKUtils;
import com.qloudbiz.product.dao.AttributeDao;
import com.qloudbiz.product.pojo.Attribute;
import com.qloudbiz.product.service.AttributeService;
import com.qloudbiz.product.vo.AttributeVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class AttributeServiceImpl implements AttributeService{
	
	private final static Logger logger = LoggerFactory.getLogger(AttributeServiceImpl.class);
	
	private AttributeDao attributeDao = new AttributeDao();

	/**
	 * 保存
	 */
	@Override
	public void save(Callback<Attribute> callback, AttributeVO vo)
			throws GenericException {
		logger.debug(">>>>>>>>>>>>>> AttributeService save method start");
		//设置主键等
		vo.setAttributeId(PKUtils.genPK());
		vo.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		vo.setCreatorId("1");
		vo.setCreatorName("admin");
		vo.setTenantId("1");
		attributeDao.save(result -> {
			logger.debug(">>>>>>>>>>>>>>>>AttributeService Method :save Attribute Callback");
			callback.accept(result);
		}, vo);
	}

	
	/**
	 * 删除
	 */
	@Override
	public void delete(Callback<Integer> callback, AttributeVO vo)
			throws GenericException {
		attributeDao.delete(rownum -> {
			callback.accept(rownum);
		}, vo);
	}

	
	/**
	 * 查询详情
	 */
	@Override
	public void queryInfo(Callback<Attribute> callback, AttributeVO vo)
			throws GenericException {
		logger.debug(">>>>>>>>>>>>>> AttributeService query info method start");
		attributeDao.queryById(attribute -> {
			callback.accept(attribute);
		} ,vo);
	}

}
