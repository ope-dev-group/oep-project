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
 * 销售渠道管理
 * 
 * @author zhutao
 *
 */
@RequestMapping("/sales")
public class SaleChannelsServicesEndpoint {

	private final static Logger logger = LoggerFactory.getLogger(SaleChannelsServicesEndpoint.class);

	/**
	 * 添加销售渠道
	 * 
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/channels", method = RequestMethod.POST)
	public void addChannels(Callback<Object> callback, Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add channels param is:{}", body);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/sales-channel-create.json");

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
	 * 修改销售渠道
	 * 
	 * @param callback
	 * @param channelId
	 * @param body
	 */
	@RequestMapping(value = "/channels/{channelId}", method = RequestMethod.PUT)
	public void updateLines(Callback<Object> callback, @PathVariable("channelId") String channelId,
			Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>Update channels :channelId is:{},param is {}", channelId, body);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/sales-channel-create.json");

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
	 * 删除销售渠道
	 * 
	 * @param callback
	 * @param channelId
	 * @param body
	 */
	@RequestMapping(value = "/channels/{channelId}", method = RequestMethod.DELETE)
	public void deleteLines(Callback<Object> callback, @PathVariable("channelId") String channelId
			) {

		// 调试日志
		logger.debug("Delete channels :channelId is:{},param is {}", channelId);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/sales-channel-create.json");

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
	 * 查询销售渠道
	 * 
	 * @param callback
	 */
	@RequestMapping(value = "/channels", method = RequestMethod.GET)
	public void queryLines(Callback<Object> callback) {

		// 调试日志
		logger.debug("Query channels");

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/sales-channel-create.json");

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
