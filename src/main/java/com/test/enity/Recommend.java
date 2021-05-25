package com.test.enity;

public class Recommend {
	private int id;
	private String url;
	private String remark;
	public Recommend(int id, String url, String remark) {
		super();
		this.id = id;
		this.url = url;
		this.remark = remark;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
