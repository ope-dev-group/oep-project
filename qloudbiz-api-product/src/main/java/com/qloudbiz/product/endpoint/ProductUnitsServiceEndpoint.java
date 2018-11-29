package com.qloudbiz.product.endpoint;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;


/**
 * 产品单位微服务
 *产品单位是产品销售的基本单元，例如一台、一箱，一箱=4台，订单可能按照台或者按照箱进行销售。产品单位之间有固定的换算关系。
 *一般来说，产品单位是针对主物料而言的，产品SKU属性不同，销售单位一般不会有变化。
 *由于产品单位与产品主物料有强烈的关联关系，二者之间是多对多关系，一般都是产品主物料创建完成后，再创建相关的产品单位。
 *
 */
@RequestMapping("/products")
public class ProductUnitsServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductUnitsServiceEndpoint.class);
	
	/**
	 * 添加产品单位
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/units",method=RequestMethod.POST)
	public void addUnits(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add Product Units param is:{}",body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-unit-create.json");		
		
		
		
		StringBuffer sb=new StringBuffer();
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(in,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader=new BufferedReader(isr);
		String  line=null;
		try {
			while((line=reader.readLine())!=null){
				sb.append(line);
				
			}
			reader.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//业务处理
		sb.toString().replaceAll("\t","");
		Object resultObj=JSON.parse(sb.toString());
	
		
		callback.accept(resultObj);	
	}
	
	
	/**
	 * 修改产品单位
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/units/{unitId}",method=RequestMethod.PUT)
	public void updateUnits(Callback<Object> callback,@PathVariable("unitId") String unitId,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>Update units :unitId is:{},param is {}",unitId,body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-unit-create.json");		
		
		
		
		StringBuffer sb=new StringBuffer();
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(in,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader=new BufferedReader(isr);
		String  line=null;
		try {
			while((line=reader.readLine())!=null){
				sb.append(line);
				
			}
			reader.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//业务处理
		sb.toString().replaceAll("\t","");
		Object resultObj=JSON.parse(sb.toString());
	
		
		callback.accept(null);	
	}
	
	/**
	 * 删除产品单位
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/units/{unitId}",method=RequestMethod.DELETE)
	public void deleteUnits(Callback<Object> callback,@PathVariable("unitId") String unitId){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>>>Delete units :unitId is:{}",unitId);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-unit-create.json");		
		
		
		
		StringBuffer sb=new StringBuffer();
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(in,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader=new BufferedReader(isr);
		String  line=null;
		try {
			while((line=reader.readLine())!=null){
				sb.append(line);
				
			}
			reader.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//业务处理
		sb.toString().replaceAll("\t","");
		Object resultObj=JSON.parse(sb.toString());
	
		
		callback.accept(null);	
	}
	
	/**
	 * 查询产品单位
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/units/products/{productId}",method=RequestMethod.GET)
	public void queryUnit(Callback<Object> callback,@PathVariable("productId") String  productId){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>Query  Product Unit by productId,productId is {}",productId);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-unit-create.json");		
		
		
		
		StringBuffer sb=new StringBuffer();
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(in,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader=new BufferedReader(isr);
		String  line=null;
		try {
			while((line=reader.readLine())!=null){
				sb.append(line);
				
			}
			reader.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//业务处理
		sb.toString().replaceAll("\t","");
		Object resultObj=JSON.parse(sb.toString());
	
		
		callback.accept(resultObj);	
	}
}
