package com.csft.price.sdk;

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
import com.qloudfin.qloudbus.reactive.Callback;
/**
 * 价格类型微服务
 * 
 * @author Administrator
 *
 */
@RequestMapping("/prices")
public class PriceTypesServicesEndpoint {

	private final static Logger logger = LoggerFactory.getLogger(PriceDimensionsServicesEndpoint.class);

	/**
	 * 新增价格类型
	 * 
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/types", method = RequestMethod.POST)
	public void addTypes(Callback<Object> callback, Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add Price types,the param is:{}", body);
		

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-type-create.json");

		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(in, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			reader.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 业务处理
		sb.toString().replaceAll("\t", "");
		Object resultObj = JSON.parse(sb.toString());

		callback.accept(resultObj);
	}

	/**
	 * 修改价格类型
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/types/{dimensionId}", method = RequestMethod.PUT)
	public void editType(Callback<Object> callback, @PathVariable("dimensionId") String dimensionId,Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Edit price type ,the typeId is:{}, param is:{}", dimensionId, body);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-type-create.json");

		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(in, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			reader.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 业务处理
		sb.toString().replaceAll("\t", "");
		Object resultObj = JSON.parse(sb.toString());

		callback.accept(resultObj);
	}

	/**
	 * 删除价格类型
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/types/{typeId}", method = RequestMethod.DELETE)
	public void deleteType(Callback<Object> callback, @PathVariable("typeId") String typeId) {

		// 调试日志
		logger.debug("Delete type :the typeId is:{},param is {}", typeId);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-type-create.json");

		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(in, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			reader.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 业务处理
		sb.toString().replaceAll("\t", "");
		Object resultObj = JSON.parse(sb.toString());

		callback.accept(null);
	}
	
	
	/**
	 * 查询价格类型列表
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public void queryDimensions(Callback<Object> callback) {

		// 调试日志
		logger.debug("Query type list");

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-type-create.json");

		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(in, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);

			}
			reader.close();
			isr.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 业务处理
		sb.toString().replaceAll("\t", "");
		Object resultObj = JSON.parse(sb.toString());

		callback.accept(resultObj);
	}
}
