package com.test.enity;

public class SongList {
	private int id;
	private String lname;
	private String lid;
	private String utel;
	private int tag;
	private String tagName;
	private String detail;
	private String cover;
	private String time;



	public SongList(int id, String lname, String lid, String utel, int tag, String tagName, String detail, String cover,
			String time) {
		super();
		this.id = id;
		this.lname = lname;
		this.lid = lid;
		this.utel = utel;
		this.tag = tag;
		this.tagName = tagName;
		this.detail = detail;
		this.cover = cover;
		this.time = time;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public SongList() {
		super();
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
}
