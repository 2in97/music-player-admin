package com.test.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.Album;
import com.test.enity.Page;
import com.test.enity.Recommend;
import com.test.enity.Song;
import com.test.enity.SongList;
import com.test.service.AdminService;
import com.test.service.SongService;

@RestController
@RequestMapping("/song")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class SongController {

	@Autowired
	SongService songservice;

	// 上传歌曲
	@RequestMapping("uploadSong")
	@ResponseBody
	public boolean uploadSong(@RequestParam("name") String name, @RequestParam("picAddress") String picAddress,
			@RequestParam("artist") String artist, @RequestParam("album") String album,
			@RequestParam("songAddress") String songAddress, @RequestParam("type") String type,
			@RequestParam("lyric") String lyric, @RequestParam("detail") String detail) {
		String albumID = songservice.isNewAlbum(artist, album);
		if (songservice.isNewArtist(artist) == null) {// 添加新歌手
			songservice.addNewArtist(artist, picAddress);
		}
		if (album != "" && albumID == null) {// 添加新专辑
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String time = df.format(new Date());
			songservice.createNewAlbum(album, artist, time, picAddress);
			albumID = songservice.isNewAlbum(artist, album);
		}
		String songID = songservice.generateID();
		return songservice.uploadSong(songID, name, picAddress, artist, albumID, songAddress, detail, lyric, type);
	}

	// 更新歌曲
	@RequestMapping("updateSong")
	@ResponseBody
	public void updateSong(@RequestParam("id") String sid, @RequestParam("name") String name,
			@RequestParam("picAddress") String picAddress, @RequestParam("artist") String artist,
			@RequestParam("album") String album, @RequestParam("type") String type, @RequestParam("lyric") String lyric,
			@RequestParam("detail") String detail) {
		String albumID = songservice.isNewAlbum(artist, album);
		if (albumID != null) {
			songservice.updateAlbum(albumID, album, artist);
			songservice.updateSong(sid, name, picAddress, artist, albumID, detail, type, lyric);
		} else if (album != "" && albumID == null) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String time = df.format(new Date());
			songservice.createNewAlbum(album, artist, time, picAddress);
			albumID = songservice.isNewAlbum(artist, album);
			songservice.updateSong(sid, name, picAddress, artist, albumID, detail, type, lyric);
		}
	}

	// 删除全部
	@GetMapping("/deleteAllSongs")
	@ResponseBody
	public void deleteAllSongs(@RequestParam("id") String sid) {
		List<String> list = Arrays.asList(sid.split(","));
		for (int i = 0; i < list.size(); i++)
			songservice.deleteSong(list.get(i));
	}

	// 删除一个
	@GetMapping("/deleteSong")
	public void deleteSong(@RequestParam("id") String sid) {
		songservice.deleteSong(sid);
	}

	// 搜索一首歌
	@GetMapping("/getSongById")
	@ResponseBody
	public Song getSongById(@RequestParam("id") String sid) {
		return songservice.getSongById(sid);
	}

	// 分页查询
	@GetMapping("/getSongsPerPage")
	@ResponseBody
	public List<Song> getSongsPerPage(@RequestParam("pageNum") int pageNum,
			@RequestParam("searchType") String searchType, @RequestParam("search") String search) {
		return songservice.getSongsPerPage(pageNum, searchType, search);
	}

	// 获取页数
	@RequestMapping("/getPage")
	@ResponseBody
	public Page getPage(String searchType, String search) {
		return songservice.getPage(searchType, search);
	}

	// 插入一首最近听的歌
	@GetMapping("/lastListened")
	@ResponseBody
	public void insertLastListened(@RequestParam("id") String sid) {
		songservice.insertLastListened(sid);
	}

	// 最近听歌
	@GetMapping("/getSongsLastListened")
	@ResponseBody
	public List<Song> getSongsLastListened(@RequestParam("pageNum") int pageNum,
			@RequestParam("searchType") String searchType, @RequestParam("search") String search) {
		return songservice.getSongsLastListened(pageNum, searchType, search);
	}

	// 获取歌词
	@GetMapping("/getLyric")
	@ResponseBody
	public String getLyric(@RequestParam("id") String sid) throws IOException, URISyntaxException {
		return songservice.getLyric(sid);
	}

	// 搜索一些歌
	@RequestMapping("/getSongs")
	@ResponseBody
	public List<Song> getSongs(String[] list) {
		List<Song> songList = new ArrayList<Song>();
		for (int i = list.length - 1; i > -1; i--) {
			songList.add(songservice.getSongById(list[i]));
		}
		return songList;
	}

	// 根据歌手搜索歌
	@RequestMapping("/getSongsByArtist")
	@ResponseBody
	public List<Song> getSongsByArtist(@RequestParam("pageNum") int pageNum, @RequestParam("id") String id) {
		return songservice.getSongsByArtist(id, pageNum);
	}

	// 获取歌手歌曲页数
	@RequestMapping("/getSBAPage")
	@ResponseBody
	public Page getSBAPage(String id) {
		return songservice.getSBAPage(id);
	}

	// 根据专辑搜索歌
	@RequestMapping("/getSongsByAlbum")
	@ResponseBody
	public List<Song> getSongsByAlbum(@RequestParam("id") String id) {
		return songservice.getSongsByAlbum(id);
	}

	// 获取我的歌单
	@RequestMapping("/getMySongLists")
	@ResponseBody
	public List<SongList> getMySongLists(@RequestParam("id") String tel) {
		return songservice.getMySongLists(tel);
	}

	// 根据歌手搜索歌
	@RequestMapping("/insertThisList")
	@ResponseBody
	public String insertThisList(@RequestParam("lid") String lid, @RequestParam("sid") String sid) {
		return songservice.insertThisList(lid, sid);
	}

	// 上传歌曲
	@RequestMapping("createSongList")
	@ResponseBody
	public void createSongList(@RequestParam("name") String name, @RequestParam("cover") String cover,
			@RequestParam("tag") String tag, @RequestParam("detail") String detail, @RequestParam("tel") String tel) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String time = df.format(new Date());
		String lid = songservice.generateID();
		songservice.createSongList(name, cover, tag, detail, tel, time, lid);
	}

	// 分页查询
	@GetMapping("/getListsPerPage")
	@ResponseBody
	public List<SongList> getListsPerPage(@RequestParam("pageNum") int pageNum, @RequestParam("tag") String tag) {
		return songservice.getListsPerPage(pageNum, tag);
	}

	// 获取页数
	@RequestMapping("/getListPage")
	@ResponseBody
	public Page getPage(@RequestParam("tag") String tag) {
		return songservice.getPage(tag);
	}

	// 搜索一个歌单
	@GetMapping("/getSongListById")
	@ResponseBody
	public SongList getSongListById(@RequestParam("id") String lid) {
		return songservice.getSongListById(lid);
	}

	// 根据歌单搜索歌
	@RequestMapping("/getSongsByList")
	@ResponseBody
	public List<Song> getSongsByList(@RequestParam("pageNum") int pageNum, @RequestParam("id") String lid) {
		return songservice.getSongsByList(pageNum, lid);
	}

	// 获取歌单歌曲页数
	@RequestMapping("/getSBLPage")
	@ResponseBody
	public Page getSBLPage(String id) {
		return songservice.getSBLPage(id);
	}

	// 搜索歌
	@RequestMapping("/searchSong")
	@ResponseBody
	public List<Song> searchSong(@RequestParam("pageNum") int pageNum, @RequestParam("search") String search) {
		return songservice.searchSong(pageNum, search);
	}

	// 获取页数
	@RequestMapping("/getSSongPage")
	@ResponseBody
	public Page getSSongPage(String search) {
		return songservice.getSSongPage(search);
	}
}
