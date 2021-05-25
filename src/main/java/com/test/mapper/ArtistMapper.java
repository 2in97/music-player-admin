package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Artist;

public interface ArtistMapper {

	@Select("select * from artist where `name` like '%${search}%' limit ${(pageNum-1)*10},10")
	List<Artist> getArtistsPerPage(int pageNum, String search);
	//获取总数
	@Select("select count(*) from artist where `name` like '%${search}%'")
	int getNumOfArtists(String search);
	
	@Delete("delete from artist where id=#{id}")
	void deleteArtist(String id);
	
	@Update("UPDATE artist SET `name` = #{name},avatar=#{avatar},language=#{language},birthday=#{birthday},detail=#{detail} WHERE id = #{id} ")
	void updateArtist(int id, String name, String avatar, String language, String birthday, String detail);
	
	@Select("select * from artist limit ${(pageNum-1)*30},30")
	List<Artist> getArtistsPerPage30(int pageNum);
	
	@Select("select * from artist where language= #{language} limit ${(pageNum-1)*30},30")
	List<Artist> getArtistsPerPage30l(int pageNum, String language);
	
	@Select("select * from artist where initial= #{initial} limit ${(pageNum-1)*30},30")
	List<Artist> getArtistsPerPage30i(int pageNum, String initial);
	
	@Select("select * from artist where language= #{language} and initial= #{initial} limit ${(pageNum-1)*30},30")
	List<Artist> getArtistsPerPage30li(int pageNum, String language, String initial);
	
	@Select("select count(*) from artist where language= #{language}")
	int getNumOfArtistsByLanguage(String language);
	
	@Select("select count(*) from artist where initial= #{initial}")
	int getNumOfArtistsByinitial(String initial);
	
	@Select("select count(*) from artist where language= #{language} and initial= #{initial}")
	int getNumOfArtistsByBoth(String language, String initial);
	
	@Select("select * from artist where id= #{id}")
	Artist getArtistById(int id);
	
	@Update("UPDATE artist SET click = #{count} WHERE id = #{id} ")
	void setClickOnce(int id, int count);
	
	@Select("select click from artist where id= #{id}")
	int getClick(int id);
	
	@Select("SELECT * FROM artist where artist.name like '%${search}%' limit ${(pageNum-1)*30},30")
	List<Artist> searchArtist(String search, int pageNum);
	
	@Select("SELECT count(*) FROM artist where artist.name like '%${search}%' ")
	int getSArtistPage(String search);

}
