package cn.xiedacon.vo;

import java.util.List;

public class SimpleSongListVo {

	private String id;
	private String name;
	private String icon;
	private List<SimpleSongVo> songs;
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
	public List<SimpleSongVo> getSongs() {
		return songs;
	}
	public void setSongs(List<SimpleSongVo> songs) {
		this.songs = songs;
	}
	@Override
	public String toString() {
		return "SimpleSongListVo [id=" + id + ", name=" + name + ", icon=" + icon + ", songList=" + songs + "]";
	}
}
