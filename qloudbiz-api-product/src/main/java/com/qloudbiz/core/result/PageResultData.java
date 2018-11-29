package com.qloudbiz.core.result;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 返回分页数据
 * @author Administrator
 *
 * @param <T>
 */
@Data
public class PageResultData<T> extends ResultData<List<T>> {
	//String pgUpDownFlag;//上一页，下一页标识,next-下一页，pre-上一页
	int totalNum=0;  //总记录数
	int pagePerNum=20;//pageSize,页大小
	int currentNum=1;//当前页
	int totalPage=0; //总页数
	int prePage;   //前一页
	int nextPage;  //下一页
	
	
	
	public PageResultData() {
		super();
	}
	
	
	
	@Override
	public String toString() {
		return "PageResultData [totalNum=" + totalNum + ", pagePerNum="
				+ pagePerNum + ", currentNum=" + currentNum + ", totalPage="
				+ totalPage + ", prePage=" + prePage + ", nextPage=" + nextPage+result
				+ "]";
	}



	public PageResultData(int totalNum, int pagePerNum, int currentNum,
			int totalPage, int prePage, int nextPage) {
		super();
		this.totalNum = totalNum;
		this.pagePerNum = pagePerNum;
		this.currentNum = currentNum;
		this.totalPage = totalPage;
		this.prePage = prePage;
		this.nextPage = nextPage;
	}
	
	
}
