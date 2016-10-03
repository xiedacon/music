package cn.xiedacon.vo;

import java.util.Date;

public class AlbumVo {

	private String id;
	private String name;
	private String remark;
	private String icon;
	private String singerName;
	private String singerId;
	private Date createTime;
	private String createCompany;
	private Integer shareNum;
	private Integer commentNum;
	private String introduction;
	private Integer songNum;

	@Override
	public String toString() {
		return "AlbumVo [id=" + id + ", name=" + name + ", remark=" + remark + ", icon=" + icon + ", singerName="
				+ singerName + ", singerId=" + singerId + ", createTime=" + createTime + ", createCompany="
				+ createCompany + ", shareNum=" + shareNum + ", commentNum=" + commentNum + ", introduction="
				+ introduction + ", songNum=" + songNum + "]";
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSingerName() {
		return singerName;
	}

	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}

	public String getSingerId() {
		return singerId;
	}

	public void setSingerId(String singerId) {
		this.singerId = singerId;
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

}
