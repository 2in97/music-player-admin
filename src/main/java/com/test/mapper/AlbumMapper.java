package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Album;
import com.test.enity.Song;

public interface AlbumMapper {

	@Select("select * from album where  `name` like '%${search}%' order by release_time limit ${(pageNum-1)*10},10")
	List<Album> getAlbumsPerPage(int pageNum, String search);
	//获取总数
	@Select("select count(*) from album where  `name` like '%${search}%'")
	int getNumOfAlbums(String search);
	
	@Delete("delete from album where id=#{id}")
	void deleteAlbum(String id);
	
	@Update("UPDATE album SET `name` = #{name},cover=#{cover},artist=#{artist},release_time=#{time},detail=#{detail} WHERE id = #{id} ")
	void updateAlbum(int id, String name, String cover, String artist, String time, String detail);
	
	@Delete("delete from album where artist=(select `name` from artist where id=#{id})")
	void deleteAlbumbyArtist(String id);
	
	@Select("select album.* from artist,album where album.artist=artist.`name` and artist.id=#{id} limit ${(pageNum-1)*30},30")
	List<Album> getAlbumsByArtist(String id, int pageNum);
	
	@Select("select count(*) from artist,album where album.artist=artist.`name` and artist.id=#{id}")
	int getABANum(String id);
	
	@Select("select * from album where id=#{aid}")
	Album getAlbumById(String aid);
	
	@Select("SELECT * FROM album where album.name like '%${search}%' limit ${(pageNum-1)*30},30")
	List<Album> searchAlbum(String search, int pageNum);
	
	@Select("SELECT count(*) FROM album where album.name like '%${search}%' ")
	int getSAlbumPage(String search);
}
