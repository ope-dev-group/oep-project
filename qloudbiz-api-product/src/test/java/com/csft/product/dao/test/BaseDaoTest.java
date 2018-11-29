package com.csft.product.dao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.qloudbiz.core.dao.BaseDao;
import com.qloudbiz.core.dao.DbInit;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.product.pojo.Product;

public class BaseDaoTest {
	
	@Before
	public void setUp() throws Exception {

		try {
			String cfg = "{\"db\":{\n" + "       \"driver\":\"org.mariadb.jdbc.MariaDbDataSource\",\n"
					+ "       \"ip\":\"192.168.11.130\",\n" + "       \"port\": 3306,\n" + "       \"dbname\":\"productdb\",\n"
					+ "       \"username\":\"root\",\n" + "       \"password\":\"666666\",\n"
					+ "       \"testQuery\":\"select 1\",\n" + "       \"maxPoolSize\": 5,\n"
					+ "       \"minPoolSize\": 5\n" + "    } }";
			System.setProperty("QLOUD_APPLICATION_CONFIG", cfg);
			DbInit.init();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public  void testCallProcQuerySingle(){
		String sql="{CALL QLOUDFLOW_PRODUCT_LISTALL_PROCEDURE_TEST3(?)}";

		try {
			BaseDao baseDao=new BaseDao();
			
			Map<String,Object> map=null;
			//返回Map可变参数传参
			//Map<String,Object> map=baseDao.callProcQuerySingle(sql);
			
			
			//返回map,列表传参
			List<Object> params=new ArrayList<Object>();
			params.add("Ipad");
			
			
/*			map=baseDao.callProcQuerySingle(sql,params);*/
		
			
			//返回map,无参
			//map=baseDao.callProcQuerySingle(sql);
			
			
			//返回对象，无参
			Product product=null;
/*			product=baseDao.callProcQuerySingle(Product.class, sql);
*/			
			//返回对象,可变参数
		/*	product=baseDao.callProcQuerySingle(Product.class, sql, "联想");*/
			
			//返回对象，列表参数
			
			product=baseDao.callProcQuerySingle(Product.class, sql, params);
			
			
			//输出结果
			System.out.println(product);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public  void testCallProcQueryList(){
		String sql="{CALL QLOUDFLOW_PRODUCT_LISTALL_PROCEDURE_TEST3(?)}";

		try {
			BaseDao baseDao=new BaseDao();
			
			List<Map<String,Object>> map=null;
			//返回Map可变参数传参
			//Map<String,Object> map=baseDao.callProcQuerySingle(sql);
			
			
			//返回map,列表传参
			List<Object> params=new ArrayList<Object>();
			params.add("Ipad");
			
			/*map=baseDao.callProcQueryList(sql,params);*/
			
		
			
			//返回map,无参
			//map=baseDao.callProcQueryList(sql);
			
			
			//返回对象，无参
			List<Product> products=null;
			/*products=baseDao.callProcQueryList(Product.class, sql);*/
			
			//返回对象,可变参数
			/*products=baseDao.callProcQueryList(Product.class, sql, "联想");*/
			
			//返回对象，列表参数
			products=baseDao.callProcQueryList(Product.class, sql, params);
			
			
			//输出结果
			System.out.println(products);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public  void testCallProcQueryPage(){
		String sql="{CALL QLOUDFLOW_PRODUCT_LISTALL_PROCEDURE_TEST4(?,?,?)}";

		try {
			BaseDao baseDao=new BaseDao();
			
			PageResultData<Map<String,Object>> page=null;
			//返回Map可变参数传参
			//Map<String,Object> map=baseDao.callProcQuerySingle(sql);
			
			
			//返回map,列表传参
			List<Object> params=new ArrayList<Object>();
			params.add("ThinkPad");
			
			//page=baseDao.callProcQueryPage(sql,1,4,params);
			
		
			
			//返回map,无参
			//page=baseDao.callProcQueryPage(sql,3,4);
			
			
			//返回对象，无参
			PageResultData<Product> page2=null;

			/*page2=baseDao.callProcQueryPage(Product.class, sql,3,4);*/
			
			//返回对象,可变参数
			//page2=baseDao.callProcQueryPage(Product.class, sql,3,4,"联想");			
			
			//返回对象，列表参数
			page2=baseDao.callProcQueryPage(Product.class, sql,3,4,params);
			
			/*List<Product> products=page2.getResult();
			for(Product product:products){
				System.out.print(product.getProductId()+"==="+product.getCode()+"======"+product.getName());
			}*/
			//输出结果
			System.out.println(page2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
