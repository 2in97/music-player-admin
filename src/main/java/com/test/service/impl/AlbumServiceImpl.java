package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.Album;
import com.test.enity.Artist;
import com.test.enity.Page;

import com.test.mapper.AlbumMapper;
import com.test.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService{

	@Autowired
	AlbumMapper albummapper;
	Page page = new Page();
	@Override
	public List<Album> getAlbumsPerPage(int pageNum, String search) {
		// TODO Auto-generated method stub
		if(page.getRecordsNum()==-1)
			getPage(search);
		page.setPageNum(pageNum);
			return albummapper.getAlbumsPerPage(pageNum,search);
	}
	
	@Override
	public Page getPage(String search) {
		page.setRecordsNum(getNumOfAlbums(search));///////////////报错找着
		page.setPageTotal();
		return page;
	}

	private int getNumOfAlbums(String search) { 
		// TODO Auto-generated method stub
		return albummapper.getNumOfAlbums(search);
	}

	@Override
	public void deleteAlbum(String id) {
		// TODO Auto-generated method stub
		albummapper.deleteAlbum(id);
	}

	@Override
	public void updateAlbum(int id, String name, String cover, String artist, String time, String detail) {
		// TODO Auto-generated method stub
		albummapper.updateAlbum(id,  name,  cover,  artist,  time,  detail);
	}

	@Override
	public void deleteAlbumbyArtist(String id) {
		// TODO Auto-generated method stub
		albummapper.deleteAlbumbyArtist(id);
	}

	@Override
	public List<Album> getAlbumsByArtist(String id,int pageNum) {
		// TODO Auto-generated method stub
		return albummapper.getAlbumsByArtist(id,pageNum);
	}

	@Override
	public Page getABAPage(String id) {
		// TODO Auto-generated method stub
		page.setRecordsNum(albummapper.getABANum(id));///////////////报错找着
		page.setPageSize(30);
		page.setPageTotal();
		return page;
	};
	@Override
	public Album getAlbumById(String aid) {
		// TODO Auto-generated method stub
		return albummapper.getAlbumById(aid);
	}
	
	
	@Override
	public List<Album> searchAlbum(int pageNum, String search) {
		// TODO Auto-generated method stub
		return albummapper.searchAlbum(search,pageNum);
	}

	@Override
	public Page getSAlbumPage(String search) {
		// TODO Auto-generated method stub
		page.setRecordsNum(albummapper.getSAlbumPage(search));///////////////报错找着
		page.setPageSize(30);
		page.setPageTotal();
		return page;
	}
}
