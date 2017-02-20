package cn.xiedacon.util;

import java.util.List;

/**
 * <h1>分页工具类</h1>
 * 
 * @author xiedacon
 * @version v0.0.0
 *
 * @param <T>
 */
public class PageBean<T> {

	private Integer page;
	private Integer totalPage;
	private Integer limit;
	private Integer count;
	private List<T> beans;

	public PageBean(Integer page, Integer limit, Integer count, List<T> beans) {
		this.page = page;
		this.limit = limit;
		this.count = count;
		this.totalPage = count / limit + (count % limit == 0 ? 0 : 1);
		this.beans = beans;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public List<T> getBeans() {
		return beans;
	}

	public void setBeans(List<T> beans) {
		this.beans = beans;
	}

	@Override
	public String toString() {
		return "PageBean [page=" + page + ", totalPage=" + totalPage + ", limit=" + limit + ", count=" + count
				+ ", beans=" + beans + "]";
	}

}
