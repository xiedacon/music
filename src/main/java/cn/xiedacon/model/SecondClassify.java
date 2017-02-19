package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SecondClassify {

	// 本表数据
	private String id;
	private String name;
	private String firstClassifyId;
	private Boolean visible;

	public SecondClassify() {
		super();
	}

	public SecondClassify(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
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

	public String getFirstClassifyId() {
		return firstClassifyId;
	}

	public void setFirstClassifyId(String firstClassifyId) {
		this.firstClassifyId = firstClassifyId;
	}

}
