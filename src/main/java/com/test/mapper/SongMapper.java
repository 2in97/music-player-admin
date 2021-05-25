package com.test.mapper;

import java.net.URI;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Page;
import com.test.enity.Song;
import com.test.enity.SongList;

public interface SongMapper {
	//
	@Insert("insert into song(id,`name`,artist,album,pic,address,detail,typeID,lyric) values(#{songID}, #{name}, #{artist}, #{albumID}, #{picAddress}, #{songAddress},#{detail}, #{type},#{lyric})")
	boolean uploadSong(String songID,String name, String picAddress, String artist, String albumID, String songAddress, String detail, String lyric, String type);
	
	
	@Select("select count(*) from song")
	int getNumOfSongs();
	//
	@Select("select id from album where artist=#{artist} and `name`=#{album}")
	String isNewAlbum(String artist, String album);
	//
	@Insert("insert into album(`name`,artist,release_time,cover) values(#{album}, #{artist}, #{time}, #{picAddress})")
	void createNewAlbum(String album, String artist, String time, String picAddress);
	//
	@Select("select song.*,album.`name` as albumName,music_type.`name` as type  from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id  limit ${(pageNum-1)*10},10")
	List<Song> getSongsPerPage(int pageNum);
//
	@Select("select song.*,album.`name` as albumName,music_type.`name` as type  from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id WHERE song.`name` like '%${search}%' limit ${(pageNum-1)*10},10")
	List<Song> searchByName(int pageNum, String search);
//
	@Select("select song.*,album.`name` as albumName,music_type.`name` as type  from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id WHERE song.artist like '%${search}%' limit ${(pageNum-1)*10},10")
	List<Song> searchByArtist(int pageNum, String search);
//
	@Select("select song.*,album.`name` as albumName,music_type.`name` as type  from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id WHERE album.`name` like '%${search}%' limit ${(pageNum-1)*10},10")
	List<Song> searchByAlbum(int pageNum, String search);
//
	@Update("UPDATE album SET `name` = #{album},artist=#{artist} WHERE id = #{albumID}")
	void updateAlbum(String albumID, String album, String artist);
//
	@Update("UPDATE song SET `name` = #{name},artist=#{artist},album=#{albumID},pic=#{picAddress},detail=#{detail},typeID=#{type},lyric=#{lyric} WHERE id = #{id} ")
	void updateSong(String id, String name, String picAddress, String artist, String albumID, String detail, String type, String lyric);

	@Delete("delete from song where id=#{sid}")
	void deleteSong(String sid);
	//
	@Select("select song.*,album.`name` as albumName,music_type.`name` as type  from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id where song.id=#{sid}")
	Song getSongById(String sid);

	@Insert("insert into lastlistened(sid) values(#{sid})")
	void insertLastListened(String sid);

	@Select("select count(*) from lastListened")
	int getNumOfLast();
	
	@Delete("delete from lastListened limit 1")
	void deleteLastListened();
	
	@Delete("delete from lastListened where sid=#{sid}")
	void deleteLastBySid(String sid);

	@Select("select song.*,album.`name` as albumName,music_type.`name` as type  from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id right "
			+ "JOIN lastlistened on lastlistened.sid=song.id  ORDER BY lastlistened.id DESC limit ${(pageNum-1)*10},10")
	List<Song> getSongsLastListened(int pageNum);

	@Select("select count(*) from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id WHERE song.`name`  like '%${search}%'")
	int getNumOfSongsByName(String search);

	@Select("select count(*) from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id WHERE song.artist like '%${search}%'")
	int getNumOfSongsByArtist(String search);

	@Select("select count(*) from album right JOIN song on album.id=song.album LEFT JOIN music_type on song.typeID=music_type.id WHERE album.`name`  like '%${search}%'")
	int getNumOfSongsByAlbum(String search);

	@Select("select id from artist where `name`=#{artist}")
	String isNewArtist(String artist);

	@Insert("insert into artist(`name`,avatar,initial,click) values(#{artist}, #{picAddress},#{initial},0)")
	void addNewArtist(String artist, String picAddress, String initial);

	@Delete("delete from song where artist=(select `name` from artist where id=#{id})")
	void deleteSongbyArtist(String id);

	@Delete("delete from song where album=#{id}")
	void deleteSongbyAlbum(String id);

	@Select("select lyric from song where id=#{sid}")
	String getLyric(String sid);

	@Select("select song.*,album.`name` as albumName from song,artist,album where song.album=album.id and album.artist=artist.`name` and artist.id=#{id} limit ${(pageNum-1)*30},30")
	List<Song> getSongsByArtist(String id, int pageNum);

	@Select("select count(*) from song,artist,album where song.album=album.id and album.artist=artist.`name` and artist.id=#{id}")
	int getSBANum(String id);

	@Select("select song.*,album.`name` as albumName from song,artist,album where song.album=album.id and album.artist=artist.`name` and song.album=#{id}")
	List<Song> getSongsByAlbum(String id);

	@Select("select `u-l`.*,music_tags.`value` as tagName from `u-l`,music_tags where music_tags.id=`u-l`.tag and utel=#{tel}")
	List<SongList> getMySongLists(String tel);

	@Select("select count(*) from songlist where lid=#{lid} and sid=#{sid}")
	int isExist(String lid, String sid);
	
	@Insert("insert into songlist(lid,sid) values(#{lid}, #{sid})")
	void insertThisList(String lid, String sid);

	@Insert("insert into `u-l`(lname,cover,tag,detail,utel,time,lid,click) values(#{name}, #{cover}, #{tag}, #{detail}, #{tel}, #{time}, #{lid},0)")
	void createSongList(String name, String cover, String tag, String detail, String tel, String time, String lid);

	@Select("SELECT count(*) FROM `u-l` where `u-l`.tag like '%${tag}%'")
	int getPage(String tag);

	@Select("SELECT `u-l`.* FROM `u-l` where `u-l`.tag like '%${tag}%' limit ${(pageNum-1)*100},100")
	List<SongList> getListsPerPage(String tag, int pageNum);

	@Select("SELECT `u-l`.* FROM `u-l` where `u-l`.lid =#{lid}")
	SongList getSongListById(String lid);

	@Select("SELECT song.*,album.`name` as albumName FROM song,songlist,artist,album where song.album=album.id and album.artist=artist.`name` and song.id=songlist.sid and songlist.lid =#{lid} limit ${(pageNum-1)*50},50")
	List<Song> getSongsByList(String lid, int pageNum);

	@Select("SELECT count(*) FROM song,songlist where song.id=songlist.sid and songlist.lid =#{id}")
	int getSBLPage(String id);

	@Select("select click from `u-l` where lid=#{lid}")
	int getClick(String lid);

	@Update("UPDATE `u-l` SET click = #{count} WHERE lid = #{lid} ")
	void setClickOnce(String lid, int count);

	@Select("select song.*,album.`name` as albumName from song,artist,album where song.album=album.id and album.artist=artist.`name` and song.name like '%${search}%' limit ${(pageNum-1)*50},50")
	List<Song> searchSong(String search, int pageNum);

	@Select("SELECT count(*) FROM song where song.name like '%${search}%' ")
	int getSSongPage(String search);


}
