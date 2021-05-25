package com.test.enity;

public class Album {
	private String id;
	private String name;
	private String artist;
	private String time;
	private String cover;
	private String detail;
	public Album(String id, String name, String artist, String time, String cover, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.time = time;
		this.cover = cover;
		this.detail = detail;
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
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
