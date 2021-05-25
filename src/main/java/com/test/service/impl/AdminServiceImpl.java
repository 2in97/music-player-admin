package com.test.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.Recommend;
import com.test.enity.User;
import com.test.mapper.UserMapper;
import com.test.service.AdminService;
import com.test.service.RedisService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	UserMapper usermapper;
	
	@Autowired
	private RedisService redisService;
	
	@Override
	public String login(String username, String password) {
		if (selectUser(username,password)) {
			String token = UUID.randomUUID().toString();
			redisService.set(token, username);
			return token;
		}
		return "登录失败";
	}
	
	@Override
	public String logout(HttpServletRequest request) {
		String token = request.getHeader("token");
		Boolean delete = redisService.delete(token);
		if (!delete) {
			return "注销失败，请检查是否登录！";
		}
		return "注销成功！";
	}
	
	@Override
	public boolean selectUser(String username, String password) {
		User user = usermapper.selectUser(username, password);
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Recommend> getAllRecommends() {
		// TODO Auto-generated method stub
		return usermapper.getAllRecommends();
	}

	@Override
	public void addRecommend(String url, String remark) {
		// TODO Auto-generated method stub
		usermapper.addRecommend(url,remark);
	}

	@Override
	public void deleteRecommend(String id) {
		// TODO Auto-generated method stub
		usermapper.deleteRecommend(id);
	}

}
