package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SecondClassify {

	private String id;
	private String name;
	private String firstClassifyId;

	private Boolean visible;

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
