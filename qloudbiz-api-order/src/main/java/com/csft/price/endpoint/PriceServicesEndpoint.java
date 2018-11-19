package com.csft.price.endpoint;

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
 * 价格维护微服务
 * 
 * @author Administrator
 *
 */
@RequestMapping("/")
public class PriceServicesEndpoint {
	private final static Logger logger = LoggerFactory.getLogger(PriceDimensionsServicesEndpoint.class);

	/**
	 * 新增价格
	 * 
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/prices", method = RequestMethod.POST)
	public void addPrice(Callback<Object> callback, Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add Price,the param is:{}", body);
		

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-rules-create.json");

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
	 * 修改价格规则
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/prices/{priceId}", method = RequestMethod.PUT)
	public void editPrice(Callback<Object> callback, @PathVariable("priceId") String priceId,Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Edit price  ,the typeId is:{}, param is:{}", priceId, body);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-rules-create.json");

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
	 * 删除价格规则
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/prices/{priceId}", method = RequestMethod.DELETE)
	public void deletePrice(Callback<Object> callback, @PathVariable("priceId") String priceId) {

		// 调试日志
		logger.debug("Delete price  :the priceId is:{},param is {}", priceId);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-rules-create.json");

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
	 * 查询产品价格列表
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/prices/{skuCode}", method = RequestMethod.GET)
	public void querySkuPrices(Callback<Object> callback,@PathVariable("skuCode") String skuCode) {

		// 调试日志
		logger.debug("Query prices list by skuCode ,the param is {}",skuCode);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-rules-create.json");

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
	 * 查询报价
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/prices/quote", method = RequestMethod.GET)
	public void queryPrices(Callback<Object> callback,Map<String, String> body) {

		// 调试日志
		logger.debug("Query prices quote ,the param is {}",body);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/prices/price-rules-create.json");

		StringBuffer sb = new StringBuffer();
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(in, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			
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
