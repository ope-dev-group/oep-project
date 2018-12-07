package com.qloudbiz.core.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.BeanUtils;
import com.qloudbiz.core.utils.ConnectionUtils;
import com.qloudbiz.core.utils.ExceptionUtils;
import com.qloudbiz.core.utils.PageUtils;
import com.qloudbiz.core.utils.ResultSetUtils;


/**
 * dao基类，提供基础的查询和数据库连接的获取和控制
 * @author Administrator
 *
 */
public  class BaseDao {
	
	private Logger logger=LoggerFactory.getLogger(BaseDao.class);

	/**
	 * 调用存储过程查询，返回单一结果,接收参数列表。
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public <T> T callProcQuerySingle(Class<T> beanClass,String callabelSql,List<Object> params) throws GenericException{
		
		
		Map<String,Object> map=this.callProcQuerySingle(callabelSql, params);
		if(null!=map){
		
			//map转对象
			return BeanUtils.mapToObject(map,beanClass);
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，返回单一结果,接收可变参数
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public <T> T callProcQuerySingle(Class<T> beanClass,String callabelSql,Object... params) throws GenericException{
		
		Map<String,Object> map=this.callProcQuerySingle(callabelSql, params);
		if(null!=map){
			//map转对象
			return BeanUtils.mapToObject(map,beanClass);
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，返回单一结果,不接收参数
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public <T> T callProcQuerySingle(Class<T> beanClass,String callabelSql) throws GenericException{
		Map<String,Object> map=this.callProcQuerySingle(callabelSql);
		if(null!=map){
			//map转对象
			return BeanUtils.mapToObject(map,beanClass);
		}
		return null;
	}
	
	
	
	
	/**
	 * 调用存储过程的查询，返回查询列表，接收列表参数
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public <T> List<T> callProcQueryList(Class<T> beanClass,String callabelSql,List<Object> params) throws GenericException{
		List<Map<String,Object>> list=this.callProcQueryList( callabelSql,params);
		List<T> datas=new ArrayList<T>();
		if(null!=list && !list.isEmpty()){
			for(Map<String,Object> obj:list){
				//map转对象
				T t=BeanUtils.mapToObject(obj,beanClass);
				datas.add(t);
			}
			return datas;
		}
		return null;
	}
	
	
	
	/**
	 * 调用存储过程的查询，返回查询列表,接收可变参数
	 * @param callabelSql  存储过程sql
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> List<T> callProcQueryList(Class<T> beanClass,String callabelSql,Object... params) throws GenericException{
		List<Map<String,Object>> list=this.callProcQueryList( callabelSql,params);
		List<T> datas=new ArrayList<T>();
		if(null!=list && !list.isEmpty()){
			for(Map<String,Object> obj:list){
				//map转对象
				T t=BeanUtils.mapToObject(obj,beanClass);
				datas.add(t);
			}
			return datas;
		}
		return null;
	}
	
	
	
	
	
	/**
	 * 调用存储过程的查询，返回查询列表,不接收参数
	 * @param callabelSql  存储过程sql
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> List<T> callProcQueryList(Class<T> beanClass,String callabelSql) throws GenericException{
		List<Map<String,Object>> list=this.callProcQueryList( callabelSql);
		List<T> datas=new ArrayList<T>();
		if(null!=list && !list.isEmpty()){
			for(Map<String,Object> obj:list){
				//map转对象
				T t=BeanUtils.mapToObject(obj,beanClass);
				datas.add(t);
			}
			return datas;
		}
		return null;
	}
	
	
	
	/**
	 * 调用存储过程的查询，返回单个Map集合，接收参数列表。
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public Map<String,Object> callProcQuerySingle(String callabelSql,List<Object> params) throws GenericException{
		List<Map<String,Object>> maps=callProcQueryList(callabelSql,params);
		if(null!=maps && !maps.isEmpty()){
			return maps.get(0);
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，返回单一Map集合结果，接收可变参数
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public Map<String,Object> callProcQuerySingle(String callabelSql,Object... params) throws GenericException{
		List<Map<String,Object>> maps=callProcQueryList(callabelSql,params);
		if(null!=maps && !maps.isEmpty()){
			return maps.get(0);
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，返回单一Map集合结果，不接收参数列表。
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public Map<String,Object> callProcQuerySingle(String callabelSql) throws GenericException{
		List<Map<String,Object>> maps=callProcQueryList(callabelSql);
		if(null!=maps && !maps.isEmpty()){
			return maps.get(0);
		}
		return null;
	}
	
	
	
	
	/**
	 * 调用存储过程的查询，返回查询List<Map>列表，接收参数列表。
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> callProcQueryList(String callabelSql,List<Object> params) throws GenericException{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		List<Map<String,Object>> resultList=null;
		ResultSet rs=null;
		try{
	
			
			statement = conn.prepareCall(callabelSql);
			
			if (null != statement) {
				if (params != null && !params.isEmpty()) {

					for (int i = 0; i < params.size(); i++) {
						statement.setObject(i + 1, params.get(i));
					}
				}
			}
			
			statement.execute();
			rs=statement.getResultSet();
			if(null!=rs){
				resultList=ResultSetUtils.processResultSet(rs);
			}
	
			
		
		}catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>DataBase SQL Exception "+e.getMessage());
			e.printStackTrace();
			ExceptionUtils.throwsGenericException("300",e.getMessage());//Call PrepareCall Exception!
		}finally{
			
			//关闭资源
			ConnectionUtils.close(statement);
			ConnectionUtils.close(rs);
			
			//如果未开启事务则关闭数据库连接
			try {
				if(!conn.isClosed() && conn.getAutoCommit()){
					ConnectionUtils.closeConnection();
				}
			} catch (SQLException e) {
				logger.error(">>>>>>>>>>>>>>>>>>>>>>>Close Connection EXCEPTION"+e.getMessage());
				ExceptionUtils.throwsGenericException("301",e.getMessage());//关闭连接出错
			}
		}
		
		return resultList;
	}
	
	
	
	/**
	 * 调用存储过程的查询，，返回查询List<Map>列表,接收可变参数
	 * @param callabelSql  存储过程sql
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> callProcQueryList(String callabelSql,Object... params) throws GenericException{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		List<Map<String,Object>> resultList=null;
		ResultSet rs=null;
		try{
		
		Object outParam=null;
		
		
	
			if (null != conn) {
				
				statement = conn.prepareCall(callabelSql);
				if (null != statement) {
					if (params != null && params.length>0) {
	
						for (int i = 0; i < params.length; i++) {
							statement.setObject(i + 1, params[i]);
						}
					}
				}
				
				statement.execute();
				rs=statement.getResultSet();
				if(null!=rs){
					resultList=ResultSetUtils.processResultSet(rs);
				}
			}
		
		}catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>DataBase SQL Exception "+e.getMessage());
			e.printStackTrace();
			ExceptionUtils.throwsGenericException("300",e.getMessage());//Call PrepareCall Exception!
		}finally{
			//关闭资源
			ConnectionUtils.close(statement);
			ConnectionUtils.close(rs);
			
			//如果未开启事务则关闭数据库连接
			try {
				if(!conn.isClosed() && conn.getAutoCommit()){
					ConnectionUtils.closeConnection();
				}
			} catch (SQLException e) {
				logger.error(">>>>>>>>>>>>>>>>>>>>>>>Close Connection EXCEPTION"+e.getMessage());
				ExceptionUtils.throwsGenericException("301",e.getMessage());//关闭连接出错
			}
		}
		
		return resultList;
	}
	
	
	
	
	
	/**
	 * 调用存储过程的查询，返回查询List<Map>列表,不接收参数。
	 * @param callabelSql  存储过程sql
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> callProcQueryList(String callabelSql) throws GenericException{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		ResultSet rs=null;
		List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
		try{
	
			if (null != conn) {
				
				statement = conn.prepareCall(callabelSql);
				
				statement.execute();
				rs=statement.getResultSet();
				if(null!=rs){
					resultList=ResultSetUtils.processResultSet(rs);
				}
			}
		
		}catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>DataBase SQL Exception "+e.getMessage());
			e.printStackTrace();
			ExceptionUtils.throwsGenericException("300",e.getMessage());//Call PrepareCall Exception!
		}finally{
		
			//关闭资源
			ConnectionUtils.close(statement);
			ConnectionUtils.close(rs);
			
			//如果未开启事务则关闭数据库连接
			try {
				if(!conn.isClosed() && conn.getAutoCommit()){
					ConnectionUtils.closeConnection();
				}
			} catch (SQLException e) {
				logger.error(">>>>>>>>>>>>>>>>>>>>>>>Close Conection Exception "+e.getMessage());
				ExceptionUtils.throwsGenericException("301",e.getMessage());//关闭连接出错
			}
		}
		
		return resultList;
	}
	
	
	/**
	 * 调用存储过程的查询，接收可变参数，返回分页对象
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> PageResultData<T> callProcQueryPage(Class<T> beanClass,String callabelSql,int currentNum,int pagePerNum,Object ...params) throws GenericException{
		
		
		//查询总记录数
		PageResultData<Map<String,Object>> page=this.callProcQueryPage(callabelSql,currentNum, pagePerNum,params);
		
		List<T> datas=new ArrayList<T>();
		if(null!=page && null!=page.getResult() && !page.getResult().isEmpty()){
			List<Map<String,Object >> list=page.getResult();
			for(Map<String,Object> obj:list){
				//map转对象
				datas.add(BeanUtils.mapToObject(obj, beanClass));
			}
			PageResultData<T> pageData=new PageResultData<T>();
			io.advantageous.boon.core.reflection.BeanUtils.copyProperties(page, pageData);
			pageData.setResult(datas);
			
			return pageData;
			
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，返回分页对象，接收分页参数，不接收其它参数。
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> PageResultData<T> callProcQueryPage(Class<T> beanClass,String callabelSql,int currentNum,int pagePerNum) throws GenericException{
		
		
	
		
		
		//查询总记录数
		PageResultData<Map<String,Object>> page=this.callProcQueryPage(callabelSql, currentNum,pagePerNum,Collections.EMPTY_LIST);
		
		List<T> datas=new ArrayList<T>();
		if(null!=page && null!=page.getResult() && !page.getResult().isEmpty()){
			List<Map<String,Object >> list=page.getResult();
			for(Map<String,Object> obj:list){
				//map转对象
				datas.add(BeanUtils.mapToObject(obj, beanClass));
			}
			PageResultData<T> pageData=new PageResultData<T>();
			io.advantageous.boon.core.reflection.BeanUtils.copyProperties(page, pageData);
			pageData.setResult(datas);
			return pageData;
			
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，返回分页对象，接收分页参数和列表参数
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> PageResultData<T> callProcQueryPage(Class<T> beanClass,String callabelSql,int currentNum,int pagePerNum,List<Object> params) throws GenericException{
		
	
		
		//查询总记录数
		PageResultData<Map<String,Object>> page=this.callProcQueryPage(callabelSql, currentNum,pagePerNum,params);
		
		List<T> datas=new ArrayList<T>();
		if(null!=page && null!=page.getResult() && !page.getResult().isEmpty()){
			List<Map<String,Object >> list=page.getResult();
			for(Map<String,Object> obj:list){
				//map转对象
				datas.add(BeanUtils.mapToObject(obj, beanClass));
			}
			PageResultData<T> pageData=new PageResultData<T>();
			io.advantageous.boon.core.reflection.BeanUtils.copyProperties(page, pageData);
			pageData.setResult(datas);
			return pageData;
			
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，返回分页Map集合，接收分页参数和可变参数。
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public PageResultData<Map<String,Object>> callProcQueryPage(String callabelSql,int currentNum,int pagePerNum,Object ...params) throws GenericException{
		
		
		//查询总记录数
		int totalCount=0;
		
		String count="0";
		Map<String,Object> maps=null;
		List<Object> pageParams=new ArrayList<Object>();
		if(null!=params && params.length>0){
			
			pageParams.add(-1);
			pageParams.add(pagePerNum);
			pageParams.addAll(Arrays.asList(params));
			maps=this.callProcQuerySingle(callabelSql,pageParams);
		}else{
			maps=this.callProcQuerySingle(callabelSql,-1,pagePerNum);
		}
		
		if(null!=maps) {
			for(String key:maps.keySet()){
				count=maps.get(key)+"";
				break;
			}
		}
		
		totalCount=Integer.parseInt(count);
		
		//设置分页
		PageUtils pageUtils=new PageUtils(pagePerNum,totalCount,currentNum);

		if(totalCount<0){
			totalCount=0;
		}
	
		PageResultData<Map<String,Object>> page = new PageResultData(pageUtils.getTotalCount(),
				pageUtils.getPageSize(), pageUtils.getCurrentPage(),
				pageUtils.getTotalPage(), pageUtils.getPrePage(),
				pageUtils.getNextPage());
				
	
		List<Object> pageParams2=new ArrayList<Object>();

		if(totalCount>0){
			
			
			//计算开始索引
			int startIndex=pageUtils.getStartIndex();
			
			List<Map<String,Object>> items=null;
			
			pageParams2.add(startIndex);
			pageParams2.add(pagePerNum);
			
			//查询分页数据
			if(null!=params && params.length>0){
				pageParams2.addAll(Arrays.asList(params));
				items=this.callProcQueryList(callabelSql,pageParams2);
				
			}else{
				 items=this.callProcQueryList(callabelSql,pageParams2);
				 page.setResult(items);
			}
			page.setResult(items);
		}
		return page;
	}
	
	
	/**
	 * 调用存储过程的查询，，返回分页的Map集合，接收分页参数
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public PageResultData<Map<String,Object>> callProcQueryPage(String callabelSql,int currentNum,int pagePerNum) throws GenericException{
		
		
		return this.callProcQueryPage(callabelSql, currentNum,pagePerNum,Collections.EMPTY_LIST);
	}
	
	
	/**
	 * 调用存储过程的查询，返回分页Map集合对象,接收列表参数。
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public PageResultData<Map<String,Object>> callProcQueryPage(String callabelSql,int currentNum,int pagePerNum,List<Object> params) throws GenericException{
		//查询总记录数
		int totalCount=0;
		
		String count="0";
		Map<String,Object> maps=null;
		
		List<Object> pageParams=new ArrayList<Object>();
		pageParams.add(-1);
		pageParams.add(pagePerNum);
	
		if(null!=params && !params.isEmpty()){
			pageParams.addAll(params);
			maps=this.callProcQuerySingle(callabelSql,pageParams);
		}else{
			maps=this.callProcQuerySingle(callabelSql,pageParams);
		}
		
		if(null!=maps) {
			for(String key:maps.keySet()){
				count=maps.get(key)+"";
				break;
			}
		}
		
		totalCount=Integer.parseInt(count);
		if(totalCount<0) {
			totalCount=0;
		}
		
		
		
		//设置分页
		PageUtils pageUtils=new PageUtils(pagePerNum,totalCount,currentNum);

		
	
		PageResultData<Map<String,Object>> page = new PageResultData(pageUtils.getTotalCount(),
				pageUtils.getPageSize(), pageUtils.getCurrentPage(),
				pageUtils.getTotalPage(), pageUtils.getPrePage(),
				pageUtils.getNextPage());
		if(totalCount>0){
			
			
			//计算开始索引
			int startIndex=pageUtils.getStartIndex();
			
			List<Map<String,Object>> items=null;
			

			List<Object> pageParams2=new ArrayList<Object>();
			pageParams2.add(startIndex);
			pageParams2.add(pagePerNum);
			
			//查询分页数据
			if(null!=params && !params.isEmpty()){
			
				pageParams2.addAll(params);
				items=this.callProcQueryList(callabelSql,pageParams2);

			}else{
				 items=this.callProcQueryList(callabelSql,pageParams2);

			}
			
			//设置分页的列表数据
			pageUtils.setItems(items);
			
			page.setResult(items);
		
			return page;
	
		}
		return page;
		
	}
	
	/**
	 * 
	 * 调用存储过程的增、删、改，不接收传入参数
	 * 存储过程的增删改可以调用此方法
	 * 存储过程没有返回消息
	 * @param callabelSql  存储过程sql
	 * @param params	        传入参数
	 * @return             无返回值
	 */
	public int callProcUpdate(String callabelSql)throws GenericException{
		List param=Collections.EMPTY_LIST;
		return callProcUpdate(callabelSql,param);
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 调用存储过的增，删，改，接收list参数
	 * 存储过程的增删改可以调用此方法
	 * 存储过程没有返回消息
	 * @param callabelSql  存储过程sql
	 * @param params	        传入参数
	 * @return             无返回值
	 */
	public int callProcUpdate(String callabelSql,List<Object> params)throws GenericException{

		System.out.println("+++++++++++++++thread:" + Thread.currentThread());
		
		// 获取连接
		Connection conn = ConnectionUtils.getConnection();
		CallableStatement statement = null;
		int updateCount=0;
		try{
			
			if (null != conn) {
				
				statement = conn.prepareCall(callabelSql);
				if (params != null && !params.isEmpty()&& null != statement) {
	
					for (int i = 0; i < params.size(); i++) {
						statement.setObject(i + 1, params.get(i));
					}
				}
	
				
				
				updateCount=statement.getUpdateCount();
			}
		}catch (Exception e) {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>DataBase SQL Exception "+e.getMessage());
			e.printStackTrace();
			ExceptionUtils.throwsGenericException("300",e.getMessage());//Call PrepareCall Exception!
		}finally{
			ConnectionUtils.close(statement);
			
			//如果未开启事务则关闭数据库连接
			try {
				if(!conn.isClosed() && conn.getAutoCommit()){
					ConnectionUtils.closeConnection();
				}
			} catch (SQLException e) {
				logger.error(">>>>>>>>>>>>>>>>>>>>>>>Close Connection EXCEPTION"+e.getMessage());
				ExceptionUtils.throwsGenericException("301",e.getMessage());//关闭连接出错
			}
		}
		
		return updateCount;
	}
	
	
	/**
	 * 
	 * 1.调用存储过程的增、删改，接收可变参数
	 * 2.存储过程的增、删、改可以调用此方法
	 * 3.存储过程没有返回消息
	 * @param callabelSql  存储过程sql
	 * @param params	        传入参数,可变参数
	 * @return             无返回值
	 */
	public int callProcUpdate(String callabelSql,Object ... params)throws GenericException{
		int updateCount = 0;
		Connection conn=null;
		CallableStatement statement	= null; 
		try {
		// 获取连接
		conn = ConnectionUtils.getConnection();

	
		
	
		
			System.out.println("+++++++++++++++thread:"
					+ Thread.currentThread());
			
			

			if (null != conn) {

				statement = conn.prepareCall(callabelSql);
				if (params != null && params.length > 0 && null != statement) {

					for (int i = 0; i < params.length; i++) {
						statement.setObject(i + 1, params[i]);
					}
				}

				statement.execute();
				updateCount = statement.getUpdateCount();
			}
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>>>>>>>>>>DataBase SQL Exception "
					+ e.getMessage());
			e.printStackTrace();
			ExceptionUtils.throwsGenericException("300", e.getMessage());// Call
																			// PrepareCall
			throw new GenericException();												// Exception!
		} finally {
			ConnectionUtils.close(statement);

			// 如果未开启事务则关闭数据库连接
			try {
				if (!conn.isClosed() && conn.getAutoCommit()) {
					ConnectionUtils.closeConnection();
				}
			} catch (Exception e) {
				logger.debug(">>>>>>>>>>>>>>>>>>>>>>>Close Exception EXCEPTION"
						+ e.getMessage());
				ExceptionUtils.throwsGenericException("301", e.getMessage());// 关闭连接出错
			}
		}

		return updateCount;
	}
}
