package cn.xiedacon.vo;

import java.util.Date;

public class UserVo {

	private String id;
	private String name;
	private String icon;
	private String sex;
	private Integer level;
	private Integer dynamicNum;
	private Integer attentionNum;
	private Integer fansNum;
	private Date birthday;
	private String age;
	private String area;
	private String introduction;
	private Integer createSongMenuNum;
	private Integer collectSongMenuNum;
	private String singerId;
	private Boolean isSigned;

	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getIsSigned() {
		return isSigned;
	}

	public void setIsSigned(Boolean isSigned) {
		this.isSigned = isSigned;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getDynamicNum() {
		return dynamicNum;
	}

	public void setDynamicNum(Integer dynamicNum) {
		this.dynamicNum = dynamicNum;
	}

	public Integer getAttentionNum() {
		return attentionNum;
	}

	public void setAttentionNum(Integer attentionNum) {
		this.attentionNum = attentionNum;
	}

	public Integer getFansNum() {
		return fansNum;
	}

	public void setFansNum(Integer fansNum) {
		this.fansNum = fansNum;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Integer getCreateSongMenuNum() {
		return createSongMenuNum;
	}

	public void setCreateSongMenuNum(Integer createSongMenuNum) {
		this.createSongMenuNum = createSongMenuNum;
	}

	public Integer getCollectSongMenuNum() {
		return collectSongMenuNum;
	}

	public void setCollectSongMenuNum(Integer collectSongMenuNum) {
		this.collectSongMenuNum = collectSongMenuNum;
	}

	public String getSingerId() {
		return singerId;
	}

	public void setSingerId(String singerId) {
		this.singerId = singerId;
	}

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", icon=" + icon + ", sex=" + sex + ", level=" + level
				+ ", dynamicNum=" + dynamicNum + ", attentionNum=" + attentionNum + ", fansNum=" + fansNum
				+ ", birthday=" + birthday + ", age=" + age + ", area=" + area + ", introduction=" + introduction
				+ ", createSongMenuNum=" + createSongMenuNum + ", collectSongMenuNum=" + collectSongMenuNum
				+ ", singerId=" + singerId + ", isSigned=" + isSigned + ", phone=" + phone + "]";
	}

}
