package cn.xiedacon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import cn.xiedacon.util.PageBean;

@JsonInclude(Include.NON_NULL)
public class Comments {

	private List<Comment> hotList;
	private PageBean<Comment> pageBean;

	public Comments(List<Comment> hotList, PageBean<Comment> pageBean) {
		super();
		this.hotList = hotList;
		this.pageBean = pageBean;
	}

	public List<Comment> getHotList() {
		return hotList;
	}

	public void setHotList(List<Comment> hotList) {
		this.hotList = hotList;
	}

	public PageBean<Comment> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<Comment> pageBean) {
		this.pageBean = pageBean;
	}

	@Override
	public String toString() {
		return "CommentsVo [hotList=" + hotList + ", pageBean=" + pageBean + "]";
	}

}
