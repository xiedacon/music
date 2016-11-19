package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author xieda
 *
 */
@JsonInclude(Include.NON_NULL)
public class SongMenuSecondTag {

	// 本表数据
	private String id;
	private String name;
	private String firstTagId;
	private Boolean visible;

	public SongMenuSecondTag() {
	}

	public SongMenuSecondTag(String id) {
		super();
		this.id = id;
	}

	public SongMenuSecondTag(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getFirstTagId() {
		return firstTagId;
	}

	public void setFirstTagId(String firstTagId) {
		this.firstTagId = firstTagId;
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
}
