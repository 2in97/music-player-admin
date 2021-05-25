package com.test.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.test.enity.Album;
import com.test.enity.Page;
import com.test.enity.Song;
import com.test.enity.SongList;


public interface SongService {

	int getNumOfSongs();
	String isNewAlbum(String artist, String album);
	void createNewAlbum(String album, String artist, String time,String picAddress);
	boolean uploadSong(String songID,String name, String picAddress, String artist, String albumID, String songAddress,
			String detail, String lyric, String type);
	String generateID();
	List<Song> getSongsPerPage(int pageNum, String searchType, String search);
	Page getPage(String searchType, String search);
	void updateAlbum(String albumID, String type, String album);
	void updateSong(String sid, String name, String picAddress, String artist, String albumID, String detail, String type, String lyric);
	void deleteSong(String sid);
	Song getSongById(String sid);
	void insertLastListened(String sid);
	List<Song> getSongsLastListened(int pageNum, String searchType, String search);
	int getNumOfSongsByName(String search);
	int getNumOfSongsByArtist(String search);
	int getNumOfSongsByAlbum(String search);
	String isNewArtist(String artist);
	void addNewArtist(String artist, String picAddress);
	void deleteSongbyArtist(String id);
	void deleteSongbyAlbum(String id);
	String getLyric(String sid) throws IOException, URISyntaxException;
	List<Song> getSongsByArtist(String id, int pageNum);
	Page getSBAPage(String id);
	List<Song> getSongsByAlbum(String id);
	List<SongList> getMySongLists(String tel);
	String insertThisList(String lid, String sid);
	void createSongList(String name, String cover, String tag, String detail, String tel, String time, String lid);
	List<SongList> getListsPerPage(int pageNum, String tag);
	Page getPage(String tag);
	SongList getSongListById(String lid);
	List<Song> getSongsByList(int pageNum, String lid);
	Page getSBLPage(String id);
	List<Song> searchSong(int pageNum, String search);
	Page getSSongPage(String search);
}
