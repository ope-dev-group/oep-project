package com.qloudbiz.core.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceProxyFactory {
	@SuppressWarnings("unchecked")
	public static <T> T createProxy(Class<T> targetClazz) throws Exception{
		
		T target=targetClazz.newInstance();
		
		T proxy=(T)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),new InvocationHandler () {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
				System.out.println("start transaction");
				Object obj=method.invoke(target, args);
				System.out.println("commit transaction");
				return obj;
			}
		});
		return proxy;
		
	}
}
