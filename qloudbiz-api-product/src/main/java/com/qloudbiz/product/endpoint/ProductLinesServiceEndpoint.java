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
import com.qloudbiz.core.exception.GenericException;
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.result.PageResultData;
import com.qloudbiz.core.utils.FileUtils;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.service.ProductLineService;
import com.qloudbiz.product.service.impl.ProductLineServiceImpl;
import com.qloudbiz.product.vo.ProductLineVO;
import com.qloudbiz.product.vo.ProductVO;
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
public class ProductLinesServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductLinesServiceEndpoint.class);
	
//	private final static String PATH_ADD_LINES_JSON="com/qloudfin/qloudbiz/apidef/products/productlines-create.json";	
//	
//	private final static String PATH_UPDATE_DELETE="com/qloudfin/qloudbiz/apidef/common/update_or_delete.json";//新增或者修改json
//	private final static String PATH_LIST_LINES_JSON="com/qloudfin/qloudbiz/apidef/products/productlines-list.json";
//	private final static String PATH_QUERY_LINES_DETAIL_JSON="com/qloudfin/qloudbiz/apidef/products/productlines-detail.json";

	private ProductLineService service = ServiceProxyFactory.createProxy(ProductLineServiceImpl.class);
	
	/**
	 * 新增产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines",method=RequestMethod.POST)
	public void addLines(Callback<Object> callback,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add lines param is:{}",body);
	
//		//读取json数据
//		String content=FileUtils.getResourceContent(PATH_ADD_LINES_JSON);
//		
//		//业务处理
//		Object resultObj=JSON.parse(content);
//	
//		
//		callback.accept(resultObj);	
	}
	
	
	/**
	 * 修改产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines/{lineId}",method=RequestMethod.PUT)
	public void updateLines(Callback<Object> callback,@PathVariable("lineId") String lineId,Map<String,String> body){
		
		
		//调试日志
		logger.debug(">>>>>>>>>Update lines :lineId is:{},param is {}",lineId,body);
		
//		//读取json数据
//		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
//				
//		//业务处理
//		Object resultObj=JSON.parse(content);
//	
//		
//		callback.accept(resultObj);	
	}
	
	/**
	 * 删除产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines/{lineId}",method=RequestMethod.DELETE)
	public void deleteLines(Callback<Object> callback,@PathVariable("lineId") String lineId){
		
		
		//调试日志
		logger.debug("Delete lines :lineId is:{}",lineId);
		
//		//读取json数据
//		String content=FileUtils.getResourceContent(PATH_UPDATE_DELETE);
//				
//		//业务处理
//		Object resultObj=JSON.parse(content);
//	
//		
//		callback.accept(resultObj);		
	}
	
	/**
	 * 查询产品线列表
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines",method=RequestMethod.GET)
	public void queryLines(Callback<Object> callback,
			@RequestParam("currentNum")int currentNum,
			@RequestParam("pagePerNum")int pagePerNum,
			@RequestParam("lineCode")String lineCode,
			@RequestParam("lineName")String lineName,
			@RequestParam("status")String status,
			@RequestParam("tenantId")String tenantId){
		
		
		//调试日志
		logger.debug(">>>>>>>>>>endpoint query productLine listall method start");
		
		try {
			
//			if(currentNum < 0) {
//				callback.accept(ResultDataUtils.error("401",new String[]{"currentNum"}));
//				return;
//			}
//			if(pagePerNum < 0) {
//				callback.accept(ResultDataUtils.error("401",new String[]{"pagePerNum"}));
//				return;
//			}
//			if(lineCode == null || lineCode == "") {
//				callback.accept(ResultDataUtils.error("401", new String[] {"lineCode"}));
//				return;
//			}
//			if(lineName == null || lineName == "") {
//				callback.accept(ResultDataUtils.error("401", new String[] {"lineName"}));
//			}
//			if(status == null || status == "") {
//				callback.accept(ResultDataUtils.error("401", new String[] {"status"}));
//			}
//			if(tenantId == null || tenantId == "") {
//				callback.accept(ResultDataUtils.error("401", new String[] {"tenantId"}));
//			}
			
			//参数设置
			ProductLineVO vo = new ProductLineVO(); 
			vo.setCurrentNum(currentNum);
			vo.setPagePerNum(pagePerNum);
			vo.setLineCode(lineCode);
			vo.setLineName(lineName);
			vo.setStatus(status);
			vo.setTenantId(tenantId);
			
			service.queryList(page -> {
				if(null!=page) {
					callback.accept(ResultDataUtils.success(page));
				} else {
					callback.accept(ResultDataUtils.error("409"));
				}
			},vo);
			
			
		} catch(Exception e) {
			logger.error(">>>>>>>>>>query productLine listall exception ");
			callback.accept(ResultDataUtils.error(e));
		}
	}
	
	
	/**
	 * 查询产品线详情
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines/{lineId}",method=RequestMethod.GET)
	public void queryLinesDetail(Callback<Object> callback,@PathVariable("lineId")String lineId){
		
		
		//调试日志
		logger.debug("Query lines");
		
//		//读取json数据
//		String content=FileUtils.getResourceContent(PATH_QUERY_LINES_DETAIL_JSON);
//				
//		//业务处理
//		Object resultObj=JSON.parse(content);
//	
//		
//		callback.accept(resultObj);	
	}
}
