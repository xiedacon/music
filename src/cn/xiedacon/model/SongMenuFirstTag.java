package cn.xiedacon.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SongMenuFirstTag {

	private String id;
	private String name;
	private List<SongMenuSecondTag> secondTagList;

	public List<SongMenuSecondTag> getSecondTagList() {
		return secondTagList;
	}

	public void setSecondTagList(List<SongMenuSecondTag> secondTagList) {
		this.secondTagList = secondTagList;
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

	@Override
	public String toString() {
		return "SongMenuFirstTag [id=" + id + ", name=" + name + ", secondTagList=" + secondTagList + "]";
	}
}
