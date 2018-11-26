package com.qloudbiz.core.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.qloudbiz.core.utils.ConnectionUtils;

public class ServiceProxyFactory {
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(Class<T> targetClazz) throws Exception{
		
		T target=targetClazz.newInstance();
		
		T proxy=(T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),new InvocationHandler () {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
				ConnectionUtils.beginTransaction();
				Object obj=method.invoke(target, args);
				System.out.println("commit transaction");
				ConnectionUtils.commitTransaction();
				return obj;
			}
		});
		return proxy;
		
	}
}
