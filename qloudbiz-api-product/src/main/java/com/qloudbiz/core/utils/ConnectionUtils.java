package com.qloudbiz.core.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.product.dao.ProductDao;
import com.qloudfin.sagas.persistence.jdbc.JdbcClient;

/**
 * 数据库连接工具类
 * @author Administrator
 *
 */
public class ConnectionUtils {
		private final static Logger logger = LoggerFactory.getLogger(ConnectionUtils.class);

		private static JdbcClient dbClient = JdbcClient.getInstance();
	
    	//声明一个本地线程变量，用于存放connection
    	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>();
    	
    	
    	public static void beginTransaction() {
    		Connection conn=getConnection();
    		try {
    			if (conn != null) {
    				if (conn.getAutoCommit()) {
    					conn.setAutoCommit(false); //手动提交
    				}
    			}
    		}catch(SQLException e) {}
    	}
    	
    	/**
    	 * 得到Connection
    	 * @return
    	 * @throws SQLException 
    	 */
    	public static Connection getConnection() {

    		Connection conn = connectionHolder.get();
    		//如果在当前线程中没有绑定相应的Connection
    		try {
	    		if (conn == null || conn.isClosed()) {
    				conn = dbClient.poolConnect();
    				
    				connectionHolder.set(conn);
	    		}
	    		

    		} catch (SQLException e) {
    			
			} 
    		
    		return conn;
    	}
    	/**
    	 * 关闭连接，只关闭当前线程的连接
    	 */
    	public static void closeConnection() {
    		Connection conn = connectionHolder.get();
    		if (conn != null) {
    			try {
    				
    			
    				
    				
    				

    				conn.close();
    				
    			
    				//从ThreadLocal中清除Connection
    				connectionHolder.remove();
    				
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}	
    		}
    	}
    	
    	/**
    	 * 关闭数据库连接
    	 * @param conn
    	 */
    	public static void close(Connection conn) {
    		
    		if(null!=conn){
    			try {
    				conn.close();
    			} catch (Exception e) {
    				
    				e.printStackTrace();
    				conn=null;
    			}
    		}
    	}

    	/**
    	 * 关闭 resultset
    	 * @param rs
    	 */
    	public static void close(ResultSet rs) {
    		
    		if(null!=rs){
    			try {
    				rs.close();
    			} catch (Exception e) {
    				
    				e.printStackTrace();
    				rs=null;
    			}
    		}
    	}

    	/**
    	 * 关闭statement
    	 * @param stmt
    	 */
    	public static void close(Statement stmt) {
    		
    		if(null!=stmt){
    			try {
    				stmt.close();
    			} catch (Exception e) {
    				
    				e.printStackTrace();
    				stmt=null;
    			}
    		}
    	}
    		 
    	/**
    	 * 关闭所有可关闭资源
    	 * 
    	 * @param objs
    	 *            可关闭的资源对象有Connection、Statement、ResultSet，别的类型资源自动忽略
    	 */
    	public static void closeAll(Object... objs) {
    		for (Object obj : objs) {
    			if (obj instanceof Connection)
    				close((Connection) obj);
    			if (obj instanceof Statement)
    				close((Statement) obj);
    			if (obj instanceof ResultSet)
    				close((ResultSet) obj);
    		}
    	}
    	
    	
    	/**
    	 * 提交事务
    	 * @throws SQLException 
    	 */
    	public static void commitTransaction()  {
    		Connection conn=getConnection();
    		try {
    			if (conn != null) {
    				if (!conn.getAutoCommit()) {
    					conn.commit();
    				}
    			}
    		}catch(SQLException e) {}
    	}
    	
    	/***
    	 * 提交事务
    	 * @param conn
    	 */
    	public static void rollbackTransaction(Connection conn) {
    		try {
    			if (conn != null) {
    				if (!conn.getAutoCommit()) {
    					conn.rollback();
    					
    				}
    			}
    		}catch(SQLException e) {}
    	}
     
    }



    