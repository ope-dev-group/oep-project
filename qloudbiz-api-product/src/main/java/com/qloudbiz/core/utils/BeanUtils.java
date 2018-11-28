package com.qloudbiz.core.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.qloudbiz.core.annotations.Pojo;

/**
 * bean工具类
 * @author Administrator
 *
 */
public class BeanUtils {
	
	/**
	 * map转对象
	 * @param map
	 * @param beanClass
	 * @return
	 */
	public static <T> T mapToObject(Map map,Class<T> beanClass){
		if(null==map) return null;
		
		
		T object=null;
		try {
			//通过class反射创建对象
			object=(T)beanClass.newInstance();
			
			//通过class反射获取字段
			Field[] fields=beanClass.getDeclaredFields();
			
			//遍历fields
			for(Field field:fields){
				//设置field可访问
				field.setAccessible(true);
				
				//判断字段上是否有注解,有注解则获取注解上的字段名称
				String fieldName=field.getName();
				if(field.isAnnotationPresent(Pojo.class)){
					Pojo anno=field.getAnnotation(Pojo.class);
					
					if(StringUtils.isNotEmpty(anno.name())){
						fieldName=anno.name();
					}
				}
				
				//为字段获取设置值
				Object fieldValue=map.get(fieldName);
				if(null!=fieldValue){
					
					field.set(object, fieldValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}
	
	/**
	 * bean 转 map
	 * @param bean
	 * @return
	 */
	public static Map<String,Object> objectToMap(Object bean){
		if(null==bean) return null;
		
		try {
			//通过对象获取class
			Class beanClass=bean.getClass();
			
			//获取对象的所有字段
			Field[] fields=beanClass.getDeclaredFields();
			if(null!=fields && fields.length>0){
				Map<String,Object> map=new HashMap<String,Object>();
				for(Field field:fields){
					
					//设置field可访问
					field.setAccessible(true);
					
				
					String fieldName=field.getName();
					
					if(field.isAnnotationPresent(Pojo.class)){
						Pojo anno=field.getAnnotation(Pojo.class);
						if(null!=anno && StringUtils.isNotEmpty(anno.name())){
							fieldName=anno.name();
						}
					}
					
					Object fieldValue=field.get(bean);
					map.put(fieldName, fieldValue);
				}
				
				return map;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
