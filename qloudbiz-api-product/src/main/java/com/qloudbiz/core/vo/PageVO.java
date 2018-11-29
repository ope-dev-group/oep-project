package com.qloudbiz.core.vo;

import lombok.Data;

/**
 * 分页参数VO
 * @author Administrator
 *
 */
@Data
public class PageVO {
	private Integer pagePerNum=null;//pageSize,页大小
	private Integer currentNum=null;//当前页
}
