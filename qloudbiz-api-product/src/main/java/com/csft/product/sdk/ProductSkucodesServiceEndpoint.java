package com.csft.product.sdk;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;


/**
 * 产品skuCode微服务
 *主物料加上一组SKU属性值，构成该产品的最小计算单位，产品的库存、销售计算的最小单位，产品的SKU属性往往决定产品的价格、影响产品的销售（例如颜色）。
 *产品SKU编码管理提供对于产品SKU编码进行增查改删的操作。
 *
 */
@RequestMapping("/products")
public class ProductSkucodesServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductSkucodesServiceEndpoint.class);
	
	/**
	 * 添加产品skuCode
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes",method=RequestMethod.POST)
	public void addSkucodes(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add Product Skucodes param is:{}",body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/skucode-create.json");		
		
		
		
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
	 * 修改产品Skucodes
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes/{skuCode}",method=RequestMethod.PUT)
	public void updateSkucodes(Callback<Object> callback,@PathVariable("skuCode") String skuCode,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>Update skucodes :skucode is:{},param is {}",skuCode,body);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/skucode-create.json");		
		
		
		
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
	 * 删除产品Skucode
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes/{skuCode}",method=RequestMethod.DELETE)
	public void deleteSkucode(Callback<Object> callback,@PathVariable("skuCode") String skuCode){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>>>Delete skuCode :skuCode is:{}",skuCode);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/skucode-create.json");		
		
		
		
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
	 * 查询产品skucode列表
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes/types/{typeId}",method=RequestMethod.GET)
	public void querySkucodes(Callback<Object> callback,@PathVariable("typeId")String typeId){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>Query  Product Skucodes list");
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/skucode-create.json");		
		
		
		
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
	 * 查询产品skucode详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/skucodes/{skuCode}",method=RequestMethod.GET)
	public void querySkucode(Callback<Object> callback,@PathVariable("skuCode")String skuCode){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>>Query  Product skuCode,skuCode is {}",skuCode);
		
		//加载数据文件
		InputStream in=ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/products/skucode-create.json");		
		
		
		
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
