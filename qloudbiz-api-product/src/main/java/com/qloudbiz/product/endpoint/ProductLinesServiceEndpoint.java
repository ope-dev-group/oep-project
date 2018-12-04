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
import com.qloudfin.qloudbus.security.util.StringUtils;


/**
 * 产品线服务
 *
 * @author Kezx
 *
 */
@RequestMapping("/products")
public class ProductLinesServiceEndpoint {
	
	private final static Logger logger=LoggerFactory.getLogger(ProductLinesServiceEndpoint.class);
	
	private ProductLineService service = ServiceProxyFactory.createProxy(ProductLineServiceImpl.class);
	
	/**
	 * 新增产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines",method=RequestMethod.POST)
	public void addLines(Callback<Object> callback, ProductLineVO vo){
		//调试日志
		logger.debug(">>>>>>>>>>>>>Add lines param is:{}",vo);
	    
		try {
			//请求参数验证
			if(null ==vo) {
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			if(StringUtils.isEmpty(vo.getLineCode())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"lineCode"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getLineName())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"lineName"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getSort())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"sort"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getStatus())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"status"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getCreatedTime())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"createdTime"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getCreatorId())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"creatorId"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getCreatorName())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"creatorName"}));
				return;
			}
			if(StringUtils.isEmpty(vo.getTenantId())) {
				callback.accept(ResultDataUtils.error("401", new String[] {"tenantId"}));
				return;
			}
			//调用service
			service.save(productLine -> {
				callback.accept(ResultDataUtils.success(productLine));
			}, vo);
			
		} catch (Exception e) {
			logger.error("the excption is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
		
	}
	
	
	/**
	 * 修改产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/lines/{lineId}",method=RequestMethod.PUT)
	public void updateLines(Callback<Object> callback,@PathVariable("lineId") String lineId,ProductLineVO vo){
		
		//调试日志
		logger.debug(">>>>>>>>>Update lines :lineId is:{}",lineId);
		
		try {
			if (null == lineId || lineId.isEmpty()) {
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			if (StringUtils.isEmpty(vo.getLineName())) {
				callback.accept(ResultDataUtils.error("401",new String[]{"lineName"}));
				return;
			}
			if (StringUtils.isEmpty(vo.getSort())) {
				callback.accept(ResultDataUtils.error("401",new String[]{"sort"}));
				return;
			}
			if (StringUtils.isEmpty(vo.getStatus())) {
				callback.accept(ResultDataUtils.error("401",new String[] {"status"}));
				return;
			}
			vo.setLineId(lineId);
			//调用service
			service.update(rownum -> {
				if (null != rownum && rownum.intValue()==1) {
					callback.accept(ResultDataUtils.success());
				}else {
					callback.accept(ResultDataUtils.error("406"));
				}
			},vo);
			
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
		
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
		logger.debug(">>>>>>>>>>delete product the lineId is {}",lineId);
		 
		try {
			//请求参数验证
			if(StringUtils.isEmpty(lineId)){
				callback.accept(ResultDataUtils.error("401",new String[]{"lineId"}));
				return;
			}
			ProductLineVO vo=new ProductLineVO();
			vo.setLineId(lineId);
			//调用delete方法
			service.delete(rownum->{				
				if(null!=rownum && rownum.intValue()==1){
					callback.accept(ResultDataUtils.success());
				}else{
					callback.accept(ResultDataUtils.error("407"));
				}
			},vo);
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
			
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
			
			if(currentNum <= 0) {
				callback.accept(ResultDataUtils.error("401",new String[]{"currentNum"}));
				return;
			}
			if(pagePerNum <= 0) {
				callback.accept(ResultDataUtils.error("401",new String[]{"pagePerNum"}));
				return;
			}
			if(StringUtils.isEmpty(lineCode)) {
				callback.accept(ResultDataUtils.error("401", new String[] {"lineCode"}));
				return;
			}
			if(StringUtils.isEmpty(lineName)) {
				callback.accept(ResultDataUtils.error("401", new String[] {"lineName"}));
				return;
			}
			if(StringUtils.isEmpty(status)) {
				callback.accept(ResultDataUtils.error("401", new String[] {"status"}));
				return;
			}
			if(StringUtils.isEmpty(tenantId)) {
				callback.accept(ResultDataUtils.error("401", new String[] {"tenantId"}));
				return;
			}
			
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
		logger.debug("Query info lines");
		
		try {
			
			//验证参数
			if(StringUtils.isEmpty(lineId)) {
				callback.accept(ResultDataUtils.error("402",new String[]{"lineId"}));
				return;
			}
			//设置参数
			ProductLineVO vo = new ProductLineVO();
			vo.setLineId(lineId);
			//调用查询详情
			service.queryInfo(result->{
				if (null != result) {
					callback.accept(ResultDataUtils.success(result));
				} else {
					callback.accept(ResultDataUtils.error("404"));	
				}
			}, vo);
		} catch (Exception e) {
			logger.error("the exception is {}",e);
			callback.accept(ResultDataUtils.error(e));
		}
	}
}
