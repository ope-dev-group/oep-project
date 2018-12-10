package com.csft.order.endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.csft.core.utils.FileUtils;
import com.csft.core.utils.ResultDataUtils;
import com.qloudfin.qloudbus.annotation.PathVariable;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.annotation.RequestParam;
import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.security.util.StringUtils;

@RequestMapping("/")
public class OrdersServicesEndpoint {

	private final static Logger logger = LoggerFactory.getLogger(OrdersServicesEndpoint.class);
	
	private final static String PATH_ADD_ORDERS_JSON = "com/qloudfin/qloudbiz/apidef/orders/order-create.json";
	private final static String PATH_EDIT_ORDERS_JSON = "com/qloudfin/qloudbiz/apidef/orders/order-update.json";
	private final static String PATH_DELETE_ORDERS_JSON ="com/qloudfin/qloudbiz/apidef/orders/order-delete.json";
	private final static String PATH_QUERY_ATTRIBUTES_ORDERS_JSON = "com/qloudfin/qloudbiz/apidef/orders/order-attributes-query-inf.json";
	private final static String PATH_QUERY_BUYER_ORGS_ORDERS_JSON = "com/qloudfin/qloudbiz/apidef/orders/order-buyerorgs-query-inf.json";
	private final static String PATH_QUERY_CONFIRM_ORDERS_JSON = "com/qloudfin/qloudbiz/apidef/orders/order-Confirm-inf.json";
	private final static String PATH_QUERY_ORDERS_LIST_JSON="com/qloudfin/qloudbiz/apidef/orders/query-orders-list.json";
	
	/**
	 * 创建订单
	 * 
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/sales/orders", method = RequestMethod.POST)
	public void addOrders(Callback<Object> callback, Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add orders param is:{}", body);

		String content = FileUtils.getResourceContent(PATH_ADD_ORDERS_JSON);
		
		Object resultObj = JSON.parse(content);
		callback.accept(resultObj);
		
		
	}

	
	/**
	 * 订单信息修改
	 * 
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/sales/orders/{orderId}", method = RequestMethod.PUT)
	public void editOrders(Callback<Object> callback, Map<String, String> body,@PathVariable("orderId") String orderId) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Edit orders orderId is :{} param is:{}", orderId,body);

		String content = FileUtils.getResourceContent(PATH_EDIT_ORDERS_JSON);
		
		Object resultObj = JSON.parse(content);
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
	 * @param callback
	 * @param orderId
	 */
	@RequestMapping(value = "/sales/orders/{orderId}", method = RequestMethod.DELETE)
	public void deleteOrders(Callback<Object> callback, @PathVariable("orderId") String orderId) {

		// 调试日志
		logger.debug("Delete orders :orderId is:{}", orderId);

		String content = FileUtils.getResourceContent(PATH_DELETE_ORDERS_JSON);
		
		Object resultObj = JSON.parse(content);
		callback.accept(resultObj);
		
	}

	/**
	 * 查询订单 若status为空，则查询所有状态
	 * 根据订单状态查询
	 * @param callback
	 * @param status
	 */
	@RequestMapping(value = "/orders/status/{status}", method = RequestMethod.GET)
	public void queryOrdersByStatus(Callback<Object> callback, @PathVariable("status") String status,
									@RequestParam("currentNum")int currentNum,
									@RequestParam("pagePerNum")int pagePerNum ) {

		// 调试日志
		logger.debug("Query orders :status is:{}", status);

		String content = FileUtils.getResourceContent(PATH_QUERY_ORDERS_LIST_JSON);
		
		Object resultObj = JSON.parse(content);
		callback.accept(resultObj);
		
	}

	/**
	 * 根据渠道和订单状态查询
	 * 
	 * @param callback
	 * @param channelId
	 * @param status
	 */
	@RequestMapping(value = "/orders/channels/{channelId}/status/{status}", method = RequestMethod.GET)
	public void queryOrdersByChannel(Callback<Object> callback, @PathVariable("channelId") String channelId,
									@PathVariable("status") String status,
									@RequestParam("currentNum")int currentNum,
									@RequestParam("pagePerNum")int pagePerNum ) {

		// 调试日志
		logger.debug("Query orders :channelId is:{},status is {}", channelId, status);

		String content = FileUtils.getResourceContent(PATH_QUERY_ORDERS_LIST_JSON);
		
		Object resultObj = JSON.parse(content);
		callback.accept(resultObj);
	}

	/**
	 * 根据服务商和状态查询
	 * 根据经销商和订单状态查询
	 * @param callback
	 * @param dealerId
	 * @param status
	 */
	@RequestMapping(value = "/orders/dealers/{dealerId}/status/{status}", method = RequestMethod.GET)
	public void queryOrdersByDealer(Callback<Object> callback, @PathVariable("dealerId") String dealerId,
									@PathVariable("status") String status,
									@RequestParam("currentNum")int currentNum,
									@RequestParam("pagePerNum")int pagePerNum ) {

		// 调试日志
		logger.debug("Query orders :dealerId is:{},status is {}", dealerId, status);

		String content = FileUtils.getResourceContent(PATH_QUERY_ORDERS_LIST_JSON);
		
		Object resultObj = JSON.parse(content);
		callback.accept(resultObj);
	}

	/**
	 * 根据用户和状态查询
	 * 根据用户和订单状态查询
	 * 
	 * @param callback
	 * @param userId
	 * @param status
	 */
	@RequestMapping(value = "/orders/users/{userId}/status/{status}", method = RequestMethod.GET)
	public void queryOrdersByUser(Callback<Object> callback, 
									@PathVariable("userId") String userId,
									@PathVariable("status") String status,
									@RequestParam("currentNum")int currentNum,
									@RequestParam("pagePerNum")int pagePerNum ) {

		// 调试日志
		logger.debug("Query orders :userId is:{},status is {}", userId, status);
		
		String content = FileUtils.getResourceContent(PATH_QUERY_ORDERS_LIST_JSON);
		
		Object resultObj = JSON.parse(content);
		callback.accept(resultObj);
	}

	
	/**
	 * 订单详情查询
	 * @param callback
	 * @param orderId
	 */
	@RequestMapping(value = "/sales/orders/{orderId}", method = RequestMethod.GET)
	public void queryAttributesOrder(Callback<Object> callback, @PathVariable("orderId") String orderId) {
		// 调试日志
		logger.debug("Query orders :orderId is:{}", orderId);

		String content = FileUtils.getResourceContent(PATH_QUERY_ATTRIBUTES_ORDERS_JSON);
		
		Object resultObj = JSON.parse(content);
		callback.accept(resultObj);
	}

	/**
	 * 客户允售商品查询
	 * @param callback
	 * @param buyerOrgId
	 * @param scopeId
	 */
	@RequestMapping(value = "/sales/skus/buyerorgs/{buyerOrgId}/salescope/{scopeId}", method = RequestMethod.GET)
	public void queryBuyerOrgsOrder(Callback<Object> callback, @PathVariable("buyerOrgId") String buyerOrgId,@PathVariable("scopeId") String scopeId) {
		// 调试日志
		logger.debug("Query orders :buyerOrgId is:{},scopeId is:{}", buyerOrgId,scopeId);
   
		// 加载数据文件
		String content = FileUtils.getResourceContent(PATH_QUERY_BUYER_ORGS_ORDERS_JSON);
		
		Object resultObj = JSON.parse(content);
		callback.accept(resultObj);
		
	}

	/**
	 * 查询订单核准任务
	 * @param callback
	 * @param userId
	 */
	@RequestMapping(value = "/sales/orders/confirm-tasks/{userId}", method = RequestMethod.GET)
	public void queryConfirmOrder(Callback<Object> callback, @PathVariable("userId") String userId) {
		// 调试日志
		logger.debug("Query orders :userId is:{}", userId);

		String content = FileUtils.getResourceContent(PATH_QUERY_CONFIRM_ORDERS_JSON);
		
		Object resultObj = JSON.parse(content);
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
