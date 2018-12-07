package com.qloudbiz.product.endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.pojo.ProductLine;
import com.qloudbiz.product.service.ProductLineService;
import com.qloudbiz.product.service.impl.ProductLineServiceImpl;
import com.qloudbiz.product.vo.ProductLineVO;
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
			//状态验证
			if(!("Y".equals(vo.getStatus()) || "N".equals(vo.getStatus()))){
				callback.accept(ResultDataUtils.error("410",new String[]{"status"}));
				return;
			}
			//调用service
			service.save(productLine -> {
				String jsonStr = JSON.toJSONString(ResultDataUtils.success(productLine),new SimplePropertyPreFilter(ProductLine.class,"lineId","lineCode"));
				callback.accept(JSON.parse(jsonStr));
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
		logger.debug(">>>>>>>>>Update lines :lineId is:{}, the param is {}",lineId,vo);
		
		try {
			if (null == vo) {
				callback.accept(ResultDataUtils.error("402"));
				return;
			}
			if (StringUtils.isEmpty(lineId)) {
				callback.accept(ResultDataUtils.error("401",new String[]{"lineId"}));
				return;
			}
			
			vo.setLineId(lineId);
			
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
			//状态验证
			if(!("Y".equals(vo.getStatus()) || "N".equals(vo.getStatus()))){
				callback.accept(ResultDataUtils.error("410",new String[]{"status"}));
				return;
			}
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
					String jsonStr=JSON.toJSONString(ResultDataUtils.success(page));
					
					callback.accept(JSON.parse(jsonStr));
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
					String jsonStr=JSON.toJSONString(ResultDataUtils.success(result));
					
					callback.accept(JSON.parse(jsonStr));
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
