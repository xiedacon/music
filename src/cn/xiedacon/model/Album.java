package cn.xiedacon.model;

import java.util.Date;

public class Album {

	private String id;
	private String name;
	private String icon;
	private String renark;
	private String singerName;
	private Date createTime;
	private String createCompany;
	private String introduction;
	private Integer songNum;
	private Singer singer;
	private Integer playNum;
	private AlbumTag tag;
	public Integer getPlayNum() {
		return playNum;
	}
	public void setPlayNum(Integer playNum) {
		this.playNum = playNum;
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
	public String getRenark() {
		return renark;
	}
	public void setRenark(String renark) {
		this.renark = renark;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateCompany() {
		return createCompany;
	}
	public void setCreateCompany(String createCompany) {
		this.createCompany = createCompany;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Integer getSongNum() {
		return songNum;
	}
	public void setSongNum(Integer songNum) {
		this.songNum = songNum;
	}
	public Singer getSinger() {
		return singer;
	}
	public void setSinger(Singer singer) {
		this.singer = singer;
	}
	public AlbumTag getTag() {
		return tag;
	}
	public void setTag(AlbumTag tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Ablum [id=" + id + ", name=" + name + ", icon=" + icon + ", renark=" + renark + ", singerName="
				+ singerName + ", createTime=" + createTime + ", createCompany=" + createCompany + ", introduction="
				+ introduction + ", songNum=" + songNum + ", singer=" + singer + ", playNum=" + playNum + ", tag=" + tag
				+ "]";
	}
	
}
