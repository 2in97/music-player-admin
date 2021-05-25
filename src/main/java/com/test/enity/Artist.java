package com.test.enity;

public class Artist {
	private String id;
	private String name;
	private String language;
	private String birthday;
	private String detail;
	private String avatar;
	public Artist(String id, String name, String language, String birthday, String detail, String avatar) {
		super();
		this.id = id;
		this.name = name;
		this.language = language;
		this.birthday = birthday;
		this.detail = detail;
		this.avatar = avatar;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
