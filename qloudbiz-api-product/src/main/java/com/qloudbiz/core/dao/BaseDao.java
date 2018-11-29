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


import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.BeanUtils;
import com.qloudbiz.core.utils.ConnectionUtils;
import com.qloudbiz.core.utils.PageUtils;
import com.qloudbiz.core.utils.ResultSetUtils;


/**
 * dao基类，提供基础的查询和数据库连接的获取和控制
 * @author Administrator
 *
 */
public  class BaseDao{

	/**
	 * 调用存储过程的查询，接收参数列表，返回单一结果
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public <T> T callProcQuerySingle(Class<T> beanClass,String callabelSql,List<Object> params) throws Exception{
		
		
		Map<String,Object> map=this.callProcQuerySingle(callabelSql, params);
		if(null!=map){
		
			//map转对象
			return BeanUtils.mapToObject(map,beanClass);
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，接收参数列表，返回单一结果
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public <T> T callProcQuerySingle(Class<T> beanClass,String callabelSql,Object... params) throws Exception{
		
		Map<String,Object> map=this.callProcQuerySingle(callabelSql, params);
		if(null!=map){
			//map转对象
			return BeanUtils.mapToObject(map,beanClass);
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，接收参数列表，返回单一结果
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public <T> T callProcQuerySingle(Class<T> beanClass,String callabelSql) throws Exception{
		Map<String,Object> map=this.callProcQuerySingle(callabelSql);
		if(null!=map){
			//map转对象
			return BeanUtils.mapToObject(map,beanClass);
		}
		return null;
	}
	
	
	
	
	/**
	 * 调用存储过程的查询，接收参数列表，返回查询列表
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public <T> List<T> callProcQueryList(Class<T> beanClass,String callabelSql,List<Object> params) throws Exception{
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
	 * 调用存储过程的查询，接收参数列表，返回查询列表
	 * @param callabelSql  存储过程sql
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> List<T> callProcQueryList(Class<T> beanClass,String callabelSql,Object... params) throws Exception{
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
	 * 调用存储过程的查询，不接收参数，返回查询列表
	 * @param callabelSql  存储过程sql
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> List<T> callProcQueryList(Class<T> beanClass,String callabelSql) throws Exception{
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
	 * 调用存储过程的查询，接收参数列表，返回单一结果
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public Map<String,Object> callProcQuerySingle(String callabelSql,List<Object> params) throws Exception{
		List<Map<String,Object>> maps=callProcQueryList(callabelSql,params);
		if(null!=maps && !maps.isEmpty()){
			return maps.get(0);
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，接收参数列表，返回单一结果
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public Map<String,Object> callProcQuerySingle(String callabelSql,Object... params) throws Exception{
		List<Map<String,Object>> maps=callProcQueryList(callabelSql,params);
		if(null!=maps && !maps.isEmpty()){
			return maps.get(0);
		}
		return null;
	}
	
	
	/**
	 * 调用存储过程的查询，接收参数列表，返回单一结果
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public Map<String,Object> callProcQuerySingle(String callabelSql) throws Exception{
		List<Map<String,Object>> maps=callProcQueryList(callabelSql);
		if(null!=maps && !maps.isEmpty()){
			return maps.get(0);
		}
		return null;
	}
	
	
	
	
	/**
	 * 调用存储过程的查询，接收参数列表，返回查询列表
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> callProcQueryList(String callabelSql,List<Object> params) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		List<Map<String,Object>> resultList=null;
		ResultSet rs=null;
		try{
	
			if (null != conn) {
				
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
			}
			
		
		}finally{
			
			//关闭资源
			ConnectionUtils.close(statement);
			ConnectionUtils.close(rs);
			
			//如果未开启事务则关闭数据库连接
			if(!conn.isClosed() && conn.getAutoCommit()){
				ConnectionUtils.closeConnection();
			}
		}
		
		return resultList;
	}
	
	
	
	/**
	 * 调用存储过程的查询，接收参数列表，返回查询列表
	 * @param callabelSql  存储过程sql
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> callProcQueryList(String callabelSql,Object... params) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		List<Map<String,Object>> resultList=null;
		ResultSet rs=null;
		try{
	
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
		
		}finally{
			//关闭资源
			ConnectionUtils.close(statement);
			ConnectionUtils.close(rs);
			
			//如果未开启事务则关闭流
			if(!conn.isClosed() && conn.getAutoCommit()){
				ConnectionUtils.closeConnection();
			}
		}
		
		return resultList;
	}
	
	
	
	
	
	/**
	 * 调用存储过程的查询，不接收参数，返回查询列表
	 * @param callabelSql  存储过程sql
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public List<Map<String,Object>> callProcQueryList(String callabelSql) throws Exception{
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
		
		}finally{
		
			//关闭资源
			ConnectionUtils.close(statement);
			ConnectionUtils.close(rs);
			
			//如果未开启事务则关闭流
			if(!conn.isClosed() && conn.getAutoCommit()){
				ConnectionUtils.closeConnection();
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
	public <T> PageResultData<T> callProcQueryPage(Class<T> beanClass,String callabelSql,int currentNum,int pagePerNum,Object ...params) throws Exception{
		
		
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
	 * 调用存储过程的查询，不接收参数，返回分页对象
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> PageResultData<T> callProcQueryPage(Class<T> beanClass,String callabelSql,int currentNum,int pagePerNum) throws Exception{
		
		
	
		
		
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
	 * 调用存储过程的查询，不接收参数，返回分页对象
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public <T> PageResultData<T> callProcQueryPage(Class<T> beanClass,String callabelSql,int currentNum,int pagePerNum,List<Object> params) throws Exception{
		
	
		
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
	 * 调用存储过程的查询，接收可变参数，返回分页对象
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public PageResultData<Map<String,Object>> callProcQueryPage(String callabelSql,int currentNum,int pagePerNum,Object ...params) throws Exception{
		
		
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
	 * 调用存储过程的查询，不接收参数，返回分页对象
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public PageResultData<Map<String,Object>> callProcQueryPage(String callabelSql,int currentNum,int pagePerNum) throws Exception{
		
		
		return this.callProcQueryPage(callabelSql, currentNum,pagePerNum,Collections.EMPTY_LIST);
	}
	
	
	/**
	 * 调用存储过程的查询，不接收参数，返回分页对象
	 * @param callabelSql  存储过程sql
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public PageResultData<Map<String,Object>> callProcQueryPage(String callabelSql,int currentNum,int pagePerNum,List<Object> params) throws Exception{
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
	 * 调用存储过程的增、删、改，没有传入参数
	 * 存储过程的增删改可以调用此方法
	 * 存储过程没有返回消息
	 * @param callabelSql  存储过程sql
	 * @param params	        传入参数
	 * @return             无返回值
	 */
	public int callProcUpdate(String callabelSql)throws Exception{
		List param=Collections.EMPTY_LIST;
		return callProcUpdate(callabelSql,param);
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 调用存储过程，接收list参数
	 * 存储过程的增删改可以调用此方法
	 * 存储过程没有返回消息
	 * @param callabelSql  存储过程sql
	 * @param params	        传入参数
	 * @return             无返回值
	 */
	public int callProcUpdate(String callabelSql,List<Object> params)throws Exception{

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
		}finally{
			ConnectionUtils.close(statement);
			
			//如果未开启事务则关闭流
			if(conn.getAutoCommit()){
				ConnectionUtils.close(conn);
			}
		}
		
		return updateCount;
	}
	
	
	/**
	 * 
	 * 1.调用存储过程，接收可变参数
	 * 2.存储过程的增、删、改可以调用此方法
	 * 3.存储过程没有返回消息
	 * @param callabelSql  存储过程sql
	 * @param params	        传入参数,可变参数
	 * @return             无返回值
	 */
	public int callProcUpdate(String callabelSql,Object ... params)throws Exception{

		System.out.println("+++++++++++++++thread:" + Thread.currentThread());
		// 获取连接
		Connection conn = ConnectionUtils.getConnection();

		CallableStatement statement = null;
		int updateCount=0;
		try{
			if (null != conn) {
				
				statement = conn.prepareCall(callabelSql);
				if (params != null && params.length>0 && null != statement) {
	
					for (int i = 0; i < params.length; i++) {
						statement.setObject(i + 1, params[i]);
					}
				}
	
				statement.execute();
				updateCount=statement.getUpdateCount();
			}
		}finally{
			ConnectionUtils.close(statement);
			
			//如果未开启事务则关闭流
			if(conn.getAutoCommit()){
				ConnectionUtils.close(conn);
			}
		}
		
		return updateCount;
	}
	
	
	
	/**
	 * 
	 * 1.调用存储过程，接收参数列表,返回存储过程输入参数。
	 * 2.存储过程的增、删、改可以调用此方法
	 * 3.存储过程没有返回消息
	 * 4.注意：输出参数必须出现在sql的最后一位
	 * @param callabelSql  存储过程sql
	 * @param params	        传入参数,参数列表
	 * @return             无返回值
	 */
	public Object callProcUpdateWithOut(String callabelSql,int outSqlType,List<Object> params) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		try{
	
			if (null != conn) {
				
				statement = conn.prepareCall(callabelSql);
				if (null != statement) {
					if (params != null && !params.isEmpty()) {
	
						for (int i = 0; i < params.size(); i++) {
							statement.setObject(i + 1, params.get(i));
						}
					}
				
					int outParamIndex = (null == params || params.isEmpty()) ? 0 : params.size();
					statement.registerOutParameter(outParamIndex+1,outSqlType);
					
					statement.execute();
					
					outParam=statement.getObject(outParamIndex);
				}
	
			}
		
		}finally{
			ConnectionUtils.close(statement);
			
			//如果未开启事务则关闭流
			if(conn.getAutoCommit()){
				ConnectionUtils.closeConnection();
			}
		}
		
		return outParam;
	}
	
	/**
	 * 
	 * 1.调用存储过程，接收参数list，返回存储过程的输出参数。注意：输出参数必须出现在sql的最后一位
	 * 2.存储过程的增、删、改可以调用此方法
	 * 3.存储过程有返回参数
	 * 4.注意：输出参数必须出现在sql的最后一位
	 * @param callabelSql  存储过程sql
	 * @param params	        传入参数,参数列表
	 * @return             无返回值
	 */
	public Object callProcUpdateWithOut(String callabelSql,int outSqlType,Object ...params) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		try{
	
			if (null != conn) {
				
				statement = conn.prepareCall(callabelSql);
				if (null != statement) {
					if (params != null && params.length>0) {
	
						for (int i = 0; i < params.length; i++) {
							statement.setObject(i + 1, params[i]);
						}
					}
				
					int outParamIndex = (null == params || params.length==0) ? 0 : params.length;
					statement.registerOutParameter(outParamIndex+1,outSqlType);
					
					statement.execute();
					
					outParam=statement.getObject(outParamIndex);
				}
	
			}
		
		}finally{
			ConnectionUtils.close(statement);
			
			//如果未开启事务则关闭流
			if(conn.getAutoCommit()){
				ConnectionUtils.closeConnection();
			}
		}
		
		return outParam;
	}
	
}
