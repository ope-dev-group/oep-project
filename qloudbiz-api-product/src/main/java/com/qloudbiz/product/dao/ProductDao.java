package com.qloudbiz.product.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;








import com.qloudbiz.core.dao.BaseDao;

import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.core.result.ResultData;
import com.qloudbiz.core.utils.ConnectionUtils;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudfin.qloudbus.reactive.Callback;

public class ProductDao extends BaseDao<Product> {
	private final static Logger logger = LoggerFactory.getLogger(ProductDao.class);
	
	
	private String save_product_sql = "{CALL QLOUDFLOW_PRODUCT_INSERT_PROCEDURE(?,?,?)}";

	
	public void save(Callback<Object> callback, Product entity) throws Exception {

		// 异步返回结果
		Map<String, Object> result = new HashMap<String, Object>();
		if (entity != null){
			try {
				
				//调用存储过程		
				super.callProcUpdate(save_product_sql, entity.getProductId(),entity.getCode(),entity.getName());		
				
				//返回结果
				callback.accept(ResultDataUtils.success("PROD_0000",null));
			
				return;
			} catch (Exception e) {
				e.printStackTrace();
				
				logger.error("local transaction error, {}", e);
				
				// 异步返回
				callback.accept(ResultDataUtils.error("PROD_0001",null));
				return;
			} 
		}else{
			
			callback.accept(ResultDataUtils.error("SYS_0000",null));
		}
		
		
	
		return;

	}

	private String update_sql="{CALL QLOUDFLOW_PRODUCT_UPDATE_PROCEDURE(?,?,?,?)}";

	//update product
	public void update(Callback<Object> callback, Product entity) throws Exception {

		Connection conn=null;
		CallableStatement ps=null;
		try{
		
			logger.debug("update product where id={}",entity.getProductId());
			
			//传入参数
			List<Object> inParams=new ArrayList<Object>();
			inParams.add(entity.getProductId());
			inParams.add(entity.getCode());
			inParams.add(entity.getName());
			
			
			
			//调用存储过程
			//ps =super.callProcedure(update_sql,inParams,outParams);
			Object o=super.callProcUpdateWithOut(update_sql, Types.INTEGER,inParams);
			int count=(Integer)o;
			
			
			
			if(count<=0){
				throw new Exception("Fail to update product "+entity.getProductId());
			}
			
			
			Map<String,String> result=new HashMap<String,String>();
			result.put("msg","Success delete");
			result.put("resultCode","200");
			callback.accept(result);
			return;
		}catch(Exception e){
			
		
			
			e.printStackTrace();
			logger.error("local sql error :{}"+e.getMessage());
			// 异步返回
			Map<String, Object> result = new HashMap<>();
			result.put("error", "Failed to update products!");
			result.put("returnCode", 500);
			callback.accept(result);
			return;
		}
		
	}

	
	//delete product
	private String delete_sql="{CALL QLOUDFLOW_PRODUCT_DELETE_PROCEDURE(?,?)}";
	
	public void delete(Callback<Object> callback, Product entity) throws Exception {
		Connection conn=null;
		CallableStatement ps=null;
		try{
			//conn=getConnection();
			logger.debug("delete product where id={}",entity.getProductId());
			
			ps=conn.prepareCall(delete_sql);
			ps.setString(1,entity.getProductId());
			ps.registerOutParameter(2, Types.INTEGER);
			
			ps.execute();
			int count=ps.getInt(2);
			if(count<=0){
				throw new Exception("Fail to product "+entity.getProductId());
			}
			Map<String,String> result=new HashMap<String,String>();
			result.put("msg","Success delete");
			result.put("resultCode","200");
			callback.accept(result);
			return;
		}catch(Exception e){
			e.printStackTrace();
			logger.error("local sql error :{}"+e.getMessage());
			// 异步返回
			Map<String, Object> result = new HashMap<>();
			result.put("error", "Failed to delete products!");
			result.put("returnCode", 500);
			callback.accept(result);
			return;
		}finally{
			//关闭资源
			//this.closeAll(ps,conn);
		}
	}

	private String listall_sql = "{CALL QLOUDFLOW_PRODUCT_LISTALL_PROCEDURE(?,?)}";

	public void listall(Callback<Object> callback, int startRow, int pageSize)throws Exception {
		
		List<Map> ret = null;
		Connection conn = null;
		CallableStatement ps = null;
		try {
			// 手动提交事务
			//conn = getConnection();

			logger.debug("listall startRow = {} , pageSize = {}", startRow, pageSize);
			
			
			ret=super.callProcQueryList(listall_sql, startRow,pageSize);
			callback.accept(ResultDataUtils.success("PROD_0000",ret));
		} catch (Exception e) {
			logger.error("local sql error, {}", e);
			// 异步返回
			Map<String, Object> result = new HashMap<>();
			result.put("error", "Failed to listall products!");
			result.put("returnCode", 500);
			callback.accept(result);
			return;
		} 
		callback.accept(ret);
		return;
	}
	
	

}
