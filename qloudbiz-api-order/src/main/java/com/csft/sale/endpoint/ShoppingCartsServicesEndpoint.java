package com.csft.sale.endpoint;

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
 * 购物车管理
 * 
 * @author zhutao
 *
 */
@RequestMapping("/sales")
public class ShoppingCartsServicesEndpoint {

	private final static Logger logger = LoggerFactory.getLogger(ShoppingCartsServicesEndpoint.class);

	/**
	 * 创建购物车
	 * 
	 * @param callback
	 * @param userId
	 * @param body
	 */
	@RequestMapping(value = "/users/{userId}/shoppingcarts", method = RequestMethod.POST)
	public void addShoppingCarts(Callback<Object> callback, @PathVariable("userId") String userId,
			Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add shoppingCarts userId is:{} param is:{}", userId, body);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/shoppingcart-create.json");

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
	 * 加入购物车
	 * 
	 * @param callback
	 * @param userId
	 * @param body
	 */
	@RequestMapping(value = "/users/{userId}/shoppingcarts/orderItems", method = RequestMethod.PUT)
	public void putShoppingCarts(Callback<Object> callback, @PathVariable("userId") String userId,
			Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Put shoppingCarts userId is:{} param is:{}", userId, body);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/shoppingcart-create.json");

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
	 * 取消购物车中清单项
	 * 
	 * @param callback
	 * @param userId
	 * @param skuCode
	 * @param body
	 */
	@RequestMapping(value = "/users/{userId}/shoppingcarts/orderItems/{skuCode}", method = RequestMethod.DELETE)
	public void deleteShoppingCartItem(Callback<Object> callback, @PathVariable("userId") String userId,
			@PathVariable("skuCode") String skuCode) {

		// 调试日志
		logger.debug("Delete shoppingCartItem :userId is:{},skuCode is:{}", userId, skuCode);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/shoppingcart-create.json");

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
	 * 修改购物车
	 * @param callback
	 * @param userId
	 * @param skuCode
	 * @param number
	 * @param body
	 */
	@RequestMapping(value = "/users/{userId}/shoppingcarts/orderItems/{skuCode}/{number}", method = RequestMethod.PUT)
	public void updateShoppingCarts(Callback<Object> callback, @PathVariable("userId") String userId,
			@PathVariable("skuCode") String skuCode,@PathVariable("number") String number, Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Put shoppingCarts userId is:{}, skuCode is:{}, number is:{}, param is:{}", userId, skuCode, body);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/shoppingcart-create.json");

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
	 * 清空购物车
	 * 
	 * @param callback
	 * @param userId
	 * @param body
	 */
	@RequestMapping(value = "/users/{userId}/shoppingcarts", method = RequestMethod.DELETE)
	public void deleteShoppingCarts(Callback<Object> callback, @PathVariable("userId") String userId) {

		// 调试日志
		logger.debug("Delete shoppingCarts :userId is:{},param is {}", userId);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/shoppingcart-create.json");

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
	 * 查询购物车清单
	 * 
	 * @param callback
	 * @param userId
	 */
	@RequestMapping(value = "/users/{userId}/shoppingcarts", method = RequestMethod.GET)
	public void queryShoppingCarts(Callback<Object> callback, @PathVariable("userId") String userId) {

		// 调试日志
		logger.debug("Query shoppingCarts :userId is:{},param is {}", userId);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/shoppingcart-create.json");

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
