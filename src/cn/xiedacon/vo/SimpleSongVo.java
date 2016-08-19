package cn.xiedacon.vo;

public class SimpleSongVo {

	private String id;
	private String name;
	private String icon;
	private String time;
	private String singerId;
	private String singerName;
	private String albumId;
	private String albumName;
	private String rank;
	private Integer rankChange;
	
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
	public String getSingerId() {
		return singerId;
	}
	public void setSingerId(String singerId) {
		this.singerId = singerId;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Integer getRankChange() {
		return rankChange;
	}
	public void setRankChange(Integer rankChange) {
		this.rankChange = rankChange;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
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
		return "SimpleSongVo [id=" + id + ", name=" + name + ", icon=" + icon + ", time=" + time + ", singerId="
				+ singerId + ", singerName=" + singerName + ", albumId=" + albumId + ", albumName=" + albumName
				+ ", rank=" + rank + ", rankChange=" + rankChange + "]";
	}
	
	
}
