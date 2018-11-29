package com.qloudbiz.product.event;

import com.qloudbiz.core.dao.DbInit;
import com.qloudfin.qloudbus.annotation.QueueCallback;
import com.qloudfin.qloudbus.annotation.QueueCallbackType;
import com.qloudfin.qloudbus.annotation.RequestMapping;

@RequestMapping("/")
public class DbInitService {

	public DbInitService() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 初始化
	 */
	@QueueCallback( { QueueCallbackType.INIT } )
	public void init() {
		DbInit.init();
	}

}
