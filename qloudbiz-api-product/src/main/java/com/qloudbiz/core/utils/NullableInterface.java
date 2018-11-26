package com.qloudbiz.core.utils;

/**
 * Nullable是空对象的相关操作接口，
 * 用于确定对象是否为空，因为在空对象模式中，对象为空会被包装成一个Object，
 * 成为Null Object，该对象会对原有对象的所有方法进行空实现
 * @author LCY
 *
 */
public interface NullableInterface {
	
	boolean isNull();

}
