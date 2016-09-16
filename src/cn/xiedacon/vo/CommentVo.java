package cn.xiedacon.vo;

import java.util.Date;

public class CommentVo {

	private String id;
	private String content;
	private Date createTime;
	private Integer agreeNum;
	private SimpleUserVo creator;

	@Override
	public String toString() {
		return "CommentVo [id=" + id + ", content=" + content + ", createTime=" + createTime + ", agreeNum=" + agreeNum
				+ ", creator=" + creator + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAgreeNum() {
		return agreeNum;
	}

	public void setAgreeNum(Integer agreeNum) {
		this.agreeNum = agreeNum;
	}

	public SimpleUserVo getCreator() {
		return creator;
	}

	public void setCreator(SimpleUserVo creator) {
		this.creator = creator;
	}
}
