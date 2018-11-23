package com.csft.product.dao.test;


import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.dao.DbInit;
import com.qloudbiz.product.dao.ProductDao;
import com.qloudbiz.product.pojo.Product;
import com.qloudbiz.core.utils.ConnectionUtils;



public class ProductDaoTest {
	
	private final static Logger logger = LoggerFactory.getLogger(ProductDaoTest.class);

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
	public void testSave() {
		logger.debug("++++++Test save product ....");
		
	
		
		ProductDao dao=new ProductDao();
		Product product=new Product();
		product.setProductId(UUID.randomUUID().toString().replace("-",""));
		product.setCode("Ipad");
		product.setName("联想-T50");
		
		
		logger.debug("++++++INSERT INTO PRODUCT ....");
		try {
			dao.save(result->{
				logger.debug("test save product {}",result);
			}, product);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testQuerylist(){
		logger.debug("++++++Test query  product list....");
		ProductDao dao=new ProductDao();
		try {
			dao.listall(result->{
				logger.debug("query all product result{}",result);
			}, 0, 10);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Ignore
	public void testDeleteProduct(){
		logger.debug("++++++Test delete  product....");
		ProductDao dao=new ProductDao();
		Product product=new Product();
		product.setProductId("828c4409bdc4481583618ac67532d304");
		try {
			dao.delete(result->{
				logger.debug("delete  product result{}",result);
			}, product);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateProduct(){
		logger.debug("++++++Test delete  product....");
		ProductDao dao=new ProductDao();
		Product product=new Product();
		product.setProductId("72ea69fef20c4ce992cc04013c7917e5");
		product.setCode("P002");
		product.setName("Ipad mini 6");
		try {
			dao.update(result->{
				logger.debug("update  product result{}",result);
			}, product);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
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
		logger.debug("++++++Test save product ....");
		
	
		ConnectionUtils.beginTransaction();
		
		ProductDao dao=new ProductDao();
		Product product=new Product();
		product.setProductId(UUID.randomUUID().toString().replace("-",""));
		product.setCode("Ipad");
		product.setName("联想-T50");
		
	
		
		
		
		
		logger.debug("++++++INSERT INTO PRODUCT ....");
		try {
			dao.save(result->{
				logger.debug("test save product {}",result);
				ConnectionUtils.commitTransaction();
				ConnectionUtils.closeConnection();
			}, product);
		} catch (Exception e) {
			
			e.printStackTrace();
			fail();
		}
	}
}
