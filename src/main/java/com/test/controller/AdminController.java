package com.test.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.Recommend;
import com.test.enity.User;
import com.test.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class AdminController {
	@Autowired
	AdminService adminservice;

	// 登陆
	@PostMapping("/login")
	@ResponseBody
	public String login(String username, String password) {
		String result = adminservice.login(username, password);
		return result;
	}

	// 注销
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		return adminservice.logout(request);
	}

	// 获取推荐
	@GetMapping("/getAllRecommends")
	public List<Recommend> getAllRecommends() {
		return adminservice.getAllRecommends();
	}

	// 添加推荐
	@PostMapping("/addRecommend")
	@ResponseBody
	public void addRecommend(@RequestParam("img") String url, @RequestParam("remark") String remark) {
		adminservice.addRecommend(url, remark);
	}

	// 删除全部推荐
	@GetMapping("/deleteAllRecommends")
	@ResponseBody
	public void deleteAllRecommends(@RequestParam("id") String id) {
		List<String> list = Arrays.asList(id.split(","));
		for (int i = 0; i < list.size(); i++)
			adminservice.deleteRecommend(list.get(i));
	}

	// 删除一个推荐
	@GetMapping("/deleteRecommend")
	public void deleteRecommend(@RequestParam("id") String id) {
		adminservice.deleteRecommend(id);
	}
}
