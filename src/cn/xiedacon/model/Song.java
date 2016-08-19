package cn.xiedacon.model;

public class Song {

	private String id;
	private String name;
	private String icon;
	private String time;
	private String lyricFile;
	private String singerName;
	private String ablumName;
	private String remark;
	private Singer singer;
	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", icon=" + icon + ", time=" + time + ", lyricFile=" + lyricFile
				+ ", singerName=" + singerName + ", ablumName=" + ablumName + ", remark=" + remark + ", singer="
				+ singer + "]";
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLyricFile() {
		return lyricFile;
	}
	public void setLyricFile(String lyricFile) {
		this.lyricFile = lyricFile;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public String getAblumName() {
		return ablumName;
	}
	public void setAblumName(String ablumName) {
		this.ablumName = ablumName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Singer getSinger() {
		return singer;
	}
	public void setSinger(Singer singer) {
		this.singer = singer;
	}
}
