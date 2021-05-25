package com.test.service;

import java.util.List;

import com.test.enity.Artist;
import com.test.enity.Page;
import com.test.enity.Song;

public interface ArtistService {

	List<Artist> getArtistsPerPage(int pageNum, String search);

	Page getPage(String search);

	void deleteArtist(String sid);

	void updateArtist(int id, String name, String avatar, String language, String birthday, String detail);

	List<Artist> getArtistsPerPage30(int pageNum, String language, String initial);

	Page getPage30(String language, String initial);

	Artist getArtistById(int id);

	List<Artist> searchArtist(int pageNum, String search);

	Page getSArtistPage(String search);

}
