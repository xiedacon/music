package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Singer {

	// 本表数据
	private String id;
	private String name;
	private String icon;
	private String remark;
	private String introduction;
	private String classifyId;
	private String userId;
	private Boolean visible;
	// 记录相关
	private Integer collectionNum;

	public Singer() {
		super();
	}

	public Singer(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Override
	public String toString() {
		return "Singer [id=" + id + ", name=" + name + ", icon=" + icon + ", remark=" + remark + ", introduction="
				+ introduction + ", classifyId=" + classifyId + ", userId=" + userId + ", visible=" + visible
				+ ", collectionNum=" + collectionNum + "]";
	}

}
