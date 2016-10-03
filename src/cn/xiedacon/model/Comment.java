package cn.xiedacon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Comment {

	private String id;
	private String content;
	private Date createTime;
	private Integer agreeNum;
	private Boolean isChecked;

	private String creatorId;
	private String creatorName;
	private String creatorIcon;

	private String songMenuId;
	private String albumId;
	private String songListId;
	private String songId;

	private SongMenu songMenu;
	private Album album;
	private SongList songList;
	private Song song;

	public SongMenu getSongMenu() {
		return songMenu;
	}

	public void setSongMenu(SongMenu songMenu) {
		this.songMenu = songMenu;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public SongList getSongList() {
		return songList;
	}

	public void setSongList(SongList songList) {
		this.songList = songList;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getSongMenuId() {
		return songMenuId;
	}

	public void setSongMenuId(String songMenuId) {
		this.songMenuId = songMenuId;
	}

	public String getAlbumId() {
		return albumId;
	}

	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}

	public String getSongListId() {
		return songListId;
	}

	public void setSongListId(String songListId) {
		this.songListId = songListId;
	}

	public String getSongId() {
		return songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorIcon() {
		return creatorIcon;
	}

	public void setCreatorIcon(String creatorIcon) {
		this.creatorIcon = creatorIcon;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAgreeNum() {
		return agreeNum;
	}

	public void setAgreeNum(Integer agreeNum) {
		this.agreeNum = agreeNum;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", createTime=" + createTime + ", agreeNum=" + agreeNum
				+ ", isChecked=" + isChecked + ", creatorId=" + creatorId + ", creatorName=" + creatorName
				+ ", creatorIcon=" + creatorIcon + ", songMenuId=" + songMenuId + ", albumId=" + albumId
				+ ", songListId=" + songListId + ", songId=" + songId + "]";
	}

}
