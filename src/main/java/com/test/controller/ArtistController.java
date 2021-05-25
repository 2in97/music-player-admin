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

import com.test.enity.Artist;
import com.test.enity.Page;
import com.test.enity.Song;
import com.test.service.AlbumService;
import com.test.service.ArtistService;
import com.test.service.SongService;


@RestController
@RequestMapping("/artist")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class ArtistController {

	@Autowired
	ArtistService artistservice;
	@Autowired
	SongService songservice;
	@Autowired
	AlbumService albumservice;

	// 更新歌手
	@RequestMapping("updateArtist")
	@ResponseBody
	public void updateArtist(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("avatar") String avatar, @RequestParam("language") String language,
			@RequestParam("birthday") String birthday,
			@RequestParam("detail") String detail) {
		System.out.print(id);
		artistservice.updateArtist(id,name,avatar,language,birthday,detail);
	}

	// 删除全部
	@GetMapping("/deleteAllArtists")
	@ResponseBody
	public void deleteAllArtists(@RequestParam("id") String id) {
		List<String> list = Arrays.asList(id.split(","));
		for (int i = 0; i < list.size(); i++)
			artistservice.deleteArtist(list.get(i));
	}

	// 删除一个
	@GetMapping("/deleteArtist")
	public void deleteArtist(@RequestParam("id") String id) {
		songservice.deleteSongbyArtist(id);
		albumservice.deleteAlbumbyArtist(id);
		artistservice.deleteArtist(id);
	}
	
	// 删除一个
		@GetMapping("/getArtistById")
		public Artist getArtistById(@RequestParam("id") int id) {
			return artistservice.getArtistById(id);
		}
		
	// 分页查询
	@GetMapping("/getArtistsPerPage")
	@ResponseBody
	public List<Artist> getArtistsPerPage(@RequestParam("pageNum") int pageNum,
			@RequestParam("search") String search) {
		return artistservice.getArtistsPerPage(pageNum, search);
	}

	// 分页查询
	@GetMapping("/getArtistsPerPage30")
	@ResponseBody
	public List<Artist> getArtistsPerPage30(@RequestParam("pageNum") int pageNum,@RequestParam("area") String language,@RequestParam("initial") String initial ) {
		return artistservice.getArtistsPerPage30(pageNum,language,initial);
	}
	
	// 获取页数
	@RequestMapping("/getPage")
	@ResponseBody
	public Page getPage(String search) {
		return artistservice.getPage(search);
	}
	// 搜索歌手
		@RequestMapping("/searchArtist")
		@ResponseBody
		public List<Artist> searchArtist(@RequestParam("pageNum") int pageNum, @RequestParam("search") String search) {
			return artistservice.searchArtist(pageNum, search);
		}

		// 获取页数
		@RequestMapping("/getSArtistPage")
		@ResponseBody
		public Page getSArtistPage(String search) {
			return artistservice.getSArtistPage(search);
		}

}
