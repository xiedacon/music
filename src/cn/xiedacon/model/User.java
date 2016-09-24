package cn.xiedacon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class User {

	private String id;
	private String name;
	private String icon;
	private String sex;
	private Integer dynamicNum;
	private Integer attentionNum;
	private Integer fansNum;
	private String introduction;
	private String area;
	private String age;
	private Integer createSongMenuNum;
	private Integer collectSongMenuNum;
	private Date birthday;
	private Integer level;
	private Integer experience;
	private String singerId;
	private Date lastSignDate;
	private Boolean visible;

	private String qq;
	private String phone;
	private String weixin;
	private String xinlangweibo;
	private String renren;
	private String douban;
	private String password;
	private String githubId;

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getSingerId() {
		return singerId;
	}

	public void setSingerId(String singerId) {
		this.singerId = singerId;
	}

	public Date getLastSignDate() {
		return lastSignDate;
	}

	public void setLastSignDate(Date lastSignDate) {
		this.lastSignDate = lastSignDate;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getXinlangweibo() {
		return xinlangweibo;
	}

	public void setXinlangweibo(String xinlangweibo) {
		this.xinlangweibo = xinlangweibo;
	}

	public String getRenren() {
		return renren;
	}

	public void setRenren(String renren) {
		this.renren = renren;
	}

	public String getDouban() {
		return douban;
	}

	public void setDouban(String douban) {
		this.douban = douban;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getGithubId() {
		return githubId;
	}

	public void setGithubId(String githubId) {
		this.githubId = githubId;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", icon=" + icon + ", sex=" + sex + ", dynamicNum=" + dynamicNum
				+ ", attentionNum=" + attentionNum + ", fansNum=" + fansNum + ", introduction=" + introduction
				+ ", area=" + area + ", age=" + age + ", createSongMenuNum=" + createSongMenuNum
				+ ", collectSongMenuNum=" + collectSongMenuNum + ", birthday=" + birthday + ", level=" + level
				+ ", experience=" + experience + ", singerId=" + singerId + ", lastSignDate=" + lastSignDate + ", qq="
				+ qq + ", phone=" + phone + ", weixin=" + weixin + ", xinlangweibo=" + xinlangweibo + ", renren="
				+ renren + ", douban=" + douban + ", password=" + password + ", githubId=" + githubId + "]";
	}

}
