package com.test.enity;

public class User {
	private int id;
	private String username;
	private String password;
	private String tel;
	private String avatar;
	private String introduction;
	private String sex;
	private int age;
	
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public User(int id, String username, String password, String tel, String avatar, String introduction, String sex,
			int age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.avatar = avatar;
		this.introduction = introduction;
		this.sex = sex;
		this.age = age;
	}
	
}
