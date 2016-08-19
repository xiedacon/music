package cn.xiedacon.model;

public class SongList {

	private String id;
	private String name;
	private String icon;
	private String refreshRate;
	private Integer songNum;
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
	public String getRefreshRate() {
		return refreshRate;
	}
	public void setRefreshRate(String refreshRate) {
		this.refreshRate = refreshRate;
	}
	public Integer getSongNum() {
		return songNum;
	}
	public void setSongNum(Integer songNum) {
		this.songNum = songNum;
	}
	@Override
	public String toString() {
		return "SongList [id=" + id + ", name=" + name + ", icon=" + icon + ", refreshRate=" + refreshRate
				+ ", songNum=" + songNum + "]";
	}
	
}
