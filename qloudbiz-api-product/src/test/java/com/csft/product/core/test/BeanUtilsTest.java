package com.csft.product.core.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.qloudbiz.core.utils.BeanUtils;
import com.qloudbiz.product.pojo.Product;

public class BeanUtilsTest {
	
	@Test
	public void testMapToBean(){
		Map<String,Object> map=new HashMap<String,Object>();
		/*Product product=new Product();
		product.setProductId("P001");
		product.setName("ipad");
		product.setCode("产品编号");*/
		map.put("product_id","U01");
		map.put("product_code","IPAD01");
		map.put("product_name","IPAD");
		
		
		Product product=BeanUtils.mapToObject(map, Product.class);
		
		System.out.println(product);
	}
	
	
	@Test
	public void testBeanToMap(){
		Map<String,Object> map=new HashMap<String,Object>();
		Product product=new Product();
		product.setProductId("P001");
		product.setName("ipad");
		product.setCode("产品编号");
		/*map.put("product_id","U01");
		map.put("product_code","IPAD01");
		map.put("product_name","IPAD");*/
		
		
		Map<String,Object> maps=BeanUtils.objectToMap(product);
		
		System.out.println(maps);
	}
}
