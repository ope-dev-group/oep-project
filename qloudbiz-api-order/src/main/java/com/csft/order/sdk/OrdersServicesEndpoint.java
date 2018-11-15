package com.csft.order.sdk;

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

@RequestMapping("/")
public class OrdersServicesEndpoint {

	private final static Logger logger = LoggerFactory.getLogger(OrdersServicesEndpoint.class);

	/**
	 * 创建订单
	 * 
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "orders", method = RequestMethod.POST)
	public void addOrders(Callback<Object> callback, Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add orders param is:{}", body);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	 * 修改订单
	 * 
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "orders/{order_id}", method = RequestMethod.PUT)
	public void editOrders(Callback<Object> callback, Map<String, String> body,@PathVariable("orderId") String orderId) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Edit orders orderId is :{} param is:{}", orderId,body);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	 * 添加、修改订单项
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/orders/{orderId}/{orderItemId}", method = RequestMethod.PUT)
	public void putOrders(Callback<Object> callback, @PathVariable("orderId") String orderId,
			@PathVariable("orderItemId") String orderItemId, Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Put orders orderId is:{},orderItemId is:{} param is:{}", orderId, orderItemId, body);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	 * 删除订单(项)
	 * 
	 * @param callback
	 * @param orderId
	 * @param orderItemId
	 * @param body
	 */
	@RequestMapping(value = "/orders/{orderId}/{orderItemId}", method = RequestMethod.DELETE)
	public void deleteOrderItem(Callback<Object> callback, @PathVariable("orderId") String orderId,
			@PathVariable("orderItemId") String orderItemId) {

		// 调试日志
		logger.debug("Delete orderItem :orderId is:{},orderItemId is:{}", orderId, orderItemId);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	 * 删除订单
	 * 
	 * @param callback
	 * @param userId
	 * @param body
	 *//*
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.DELETE)
	public void deleteOrders(Callback<Object> callback, @PathVariable("orderId") String orderId,
			Map<String, String> body) {

		// 调试日志
		logger.debug("Delete orders :orderId is:{},param is {}", orderId, body);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	}*/

	/**
	 * 查询订单 若status为空，则查询所有状态
	 * 
	 * @param callback
	 * @param status
	 */
	@RequestMapping(value = "/orders/status/{status}", method = RequestMethod.GET)
	public void queryOrdersByStatus(Callback<Object> callback, @PathVariable("status") String status) {

		// 调试日志
		logger.debug("Query orders :status is:{}", status);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	 * 根据销售渠道和状态查询
	 * 
	 * @param callback
	 * @param channelId
	 * @param status
	 */
	@RequestMapping(value = "/orders/channels/{channelId}/status/{status}", method = RequestMethod.GET)
	public void queryOrdersByChannel(Callback<Object> callback, @PathVariable("channelId") String channelId,
			@PathVariable("status") String status) {

		// 调试日志
		logger.debug("Query orders :channelId is:{},status is {}", channelId, status);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	 * 根据服务商和状态查询
	 * 
	 * @param callback
	 * @param dealerId
	 * @param status
	 */
	@RequestMapping(value = "/orders/dealers/{dealerId}/status/{status}", method = RequestMethod.GET)
	public void queryOrdersByDealer(Callback<Object> callback, @PathVariable("dealerId") String dealerId,
			@PathVariable("status") String status) {

		// 调试日志
		logger.debug("Query orders :dealerId is:{},status is {}", dealerId, status);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	 * 根据用户和状态查询
	 * 
	 * @param callback
	 * @param userId
	 * @param status
	 */
	@RequestMapping(value = "/orders/users/{userId}/status/{status}", method = RequestMethod.GET)
	public void queryOrdersByUser(Callback<Object> callback, @PathVariable("userId") String userId,
			@PathVariable("status") String status) {

		// 调试日志
		logger.debug("Query orders :userId is:{},status is {}", userId, status);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
			
			e.printStackTrace();
		}

		// 业务处理
		sb.toString().replaceAll("\t", "");
		Object resultObj = JSON.parse(sb.toString());

		callback.accept(resultObj);
	}

	/**
	 * 查询订单核准任务
	 * 
	 * @param callback
	 * @param channelId
	 * @param status
	 */
	@RequestMapping(value = "/orders/confirm-tasks/{userId}", method = RequestMethod.GET)
	public void queryConfirmOrders(Callback<Object> callback, @PathVariable("userId") String userId) {

		// 调试日志
		logger.debug("Query orders :userId is:{}", userId);

		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
	 * 审批订单
	 * @param callback
	 * @param orderId
	 * @param action 统一或拒绝
	 */
	@RequestMapping(value = "/orders/{orderId}/confirm/{action}", method = RequestMethod.PUT)
	public void confirmOrder(Callback<Object> callback, @PathVariable("orderId") String orderId,
			@PathVariable("action") String action) {

		// 调试日志
		logger.debug("Query orders :orderId is:{},action is:{}", orderId,action);
		
		// 加载数据文件
		InputStream in = ClassLoader.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/order-create.json");

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
			
			e.printStackTrace();
		}

		// 业务处理
		sb.toString().replaceAll("\t", "");
		Object resultObj = JSON.parse(sb.toString());

		callback.accept(resultObj);
	}
	
	
}
