package cn.xiedacon.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Song {

	// 基本信息
	// 常用
	private String id;
	private String name;
	private String icon;
	private String time;
	private String remark;
	// 文件相关
	private String lrcUri;
	private String fileUri;
	// 记录相关
	private Integer commentNum;
	private Integer collectionNum;
	private Integer playNum;
	// 可见性相关
	private Boolean visible;

	// 关联相关
	// R
	private String singerName;
	private String albumName;
	private String singerId;
	private String albumId;
	private Integer rank;
	private Integer rankChange;
	// CUD（谁拥有谁维护）
	private SongMenu_SongGL songMenu_SongGL;

	public Song() {
		super();
	}

	public Song(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SongMenu_SongGL getSongMenu_SongGL() {
		return songMenu_SongGL;
	}

	public void setSongMenu_SongGL(SongMenu_SongGL songMenu_SongGL) {
		this.songMenu_SongGL = songMenu_SongGL;
	}

	public Integer getPlayNum() {
		return playNum;
	}

	public void setPlayNum(Integer playNum) {
		this.playNum = playNum;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public String getLrcUri() {
		return lrcUri;
	}

	public void setLrcUri(String lrcUri) {
		this.lrcUri = lrcUri;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getRankChange() {
		return rankChange;
	}

	public void setRankChange(Integer rankChange) {
		this.rankChange = rankChange;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public String getSingerId() {
		return singerId;
	}

	public void setSingerId(String singerId) {
		this.singerId = singerId;
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

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", icon=" + icon + ", time=" + time + ", remark=" + remark
				+ ", singerId=" + singerId + ", singerName=" + singerName + ", albumId=" + albumId + ", albumName="
				+ albumName + ", rank=" + rank + ", rankChange=" + rankChange + ", commentNum=" + commentNum
				+ ", collectionNum=" + collectionNum + ", fileUri=" + fileUri + ", lrcUri=" + lrcUri + "]";
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

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
