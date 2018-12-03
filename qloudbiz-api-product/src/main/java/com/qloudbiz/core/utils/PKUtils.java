package com.qloudbiz.core.utils;

import java.util.UUID;

/**
 * 主键生成器
 * @author Administrator
 *
 */
public class PKUtils {
	public static String genPK(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}
