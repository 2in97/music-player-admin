package com.test.service;

import java.util.List;

import com.test.enity.Album;
import com.test.enity.Page;
import com.test.enity.Song;

public interface AlbumService {

	List<Album> getAlbumsPerPage(int pageNum, String search);

	Page getPage(String search);

	void deleteAlbum(String sid);

	void updateAlbum(int id, String name, String cover, String artist, String time, String detail);

	void deleteAlbumbyArtist(String id);

	List<Album> getAlbumsByArtist(String id, int pageNum);

	Page getABAPage(String id);

	Album getAlbumById(String aid);

	List<Album> searchAlbum(int pageNum, String search);

	Page getSAlbumPage(String search);

}
