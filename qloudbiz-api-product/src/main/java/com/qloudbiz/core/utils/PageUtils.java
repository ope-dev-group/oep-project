package com.qloudbiz.core.utils;

import java.util.List;

/**
 * 分页工具类
 * @author Administrator
 *
 */
public class PageUtils {
	int pageSize=20;//页大小
	int totalCount=0;//总记录数
	int currentPage=1;//当前页
	int nextPage;//下一页
	int prePage;//上一页
	int totalPage=0;//总页数
	int startIndex=0;//开始行号
	int endIndex;//结束行号
	
	private List<?> items;//数据
	
	public PageUtils(int pageSize, int totalCount, int currentPage) {
		this.caculatePage(pageSize, totalCount, currentPage);
	}
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getNextPage() {
		return nextPage;
	}
	
	public int getPrePage() {
		return prePage;
	}
	
	
	
	public int getTotalPage() {
		return totalPage;
	}

	
	/**
	 * 计算分页
	 * @param pageSize
	 * @param totalCount
	 * @param currentPage
	 */
	private void caculatePage(int pageSize, int totalCount, int currentPage){
		//设置总记录数
		
		if(totalCount>0){
			this.totalCount = totalCount;
		}
		
		
		//设置页大小
		if(pageSize>0){
			this.pageSize = pageSize;
		}
		
		//设置总页数
		if(totalCount==0){
			this.totalPage=0;
		}else if(totalCount%pageSize==0){
			this.totalPage=totalCount/pageSize;
		}else{
			this.totalPage=(totalCount/pageSize)+1;
		}
		
		//设置当前页
		if(currentPage>this.totalPage){
			this.currentPage=totalPage;
		}else if(currentPage<1){
			this.currentPage=1;
		}else{
			this.currentPage=currentPage;
		}
		
		//设置下一页
		if(this.currentPage<this.totalPage){
			this.nextPage=this.currentPage+1;
		}else{
			this.nextPage=this.currentPage;
		}
		
		//设置前一页
		if(this.currentPage>1){
			this.prePage=this.currentPage-1;
		}else{
			this.prePage=this.currentPage;
		}
	
		//设置开始行号
		this.startIndex=(this.currentPage-1)*this.pageSize;
		
		//设置结束行号
		if(this.startIndex+this.pageSize>this.totalCount){
			this.endIndex=this.totalCount-1;
		}else{
			this.endIndex=this.startIndex+this.pageSize-1;
		}
	}


	public List<?> getItems() {
		return items;
	}


	public void setItems(List<?> items) {
		this.items = items;
	}


	@Override
	public String toString() {
		return "PageUtils [pageSize=" + pageSize + ", totalCount=" + totalCount
				+ ", currentPage=" + currentPage + ", nextPage=" + nextPage
				+ ", prePage=" + prePage + ", totalPage=" + totalPage
				+ ", startIndex=" + startIndex + ", endIndex=" + endIndex
				+ ", items=" + items + "]";
	}


	public int getStartIndex() {
		return startIndex;
	}


	public int getEndIndex() {
		return endIndex;
	}
	
	
}
