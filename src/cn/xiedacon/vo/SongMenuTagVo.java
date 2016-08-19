package cn.xiedacon.vo;

import java.util.List;

public class SongMenuTagVo {

	private String id;
	private String name;
	private List<SongMenuSecondTagVo> secondTagList;
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
	public List<SongMenuSecondTagVo> getSecondTagList() {
		return secondTagList;
	}
	public void setSecondTagList(List<SongMenuSecondTagVo> secondTagList) {
		this.secondTagList = secondTagList;
	}
}
