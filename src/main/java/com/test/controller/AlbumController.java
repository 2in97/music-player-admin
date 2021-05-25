package com.test.controller;

import java.text.SimpleDateFormat;
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
import com.test.enity.Song;
import com.test.service.AlbumService;
import com.test.service.SongService;

@RestController
@RequestMapping("/album")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class AlbumController {

	@Autowired
	AlbumService albumservice;
	@Autowired
	SongService songservice;

	// 更新
	@RequestMapping("updateAlbum")
	@ResponseBody
	public void updateAlbum(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("artist") String artist, @RequestParam("cover") String cover,
			@RequestParam("time") String time, @RequestParam("detail") String detail) {
		System.out.print(id);
		albumservice.updateAlbum(id, name, cover, artist, time, detail);
	}

	// 删除全部
	@GetMapping("/deleteAllAlbums")
	@ResponseBody
	public void deleteAllAlbums(@RequestParam("id") String id) {
		List<String> list = Arrays.asList(id.split(","));
		for (int i = 0; i < list.size(); i++)
			albumservice.deleteAlbum(list.get(i));
	}

	// 删除一个
	@GetMapping("/deleteAlbum")
	public void deleteAlbum(@RequestParam("id") String id) {
		songservice.deleteSongbyAlbum(id);
		albumservice.deleteAlbum(id);
	}

	// 分页查询
	@GetMapping("/getAlbumsPerPage")
	@ResponseBody
	public List<Album> getAlbumsPerPage(@RequestParam("pageNum") int pageNum, @RequestParam("search") String search) {
		return albumservice.getAlbumsPerPage(pageNum, search);
	}

	// 获取页数
	@RequestMapping("/getPage")
	@ResponseBody
	public Page getPage(String search) {
		return albumservice.getPage(search);
	}

	// 根据歌手搜索专辑
	@RequestMapping("/getAlbumsByArtist")
	@ResponseBody
	public List<Album> getAlbumsByArtist(@RequestParam("pageNum") int pageNum, @RequestParam("id") String id) {
		return albumservice.getAlbumsByArtist(id, pageNum);
	}

	// 获取歌手歌曲页数
	@RequestMapping("/getABAPage")
	@ResponseBody
	public Page getABAPage(String id) {
		return albumservice.getABAPage(id);
	}

	// 搜索一张专辑
	@GetMapping("/getAlbumById")
	@ResponseBody
	public Album getAlbumById(@RequestParam("id") String aid) {
		return albumservice.getAlbumById(aid);
	}
	
	// 搜索专辑
	@RequestMapping("/searchAlbum")
	@ResponseBody
	public List<Album> searchAlbum(@RequestParam("pageNum") int pageNum, @RequestParam("search") String search) {
		return albumservice.searchAlbum(pageNum, search);
	}

	// 获取页数
	@RequestMapping("/getSAlbumPage")
	@ResponseBody
	public Page getSAlbumPage(String search) {
		return albumservice.getSAlbumPage(search);
	}
}
