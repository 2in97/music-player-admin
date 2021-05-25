package com.test.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.enity.Album;
import com.test.enity.Artist;
import com.test.enity.Page;
import com.test.enity.Song;
import com.test.enity.SongList;
import com.test.enity.User;
import com.test.mapper.UserMapper;
import com.test.service.RedisService;
import com.test.service.UserService;
import com.test.util.SMSUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper usermapper;
	Page page = new Page();
	@Autowired
	private RedisService redisService;
	
	@Override
	 public boolean getVerification(String tel) {
	        // 发送短信
	        SMSUtil sendSMS = new SMSUtil();
	        // 获取验证码
	        String code=String.valueOf(sendSMS.sendMes(tel));

	        redisService.set("code", code);
	        redisService.expire("code", 60*5);
	        redisService.set("phone",tel);
	        redisService.expire("phone",60*5);
	        User user = usermapper.selectOneUser(tel);
//	        if(user==null){
//	            usermapper.createNewUser(tel);
//	            usermapper.createNewTable(tel);
//	            usermapper.createNewTable2(tel);
//	            usermapper.createNewTable3(tel);
//	        }
	        return true;
	    }

	
	public String login(String tel, String code) throws Exception {
		if (redisService.get("phone").equals(tel)) {
			String token = UUID.randomUUID().toString();
			System.out.print(redisService.get("code"));
			if(redisService.get("code").equals(code)) {
			redisService.set(token, tel);
			return token;
			}
		}
		throw new Exception("验证码错误");
	}

	public String logout(HttpServletRequest request) {
		/*String token = request.getHeader("token");*/
		Boolean delete = redisService.delete("token");
		if (!delete) {
			return "注销失败，请检查是否登录！";
		}
		return "注销成功！";
	}

	
	@Override
	public List<SongList> getHotSongLists() {
		// TODO Auto-generated method stub
		return usermapper.getHotSongLists();
	}

	@Override
	public List<Song> getLastedSongs() {
		// TODO Auto-generated method stub
		return usermapper.getLastedSongs();
	}


	@Override
	public List<Artist> getHotArtists() {
		// TODO Auto-generated method stub
		return usermapper.getHotArtists();
	}


	@Override
	public User getInfo(String tel) {
		// TODO Auto-generated method stub
		return usermapper.getInfo(tel);
	}


	@Override
	public void updateInfo(String tel, String username, String avatar, String introduction, String sex, String age) {
		// TODO Auto-generated method stub
		usermapper.updateInfo(tel, username, avatar, introduction,sex,age);
	}


	@Override
	public boolean songIsMark(String sid,String tel) {
		// TODO Auto-generated method stub
		if(usermapper.songIsMark(sid,tel)==0)
			return false;
		return true;
	}


	@Override
	public void addToFavoSong(String sid, String tel) {
		// TODO Auto-generated method stub
		usermapper.addToFavoSong(sid,tel);
	}

	@Override
	public void deleteFromFavoSong(String sid, String tel) {
		// TODO Auto-generated method stub
		usermapper.deleteFromFavoSong(sid,tel);
	}


	@Override
	public boolean albumIsMark(String aid, String tel) {
		// TODO Auto-generated method stub
		if(usermapper.albumIsMark(aid,tel)==0)
			return false;
		return true;
	}


	@Override
	public void addToFavoAlbum(String aid, String tel) {
		// TODO Auto-generated method stub
		usermapper.addToFavoAlbum(aid,tel);
	}


	@Override
	public void deleteFromFavoAlbum(String aid, String tel) {
		// TODO Auto-generated method stub
		usermapper.deleteFromFavoAlbum(aid,tel);
	}


	@Override
	public List<Song> getMyFavoSong(int pageNum,String tel) {
		// TODO Auto-generated method stub
		return usermapper.getMyFavoSong(pageNum,tel);
	}


	@Override
	public List<Album> getMyFavoAlbum(int pageNum,String tel) {
		// TODO Auto-generated method stub
		return usermapper.getMyFavoAlbum(pageNum,tel);
	}


	@Override
	public List<SongList> getMyFavoList(int pageNum,String tel) {
		// TODO Auto-generated method stub
		return usermapper.getMyFavoList(pageNum,tel);
	}


	@Override
	public Page getMFSPage(String tel) {
		// TODO Auto-generated method stub
		page.setRecordsNum(usermapper.getMFSNum(tel));///////////////报错找着
		page.setPageTotal();
		return page;
	}


	@Override
	public Page getMFAPage(String tel) {
		// TODO Auto-generated method stub
		page.setRecordsNum(usermapper.getMFANum(tel));///////////////报错找着
		page.setPageTotal();
		return page;
	}


	@Override
	public Page getMFLPage(String tel) {
		// TODO Auto-generated method stub
		page.setRecordsNum(usermapper.getMFLNum(tel));///////////////报错找着
		page.setPageTotal();
		return page;
	}


	@Override
	public boolean listIsMark(String lid, String tel) {
		// TODO Auto-generated method stub
		if(usermapper.listIsMark(lid,tel)==0)
			return false;
		return true;
	}


	@Override
	public void addToFavoList(String lid, String tel) {
		// TODO Auto-generated method stub
		usermapper.addToFavoList(lid,tel);
	}


	@Override
	public void deleteFromFavoList(String lid, String tel) {
		// TODO Auto-generated method stub
		usermapper.deleteFromFavoList(lid,tel);
	}


	@Override
	public String getAvatar(String tel) {
		// TODO Auto-generated method stub
		return usermapper.getAvatar(tel);
	}



}
