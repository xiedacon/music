package cn.xiedacon.vo;

public class SongVo {

	private String id;
	private String name;
	private String icon;
	private String remark;
	private String time;
	private String singerId;
	private String singerName;
	private String albumId;
	private String albumName;
	private Integer rank;
	private Integer rankChange;
	private Integer commentNum;
	private Integer collectionNum;
	
	public Integer getCollectionNum() {
		return collectionNum;
	}
	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}
	public Integer getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	public Integer getRankChange() {
		return rankChange;
	}
	public void setRankChange(Integer rankChange) {
		this.rankChange = rankChange;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "SongVo [id=" + id + ", name=" + name + ", icon=" + icon + ", remark=" + remark + ", time=" + time
				+ ", singerId=" + singerId + ", singerName=" + singerName + ", albumId=" + albumId + ", albumName="
				+ albumName + ", rank=" + rank + ", rankChange=" + rankChange + ", commentNum=" + commentNum
				+ ", collectionNum=" + collectionNum + "]";
	}
	
}
