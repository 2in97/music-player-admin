package com.test.enity;


public class Page {
	private int pageNum;
	private int pageSize = 10;
	private int pageTotal;
	private static int recordsNum = -1;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordsNum() {
			return recordsNum;
	}

	public void setRecordsNum(int num) {
		recordsNum = num;
		setPageTotal();
	}

	public void setPageTotal() {
		int mod = recordsNum % pageSize;
		if (mod == 0) {
			pageTotal = recordsNum / pageSize;
		} else {
			pageTotal = recordsNum / pageSize + 1;
		}
	}

	public int getPageTotal() {
		return pageTotal;
	}
}