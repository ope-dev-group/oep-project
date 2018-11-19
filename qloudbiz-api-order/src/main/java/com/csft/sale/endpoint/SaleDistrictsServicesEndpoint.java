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
 * 销售地区管理
 * 
 * @author zhutao
 *
 */
@RequestMapping("/sales")
public class SaleDistrictsServicesEndpoint {
	private final static Logger logger = LoggerFactory.getLogger(SaleDistrictsServicesEndpoint.class);

	/**
	 * 添加销售地区
	 * 
	 * @param callback
	 * @param body
	 */
	@RequestMapping(value = "/districts", method = RequestMethod.POST)
	public void addDistricts(Callback<Object> callback, Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>>>>>Add districts param is:{}", body);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/sales-district-create.json");

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
	 * 更新销售地区
	 * 
	 * @param callback
	 * @param districtId
	 * @param body
	 */
	@RequestMapping(value = "/districts/{districtId}", method = RequestMethod.PUT)
	public void updateDistricts(Callback<Object> callback, @PathVariable("districtId") String districtId,
			Map<String, String> body) {

		// 调试日志
		logger.debug(">>>>>>>>>Update districts :districtId is:{},param is {}", districtId, body);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/sales-district-create.json");

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
	 * 删除销售地区
	 * 
	 * @param callback
	 * @param districtId
	 * @param body
	 */
	@RequestMapping(value = "/districts/{districtId}", method = RequestMethod.DELETE)
	public void deleteDistricts(Callback<Object> callback, @PathVariable("districtId") String districtId
			) {

		// 调试日志
		logger.debug("Delete districts :districtId is:{}", districtId);

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/sales-district-create.json");

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
	 * 查询销售地区
	 * 
	 * @param callback
	 */
	@RequestMapping(value = "/districts", method = RequestMethod.GET)
	public void queryDistricts(Callback<Object> callback) {

		// 调试日志
		logger.debug("Query districts");

		// 加载数据文件
		InputStream in = ClassLoader
				.getSystemResourceAsStream("com/qloudfin/qloudbiz/apidef/orders/sales-district-create.json");

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
