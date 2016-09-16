package cn.xiedacon.vo;

public class SimpleUserVo {

	private String id;
	private String name;
	private String icon;
	private String introduction;
	private Integer dynamicNum;
	private Integer attentionNum;
	private Integer fansNum;
	private String sex;

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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Override
	public String toString() {
		return "SimpleUserVo [id=" + id + ", name=" + name + ", icon=" + icon + ", introduction=" + introduction
				+ ", dynamicNum=" + dynamicNum + ", attentionNum=" + attentionNum + ", fansNum=" + fansNum + ", sex="
				+ sex + "]";
	}

}
