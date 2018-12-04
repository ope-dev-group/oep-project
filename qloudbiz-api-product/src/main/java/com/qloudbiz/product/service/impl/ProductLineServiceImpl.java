package com.qloudbiz.product.service.impl;

import io.advantageous.boon.core.reflection.BeanUtils;

import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.ExceptionUtils;
import com.qloudbiz.core.utils.PKUtils;
import com.qloudbiz.product.dao.ProductLineDao;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.service.ProductLineService;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductLineServiceImpl implements ProductLineService{
	
	private final static Logger logger = LoggerFactory.getLogger(ProductLineServiceImpl.class);
	
	private ProductLineDao productLineDao = new ProductLineDao();
	

	/**
	 * 查询列表
	 */
	@Override
	public void queryList(Callback<PageResultData<ProductLine>> callback,
			ProductLineVO vo) throws GenericException {
		logger.debug(">>>>>>>>>>>>>> service query method start");
		productLineDao.listall(result->{
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> queryList is error in dao");
		},vo);
	}

	
	/**
	 * 查询详情
	 */
	@Override
	public void queryInfo(Callback<ProductLine> callback, ProductLineVO vo)
			throws GenericException {
		logger.debug(">>>>>>>>>>>>>> service query info method start");
		productLineDao.queryById(productLine -> {
			callback.accept(productLine);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> query info is error in dao");
		} ,vo);
	}

    
	/**
	 * 保存
	 */
	@Override
	public void save(Callback<ProductLine> callback, ProductLineVO vo)
			throws GenericException {
		logger.debug(">>>>>>>>>>>>>> service save method start");
		//设置主键
		vo.setLineId(PKUtils.genPK());
		vo.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		vo.setCreatorId("1");
		vo.setCreatorName("admin");
		vo.setTenantId("1");
		productLineDao.save(result -> {
			logger.debug(">>>>>>>>>>Service Method :save line Callback");
			callback.accept(result);
		}, vo);
	}


	/**
	 * 删除
	 */
	@Override
	public void delete(Callback<Integer> callback, ProductLineVO vo)
			throws GenericException {
		        //同步计数器
				CountDownLatch latch=new CountDownLatch(1);
				ProductLine line=new ProductLine();
			
				//查询product，验证是否存在此记录
				productLineDao.queryById(productLine->{
					latch.countDown();
					if(null!=productLine){
						BeanUtils.copyProperties(productLine, line);
					}
				}, vo);
				try {
					//同步等待
					latch.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				//记录存在则删除
				if(null !=line && StringUtils.isNotEmpty(line.getLineId())){
					productLineDao.delete(rownum->{
						callback.accept(rownum);
					}, vo);
				}else{
					//记录不存在抛出异常
					ExceptionUtils.throwsGenericException("408");
				}	
	}


	/**
	 * 修改
	 */
	@Override
	public void update(Callback<Integer> callback, ProductLineVO vo)
			throws GenericException {
		        //同步计数器
				CountDownLatch latch=new CountDownLatch(1);
				ProductLine line=new ProductLine();
				//查询productLine，验证是否存在此记录
				productLineDao.queryById(productLine->{
					latch.countDown();
					if(null!=productLine){
						BeanUtils.copyProperties(productLine, line);
					}
				}, vo);
				try {
					//同步等待
					latch.await();
				} catch (InterruptedException e1) {	
					e1.printStackTrace();
				}
				//记录存在则更新
				line.setModifierId("1");
				line.setModifierName("admin");
				line.setModifyTime(new Timestamp(System.currentTimeMillis()));
				line.setLastModifyTime(line.getModifyTime());
				if(null!=line && StringUtils.isNotEmpty(line.getLineId())){
					productLineDao.update(rownum->{
						callback.accept(rownum);
					}, vo);
				}else{
					//记录不存在抛出异常
					ExceptionUtils.throwsGenericException("408");
				}
	}


}
