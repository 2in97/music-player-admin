package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.enity.Album;
import com.test.enity.Artist;
import com.test.enity.Page;
import com.test.enity.Song;
import com.test.enity.SongList;
import com.test.enity.User;

public interface UserService {
	boolean getVerification(String tel);

	String login(String tel, String code) throws Exception;

	String logout(HttpServletRequest request);

	List<Song> getLastedSongs();

	List<Artist> getHotArtists();

	User getInfo(String tel);

	void updateInfo(String tel, String username, String avatar, String introduction, String sex, String age);

	boolean songIsMark(String sid, String tel);

	void addToFavoSong(String sid, String tel);

	void deleteFromFavoSong(String sid, String tel);

	boolean albumIsMark(String aid, String tel);

	void addToFavoAlbum(String aid, String tel);

	void deleteFromFavoAlbum(String aid, String tel);

	List<Song> getMyFavoSong(int pageNum, String tel);

	List<Album> getMyFavoAlbum(int pageNum, String tel);

	List<SongList> getMyFavoList(int pageNum, String tel);

	Page getMFSPage(String tel);

	Page getMFAPage(String tel);

	Page getMFLPage(String tel);

	boolean listIsMark(String lid, String tel);

	void addToFavoList(String lid, String tel);

	void deleteFromFavoList(String lid, String tel);

	List<SongList> getHotSongLists();

	String getAvatar(String tel);

}
