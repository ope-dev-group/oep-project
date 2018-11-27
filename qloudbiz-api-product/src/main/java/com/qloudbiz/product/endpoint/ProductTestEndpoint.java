package com.qloudbiz.product.endpoint;


import io.vertx.core.Launcher;







import java.util.List;
import java.util.Map;







import org.slf4j.Logger;
import org.slf4j.LoggerFactory;







import com.qloudbiz.core.factory.ServiceProxyFactory;
import com.qloudbiz.core.result.BaseResult;
import com.qloudbiz.core.utils.ResultDataUtils;
import com.qloudbiz.product.dao.ProductDao;
import com.qloudbiz.product.service.ProductServiceInterface;
import com.qloudfin.qloudbus.annotation.RequestMapping;
import com.qloudfin.qloudbus.annotation.RequestMethod;
import com.qloudfin.qloudbus.reactive.Callback;


/**
 * 产品测试微服务
 *
 * @author Kezx
 *
 */
@RequestMapping("/")
public class ProductTestEndpoint {
	
	private ProductDao productDao=new ProductDao();
	
	private final static Logger logger=LoggerFactory.getLogger(ProductTestEndpoint.class);
	
	/**
	 * 添加产品线
	 * @param callback
	 * @param corpId
	 * @param token
	 */
	@RequestMapping(value="/producttest",method=RequestMethod.GET)
	public void addLines(Callback<Object> callback){
		
		
		try {
			/*productDao.listall(result->{
				
				ResultData<List<Map>> data=(ResultData<List<Map>>)result;
				List<Map> maps=data.getData();
				callback.accept(maps);
				
				
			}, 0, 10);*/
			
			ProductServiceInterface service=ServiceProxyFactory.createProxy(ProductServiceInterface.class);
			service.save();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	
		
	
	}
}
