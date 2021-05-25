package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.test.enity.Album;
import com.test.enity.Artist;
import com.test.enity.Page;
import com.test.enity.Song;
import com.test.enity.SongList;
import com.test.enity.User;
import com.test.service.UserService;
import com.test.util.SMSUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {
	@Autowired
	UserService userservice;

	/**
	 * @Description:发送手机验证码
	 * @Param:手机号码
	 * @return:1表示成功，0表示失败
	 */
	// 获取验证码
	@PostMapping("/getVerification")
	@ResponseBody
	public boolean getVerification(String tel) {
		userservice.getVerification(tel);
		return true;
	}

	/**
	 * @Description:校验验证码是否正确
	 * @Param:验证码
	 * @return:成功返回OK，验证码超时返回TimeOut，验证码错误返回CodeError
	 * @throws Exception
	 */
	// 登陆
	@PostMapping("/login")
	@ResponseBody
	public String login(String tel, String code) throws Exception {
		String result = userservice.login(tel, code);
		return result;
	}
	
	// 获得头像
	@PostMapping("/getAvatar")
		@ResponseBody
		public String getAvatar(String tel) {
			return userservice.getAvatar(tel);
		}

	// 注销
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		return userservice.logout(request);
	}

	// 获得热门歌单
	@GetMapping("/getHotSongLists")
	@ResponseBody
	public List<SongList> getHotSongLists() {
		return userservice.getHotSongLists();
	}

	// 获得新歌
	@GetMapping("/getLastedSongs")
	@ResponseBody
	public List<Song> getLastedSongs() {
		return userservice.getLastedSongs();
	}

	// 获得热门歌手
	@GetMapping("/getHotArtists")
	@ResponseBody
	public List<Artist> getHotArtists() {
		return userservice.getHotArtists();
	}

	// 获得个人信息
	@PostMapping("/getInfo")
	@ResponseBody
	public User getInfo(String tel) {
		return userservice.getInfo(tel);
	}

	// 更新个人信息
	@PostMapping("/updateInfo")
	@ResponseBody
	public void updateInfo(@RequestParam("tel") String tel, @RequestParam("username") String username,
			@RequestParam("avatar") String avatar, @RequestParam("introduction") String introduction,
			@RequestParam("sex") String sex, @RequestParam("age") String age) {
		userservice.updateInfo(tel, username, avatar, introduction, sex, age);
	}

	// 检查是否收藏
	@GetMapping("/songIsMark")
	@ResponseBody
	public boolean songIsMark(@RequestParam("sid") String sid, @RequestParam("tel") String tel) {
		return userservice.songIsMark(sid, tel);
	}

	// 收藏歌曲
	@PostMapping("/addToFavoSong")
	@ResponseBody
	public void addToFavoSong(String sid, String tel) {
		userservice.addToFavoSong(sid, tel);
	}

	// 取消收藏歌曲
	@PostMapping("/deleteFromFavoSong")
	@ResponseBody
	public void deleteFromFavoSong(String sid, String tel) {
		userservice.deleteFromFavoSong(sid, tel);
	}

	// 检查是否收藏专辑
	@GetMapping("/albumIsMark")
	@ResponseBody
	public boolean albumIsMark(@RequestParam("aid") String aid, @RequestParam("tel") String tel) {
		return userservice.albumIsMark(aid, tel);
	}

	// 收藏专辑
	@PostMapping("/addToFavoAlbum")
	@ResponseBody
	public void addToFavoAlbum(String aid, String tel) {
		userservice.addToFavoAlbum(aid, tel);
	}

	// 取消收藏专辑
	@PostMapping("/deleteFromFavoAlbum")
	@ResponseBody
	public void deleteFromFavoAlbum(String aid, String tel) {
		userservice.deleteFromFavoAlbum(aid, tel);
	}

	// 获得我的收藏歌曲
	@RequestMapping("/getMyFavoSong")
	@ResponseBody
	public List<Song> getMyFavoSong(@RequestParam("pageNum") int pageNum, @RequestParam("tel") String tel) {
		return userservice.getMyFavoSong(pageNum, tel);
	}

	// 获取收藏歌曲页数
	@RequestMapping("/getMFSPage")
	@ResponseBody
	public Page getMFSPage(String tel) {
		return userservice.getMFSPage(tel);
	}

	// 获得我的收藏专辑
	@RequestMapping("/getMyFavoAlbum")
	@ResponseBody
	public List<Album> getMyFavoAlbum(@RequestParam("pageNum") int pageNum, @RequestParam("tel") String tel) {
		return userservice.getMyFavoAlbum(pageNum, tel);
	}

	// 获取收藏专辑页数
	@RequestMapping("/getMFAPage")
	@ResponseBody
	public Page getMFAPage(String tel) {
		return userservice.getMFAPage(tel);
	}

	// 获得我的收藏歌单
	@RequestMapping("/getMyFavoList")
	@ResponseBody
	public List<SongList> getMyFavoList(@RequestParam("pageNum") int pageNum, @RequestParam("tel") String tel) {
		return userservice.getMyFavoList(pageNum, tel);
	}

	// 获取收藏歌单页数
	@RequestMapping("/getMFLPage")
	@ResponseBody
	public Page getMFLPage(String tel) {
		return userservice.getMFLPage(tel);
	}

	// 检查是否收藏歌单
		@GetMapping("/listIsMark")
		@ResponseBody
		public boolean listIsMark(@RequestParam("lid") String lid, @RequestParam("tel") String tel) {
			return userservice.listIsMark(lid, tel);
		}
		
		// 收藏歌单
		@PostMapping("/addToFavoList")
		@ResponseBody
		public void addToFavoList(String lid, String tel) {
			userservice.addToFavoList(lid, tel);
		}

		// 取消收藏歌单
		@PostMapping("/deleteFromFavoList")
		@ResponseBody
		public void deleteFromFavoList(String lid, String tel) {
			userservice.deleteFromFavoList(lid, tel);
		}
}
