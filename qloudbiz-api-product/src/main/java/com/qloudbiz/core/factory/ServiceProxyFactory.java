package com.qloudbiz.core.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudbiz.core.utils.ConnectionUtils;

public class ServiceProxyFactory {

	private static Logger logger = LoggerFactory
			.getLogger(ServiceProxyFactory.class);

	@SuppressWarnings("unchecked")
	public static <T> T createProxy(Class<T> targetClazz) {

		T target;

		T proxy = null;
		try {
			target = targetClazz.newInstance();

			proxy = (T) Proxy.newProxyInstance(target.getClass()
					.getClassLoader(), target.getClass().getInterfaces(),
					new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method,
								Object[] args) throws Throwable {
							logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>BEGIN  TRANSACTION");

							ConnectionUtils.beginTransaction();
							Object obj;
							try {
								obj = method.invoke(target, args);

								logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>COMMIT TRANSACTION");
								
								// 提交事务
								ConnectionUtils.commitTransaction();

								return obj;
							} catch (Exception e) {
								logger.debug("ROLLBACK TRANSACTION");

								// 回滚
								ConnectionUtils
										.rollbackTransaction(ConnectionUtils
												.getConnection());

								logger.debug("SERVICE THROWS EXCEPTION");
								throw e;
							} finally {
								// 关闭连接
								ConnectionUtils.closeConnection();
							}
						}
					});

		} catch (Exception e1) {			
			e1.printStackTrace();
		}
		return proxy;
	}
}
