package cn.huat.util;

import java.util.List;


public class PageUtil<T> {
	//总记录数
	private Integer total=0;
	//当前页
	private Integer page = 1;
	//每页显示记录数
	private Integer pageSize = 10;
	// 总页数
	private Integer totalPage = 0;
	// 开始条数
	private Integer startTotal;
	private List<T> data;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		int count = this.total%this.pageSize==0?this.total/this.pageSize:(this.total/this.pageSize+1);
		return this.total%this.pageSize==0?this.total/this.pageSize:(this.total/this.pageSize+1);
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public Integer getStartTotal() {
		return (this.page-1)*this.pageSize+1;
	}
	public Integer getStart() {
		return (this.page-1)*this.pageSize;
	}
	@Override
	public String toString() {
		return "PageUtil [total=" + total + ", page=" + page + ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", startTotal=" + startTotal + ", data=" + data + "]";
	}
	


	
	
}