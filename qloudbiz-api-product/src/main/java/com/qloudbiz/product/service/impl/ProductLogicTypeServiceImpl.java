package com.qloudbiz.product.service.impl;

import io.advantageous.boon.core.reflection.BeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.ExceptionUtils;
import com.qloudbiz.core.utils.PKUtils;
import com.qloudbiz.product.dao.ProductLogicTypeDao;
import com.qloudbiz.product.pojo.ProductLogicType;
import com.qloudbiz.product.pojo.ProductType;
import com.qloudbiz.product.service.ProductLogicTypeService;
import com.qloudbiz.product.vo.ProductLogicTypeVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductLogicTypeServiceImpl implements ProductLogicTypeService{

	private final static Logger logger = LoggerFactory.getLogger(ProductTypeServiceImpl.class);
	
	private ProductLogicTypeDao productLogicTypeDao = new ProductLogicTypeDao();
	
	
	/**
	 * 增加
	 */
	@Override
	public void add(Callback<ProductLogicType> callback, ProductLogicTypeVO vo)
			throws GenericException {
		
		logger.debug(">>>>>>>>>>>>>> ProductLogicTypeService add method start");
		//设置主键
		vo.setTypeId(PKUtils.genPK());
		vo.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		vo.setCreatorId("1");
		vo.setCreatorName("admin");
		vo.setTenantId("1");
		productLogicTypeDao.save(result -> {
			logger.debug(">>>>>>>>>>ProductLogicTypeService Method :save type Callback");
			callback.accept(result);
		},vo);
		
	}

	
	/**
	 * 删除
	 */
	@Override
	public void remote(Callback<Integer> callback, ProductLogicTypeVO vo)
			throws GenericException {
		 //同步计数器
		CountDownLatch latch=new CountDownLatch(1);
		ProductLogicType logicType=new ProductLogicType();
	
		//查询productLogicTypeDao，验证是否存在此记录
		productLogicTypeDao.queryById(productdLogicType->{
			latch.countDown();
			if(null!=productdLogicType){
				BeanUtils.copyProperties(productdLogicType, logicType);
			}
		}, vo);
		try {
			//同步等待
			latch.await();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//记录存在则删除
		if(null !=logicType && StringUtils.isNotEmpty(logicType.getTypeId())){
			productLogicTypeDao.delete(rownum->{
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
	public void modify(Callback<Integer> callback, ProductLogicTypeVO vo)
			throws GenericException {
		//同步计数器
		CountDownLatch latch=new CountDownLatch(1);
		ProductLogicType type=new ProductLogicType();
		//查询productLogicTypeDao，验证是否存在此记录
		productLogicTypeDao.queryById(productLogicType->{
			latch.countDown();
			if(null!=productLogicType){
				BeanUtils.copyProperties(productLogicType, type);
			}
		}, vo);
		try {
			//同步等待
			latch.await();
		} catch (InterruptedException e1) {	
			e1.printStackTrace();
		}
		//记录存在则更新
		vo.setModifierId("1");
		vo.setModifierName("admin");
		vo.setModifyTime(new Timestamp(System.currentTimeMillis()));
		vo.setLastModifyTime(vo.getModifyTime());
		if(null!=type && StringUtils.isNotEmpty(type.getTypeId())){
			productLogicTypeDao.update(rownum->{
				callback.accept(rownum);
			}, vo);
		}else{
			//记录不存在抛出异常
			ExceptionUtils.throwsGenericException("408");
		}  
	}

	
	/**
	 * 查询树
	 */
	@Override
	public void seachTree(Callback<List<ProductLogicType>> callback,
			ProductLogicTypeVO vo) throws GenericException {
		//遍历父节点
		logger.debug(">>>>>>>>>>>>>> ProductLogicTypeService query tree method start");
		productLogicTypeDao.queryTree(nodes -> {
			
            //1.遍历父节点,查询父节点的子节点
			List<ProductLogicType> productLogicTypes=new ArrayList<ProductLogicType>();
			for(ProductLogicType productLogicType:nodes){
				if(StringUtils.isEmpty(productLogicType.getParentId())){
					productLogicTypes.add(productLogicType);
				}
			}
			
			for(ProductLogicType type:productLogicTypes){
				recur(nodes,type);
			}
			
			callback.accept(productLogicTypes);
		}, vo);
	}

	
	/**
	 * 查询详情
	 */
	@Override
	public void seachById(Callback<ProductLogicType> callback,
			ProductLogicTypeVO vo) throws GenericException {
		logger.debug(">>>>>>>>>>>>>> ProductLogicTypeService query info method start");
		productLogicTypeDao.queryById(result -> {
        	callback.accept(result);
        }, vo);
	}

	
    /**
     * 查询全部
     */
	@Override
	public void seachList(Callback<PageResultData<ProductLogicType>> callback,
			ProductLogicTypeVO vo) throws GenericException {
		logger.debug(">>>>>>>>>>>>>> ProductLogicTypeService query method start");		
		productLogicTypeDao.listall(result->{
			callback.accept(result);
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> queryList is error in dao");
		},vo);
	}

	
	        //递归
			public static void  recur(List<ProductLogicType> nodes,ProductLogicType type){
				//根据当前节点ID、查询子节点
				List<ProductLogicType> subs=querySubNodes(nodes,type.getTypeId());
				if(null==subs || subs.isEmpty())return;
				type.setChildren(subs);
				//遍历子节点，根据当前子节点，查询它的子节点
				for(ProductLogicType sub:subs){
					recur(nodes,sub);	
				}
			}
			
			public static List<ProductLogicType> querySubNodes(List<ProductLogicType> nodes,String pid){
				List<ProductLogicType> subs=new ArrayList<ProductLogicType>();
				for(ProductLogicType type: nodes){
					if(null!=type.getParentId() && type.getParentId().equals(pid)){
						subs.add(type);
					}
				}
				return subs;
			}
			

}
