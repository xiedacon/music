package cn.xiedacon.vo;

import java.util.List;

import cn.xiedacon.util.PageBean;

public class CommentsVo {

	private List<CommentVo> hotList;
	private PageBean<CommentVo> pageBean;

	public List<CommentVo> getHotList() {
		return hotList;
	}

	public void setHotList(List<CommentVo> hotList) {
		this.hotList = hotList;
	}

	public PageBean<CommentVo> getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean<CommentVo> pageBean) {
		this.pageBean = pageBean;
	}

	@Override
	public String toString() {
		return "CommentsVo [hotList=" + hotList + ", pageBean=" + pageBean + "]";
	}

}
