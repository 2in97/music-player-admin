package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.enity.Recommend;
import com.test.enity.User;

public interface AdminService {
	boolean selectUser(String username, String password);
	String login(String username, String password);
	String logout(HttpServletRequest request);
	List<Recommend> getAllRecommends();
	void addRecommend(String url, String remark);
	void deleteRecommend(String id);
}
