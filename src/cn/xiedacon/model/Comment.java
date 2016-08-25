package cn.xiedacon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Comment {

	private String id;
	private String content;
	private Date createTime;
	private Integer agreeNum;
	
	private String creatorId;
	private String creatorName;
	private String creatorIcon;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorIcon() {
		return creatorIcon;
	}
	public void setCreatorIcon(String creatorIcon) {
		this.creatorIcon = creatorIcon;
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
	@Override
	public String toString() {
		return "Comment [id=" + id + ", creatorName=" + creatorName + ", creatorId=" + creatorId + ", creatorIcon="
				+ creatorIcon + ", content=" + content + ", createTime=" + createTime + ", agreeNum=" + agreeNum + "]";
	}
	
	
}
