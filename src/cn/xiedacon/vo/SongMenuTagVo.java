package cn.xiedacon.vo;

import java.util.List;

import cn.xiedacon.model.SongMenuSecondTag;

public class SongMenuTagVo {

	private String id;
	private String name;
	private List<SongMenuSecondTag> secondTagList;
	@Override
	public String toString() {
		return "SongMenuTagVo [id=" + id + ", name=" + name + ", secondTagList=" + secondTagList + "]";
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
	public List<SongMenuSecondTag> getSecondTagList() {
		return secondTagList;
	}
	public void setSecondTagList(List<SongMenuSecondTag> secondTagList) {
		this.secondTagList = secondTagList;
	}
}
