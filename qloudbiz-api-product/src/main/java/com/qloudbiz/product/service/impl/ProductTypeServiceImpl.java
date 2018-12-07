package com.qloudbiz.product.service.impl;

import io.advantageous.boon.core.reflection.BeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lcy.test.Menu;
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.utils.ExceptionUtils;
import com.qloudbiz.core.utils.PKUtils;
import com.qloudbiz.product.dao.ProductTypeDao;
import com.qloudbiz.product.pojo.ProductType;
import com.qloudbiz.product.service.ProductTypeService;
import com.qloudbiz.product.vo.ProductTypeVO;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductTypeServiceImpl implements ProductTypeService {

	private final static Logger logger = LoggerFactory.getLogger(ProductTypeServiceImpl.class);
	
	private ProductTypeDao productTypeDao = new ProductTypeDao();
	
	/**
	 * 添加
	 */
	@Override
	public void add(Callback<ProductType> callback, ProductTypeVO vo)
			throws GenericException {
		logger.debug(">>>>>>>>>>>>>> service add method start");
		//设置主键
		vo.setTypeId(PKUtils.genPK());
//		vo.setToTransport(true);
//		vo.setPhysical(true);
		vo.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		vo.setCreatorId("1");
		vo.setCreatorName("admin");
		vo.setTenantId("1");
		productTypeDao.save(result -> {
			logger.debug(">>>>>>>>>>Service Method :save type Callback");
			callback.accept(result);
		},vo);
	}

	
	
	/**
	 * 删除
	 */
	@Override
	public void remote(Callback<Integer> callback, ProductTypeVO vo)
			throws GenericException {
                //同步计数器
				CountDownLatch latch=new CountDownLatch(1);
				ProductType type=new ProductType();
			
				//查询ProductType，验证是否存在此记录
				productTypeDao.queryById(productType->{
					latch.countDown();
					if(null!=productType){
						BeanUtils.copyProperties(productType, type);
					}
				}, vo);
				try {
					//同步等待
					latch.await();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				//记录存在则删除
				if(null !=type && StringUtils.isNotEmpty(type.getTypeId())){
					productTypeDao.delete(rownum->{
						callback.accept(rownum);
					}, vo);
				}else{
					//记录不存在抛出异常
					ExceptionUtils.throwsGenericException("408");
				}
	}

	
	/**
	 * 更新
	 */
	@Override
	public void modify(Callback<Integer> callback, ProductTypeVO vo)
			throws GenericException {
                //同步计数器
				CountDownLatch latch=new CountDownLatch(1);
				ProductType type=new ProductType();
				//查询productTypeDao，验证是否存在此记录
				productTypeDao.queryById(productType->{
					latch.countDown();
					if(null!=productType){
						BeanUtils.copyProperties(productType, type);
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
					productTypeDao.update(rownum->{
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
	public void seachTree(Callback<List<ProductType>> callback, ProductTypeVO vo)
			throws GenericException {
		logger.debug(">>>>>>>>>>>>>> service query tree method start");
		productTypeDao.queryTree(nodes -> {
			
            //1.遍历父节点,查询父节点的子节点
			List<ProductType> productTypes=new ArrayList<ProductType>();
			for(ProductType productType:nodes){
				if(StringUtils.isEmpty(productType.getParentId())){
					productTypes.add(productType);
				}
			}
			
			for(ProductType type:productTypes){
				recur(nodes,type);
			}
			
			callback.accept(productTypes);
		}, vo);
	}
	
    
	
	
	
	/**
	 * 查询详情
	 */
	@Override
	public void seachById(Callback<ProductType> callback, ProductTypeVO vo)
			throws GenericException {
		logger.debug(">>>>>>>>>>>>>> service query info method start");
        productTypeDao.queryById(result -> {
        	callback.accept(result);
        }, vo);
	}
	
	//递归
		public static void  recur(List<ProductType> nodes,ProductType type){
			//根据当前节点ID、查询子节点
			List<ProductType> subs=querySubNodes(nodes,type.getTypeId());
			if(null==subs || subs.isEmpty())return;
			type.setChildren(subs);
			//遍历子节点，根据当前子节点，查询它的子节点
			for(ProductType sub:subs){
				recur(nodes,sub);	
			}
		}
		
		public static List<ProductType> querySubNodes(List<ProductType> nodes,String pid){
			List<ProductType> subs=new ArrayList<ProductType>();
			for(ProductType type: nodes){
				if(null!=type.getParentId() && type.getParentId().equals(pid)){
					subs.add(type);
				}
			}
			return subs;
		}
		
		
}
