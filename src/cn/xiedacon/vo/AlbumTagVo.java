package cn.xiedacon.vo;

public class AlbumTagVo {

	private String id;
	private String name;
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
		return "AlbumTagVo [id=" + id + ", name=" + name + "]";
	}
}
