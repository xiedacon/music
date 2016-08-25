package cn.xiedacon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class SongList {

	private String id;
	private String name;
	private String icon;
	private String refreshRate;
	private Date refreshDate;
	private Integer songNum;

	private Integer collectionNum;
	private Integer shareNum;
	private Integer commentNum;
	private Integer playNum;

	private Boolean globe;

	public Date getRefreshDate() {
		return refreshDate;
	}

	public void setRefreshDate(Date refreshDate) {
		this.refreshDate = refreshDate;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public Integer getShareNum() {
		return shareNum;
	}

	public void setShareNum(Integer shareNum) {
		this.shareNum = shareNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getPlayNum() {
		return playNum;
	}

	public void setPlayNum(Integer playNum) {
		this.playNum = playNum;
	}

	public Boolean getGlobe() {
		return globe;
	}

	public void setGlobe(Boolean globe) {
		this.globe = globe;
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
				+ ", refreshDate=" + refreshDate + ", songNum=" + songNum + ", collectionNum=" + collectionNum
				+ ", shareNum=" + shareNum + ", commentNum=" + commentNum + ", playNum=" + playNum + ", globe=" + globe
				+ "]";
	}

}
