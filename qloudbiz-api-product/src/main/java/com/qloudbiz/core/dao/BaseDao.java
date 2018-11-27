package com.qloudbiz.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.kafka.common.utils.CollectionUtils;

import com.alibaba.fastjson.asm.Type;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.ConnectionUtils;
import com.qloudbiz.core.utils.PageUtils;
import com.qloudbiz.core.utils.ResultSetUtils;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.sagas.persistence.jdbc.JdbcClient;

public abstract class BaseDao<T> {

	private JdbcClient dbClient = JdbcClient.getInstance();

	/*
	 * public Connection getConnection(){ Connection conn=null; try { conn =
	 * dbClient.poolConnect();
	 * 
	 * //设置手动提交 conn.setAutoCommit(false); } catch (SQLException e) {
	 * 
	 * e.printStackTrace(); } if(null==conn){ //抛出异常 } return conn; }
	 */

	/*public abstract void save(final Callback<Object> callback, T entity)
			throws Exception;

	public abstract void update(final Callback<Object> callback, T entity)
			throws Exception;

	public abstract void delete(final Callback<Object> callback, T entity)
			throws Exception;

	public abstract void listall(final Callback<Object> callback, int startRow,
			int pageSize) throws Exception;*/

	
	
	/**
	 * 调用存储过程的查询，接收参数列表，返回单一结果
	 * @param callabelSql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public Map callProcQuerySingle(String callabelSql,List<Object> params) throws Exception{
		List<Map> maps=callProcQueryList(callabelSql,params);
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
	public Map callProcQuerySingle(String callabelSql,Object... params) throws Exception{
		List<Map> maps=callProcQueryList(callabelSql,params);
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
	public Map callProcQuerySingle(String callabelSql) throws Exception{
		List<Map> maps=callProcQueryList(callabelSql);
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
	public List<Map> callProcQueryList(String callabelSql,List<Object> params) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		List<Map> resultList=null;
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
		
		}catch(Exception e){
			ConnectionUtils.rollbackTransaction(conn);
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
	public List<Map> callProcQueryList(String callabelSql,Object... params) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		List<Map> resultList=null;
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
		
		}catch(Exception e){
			ConnectionUtils.rollbackTransaction(conn);
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
	public List<Map> callProcQueryList(String callabelSql) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		DataSource dataSource = null;
		CallableStatement statement = null;
		Object outParam=null;
		ResultSet rs=null;
		List<Map> resultList=new ArrayList<Map>();
		try{
	
			if (null != conn) {
				
				statement = conn.prepareCall(callabelSql);
				
				statement.execute();
				rs=statement.getResultSet();
				if(null!=rs){
					resultList=ResultSetUtils.processResultSet(rs);
				}
			}
		
		}catch(Exception e){
			ConnectionUtils.rollbackTransaction(conn);
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
	 * @currentNum 当前页
	 * @pagePerNum 页大小
	 * @param params	        可变参数列表
	 * @return
	 * @throws SQLException 
	 */
	public PageResultData<List<Map>> callProcQueryPage(String callabelSql,int currentNum,int pagePerNum,Object ...params) throws Exception{
		
		
		//查询总记录数
		int totalCount=0;
		
		String count="0";
		Map<String,Object> maps=null;
		if(null!=params && params.length>0){
			maps=this.callProcQuerySingle(callabelSql,-1,pagePerNum,params);
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
		PageResultData<List<Map>> page=new PageResultData<List<Map>>();
		if(totalCount>0){
			PageUtils pageUtils=new PageUtils(pagePerNum,totalCount,currentNum);
			
			
			//计算开始索引
			int startIndex=pageUtils.getStartIndex();
			
			List<Map> items=null;
			//查询分页数据
			if(null!=params && params.length>0){
				items=this.callProcQueryList(callabelSql,startIndex,pagePerNum,params);

			}else{
				 items=this.callProcQueryList(callabelSql,startIndex,pagePerNum);

			}
			
			pageUtils.setItems(items);
			
			page=new PageResultData<List<Map>>();
			
			
			page.setCurrentNum(pageUtils.getCurrentPage());
			page.setNextPage(pageUtils.getNextPage());
			page.setPagePerNum(pagePerNum);
			page.setPrePage(pageUtils.getPrePage());
			page.setResult(items);
			page.setTotalNum(pageUtils.getTotalCount());
			page.setTotalPage(pageUtils.getTotalPage());
			page.setResultCode("0");
			
		}else{
			
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
		}catch(Exception e){
			//回滚事务
			ConnectionUtils.rollbackTransaction(conn);
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
		}catch(Exception e){
			//回滚事务
			ConnectionUtils.rollbackTransaction(conn);
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
		
		}catch(Exception e){
			//回滚事务
			ConnectionUtils.rollbackTransaction(conn);
		}finally{
			ConnectionUtils.close(statement);
			
			//如果未开启事务则关闭流
			if(conn.getAutoCommit()){
				ConnectionUtils.close(conn);
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
		
		}catch(Exception e){
			//回滚事务
			ConnectionUtils.rollbackTransaction(conn);
		}finally{
			ConnectionUtils.close(statement);
			
			//如果未开启事务则关闭流
			if(conn.getAutoCommit()){
				ConnectionUtils.close(conn);
			}
		}
		
		return outParam;
	}
	
}
