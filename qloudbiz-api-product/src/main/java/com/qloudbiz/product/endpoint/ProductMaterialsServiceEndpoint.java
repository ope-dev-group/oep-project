package com.qloudbiz.product.endpoint;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.product.service.ProductServiceInterface;
import com.qloudbiz.product.service.impl.ProductServiceImpl;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;


/**
 * 产品线服务
 *
 * @author Kezx
 *
 */
@RequestMapping("/products")
public class ProductMaterialsServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductMaterialsServiceEndpoint.class);
	  
	
	/**
	 * 添加产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials",method=RequestMethod.POST)
	public void addMaterials(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add materials param is:{}",body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-create.json");		
		
		
		
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
	
	
	/**
	 * 修改产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials/{lineId}",method=RequestMethod.PUT)
	public void updateMaterials(Callback<Object> callback,@PathVariable("lineId") String lineId,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>Update materials :materialId is:{},param is {}",lineId,body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-create.json");		
		
		
		
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
	 * 删除产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials/{lineId}",method=RequestMethod.DELETE)
	public void deleteMaterials(Callback<Object> callback,@PathVariable("lineId") String lineId){
		
		
		//调试日志
		logger.debug("Delete materials :materialId is:{}",lineId);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-create.json");		
		
		
		
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
	 * 查询产品线列表信息
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials",method=RequestMethod.GET)
	public void queryMaterials(Callback<Object> callback){
		
		
		//调试日志
		logger.debug("Query materials");
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-create.json");		
		
		
		
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
	
	
	/**
	 * 查询产品线列表信息
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/materials/{materialId}",method=RequestMethod.GET)
	public void queryMaterial(Callback<Object> callback,@PathVariable("materialId")String materialId){
		
		
		//调试日志
		logger.debug(">>>>>>>Query material Detail materialId is {}",materialId);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/product-create.json");		
		
		
		
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
	
	public static void main(String[] args) throws Exception {
		ProductServiceInterface service=ServiceProxyFactory.createProxy(ProductServiceImpl.class);
		
		service.save();
	}
}
