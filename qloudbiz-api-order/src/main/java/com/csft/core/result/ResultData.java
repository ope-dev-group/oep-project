package com.csft.core.result;

import lombok.Data;

/**
 * 对象返回
 * @author Administrator
 *
 * @param <T>
 */
@Data
public class ResultData<T> extends BaseResult{
	T result;

	
	@Override
	public String toString() {
		return "ResultData [result=" + result + "]";
	}
	
	
}
