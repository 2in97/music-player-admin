package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Album;
import com.test.enity.Artist;
import com.test.enity.Recommend;
import com.test.enity.Song;
import com.test.enity.SongList;
import com.test.enity.User;

public interface UserMapper {
	//管理系统检查用户
	@Select("SELECT * FROM `user` WHERE username='${username}' and password=${password}")
	User selectUser(@Param("username")String username,@Param("password") String password);

	// 判断用户是否重复(前台)
	@Select("SELECT * FROM `user` WHERE tel=#{tel}")
	User selectOneUser(@Param("tel") String tel);
	
	//创建新号码
	@Insert("insert into user(`username`,tel,age) values(#{tel}, #{tel},0)")
	void createNewUser(String tel);
	/*
	 * // 注册用户
	 * 
	 * @Insert("insert into user value(null,#{userName},#{password},#{regAge},#{regSex},#{regEmail},null,0,#{regTime},#{loginTime})"
	 * ) void insertUser(String userName, String password, int regAge, String
	 * regSex, String regEmail, String regTime, String loginTime);

	 * // 更改密码
	 * 
	 * @Update("update `user` SET `password`=#{password} WHERE id=#{id}") void
	 * updatePassword(@Param("password") String password, @Param("id") int id);
	 * 
	 * // 更改头像
	 * 
	 * @Update("update `user` SET `regPhoto`=#{regPhoto} WHERE id=#{id}") void
	 * updateregPhoto(@Param("regPhoto") String regPhoto, @Param("id") int id);
	 * 
	 * @Select("SELECT * FROM `user` WHERE id=#{id}") User
	 * selectOneByid(@Param("id") int id);
	 * 
	 * // 个人设置
	 * 
	 * @Update("update `user` SET `userName`=#{userName},regSex=#{regSex},regAge=#{regAge},regEmail=#{regEmail} WHERE id=#{id}"
	 * ) void updateUser(@Param("userName") String userName, @Param("regSex") String
	 * regSex, @Param("regAge") int regAge,
	 * 
	 * @Param("regEmail") String regEmail, @Param("id") int id);
	 * 
	 * // 管理员查看用户
	 * 
	 * @Select("SELECT * FROM user WHERE admin=0") List<User> selectAllUser();
	 * 
	 * @Select("SELECT * FROM user") List<User> selectAll();
	 * 
	 * // 管理员删除用户
	 * 
	 * @Delete("DELETE FROM `user` WHERE id=#{id}") void deleteUser(@Param("id") int
	 * id);
	 */
	@Select("SELECT * FROM Recommend")
	List<Recommend> getAllRecommends();

	@Insert("insert into Recommend(url,remark) values(#{url},#{remark})")
	void addRecommend(String url, String remark);

	@Delete("delete from Recommend where id=#{id}")
	void deleteRecommend(String id);

	@Select("SELECT `u-l`.* FROM `u-l`  ORDER BY click DESC limit 0,8")
	List<SongList> getHotSongLists();
	
	@Select("SELECT song.* FROM song,album WHERE album.id=song.album ORDER BY release_time DESC limit 0,8")
	List<Song> getLastedSongs();
	
	@Select("SELECT artist.* FROM artist ORDER BY click DESC limit 0,8")
	List<Artist> getHotArtists();

	@Select("SELECT * FROM user where tel=#{tel}")
	User getInfo(String tel);

	@Update("update `user` SET username=#{username},avatar=#{avatar},introduction=#{introduction},sex=#{sex},age=${age} WHERE tel=#{tel}")
	void updateInfo(String tel, String username, String avatar, String introduction, String sex, String age);
	
//	@Update({"CREATE TABLE `myfavo_lists${tel}`  (\r\n" + 
//			"  `id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
//			"  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n" + 
//			"  `lid` int(11) NULL DEFAULT NULL,\r\n" + 
//			"  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n" + 
//			"  `tags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n" + 
//			"  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n" + 
//			"  PRIMARY KEY (`id`) USING BTREE\r\n" + 
//			") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;\r\n" })
//	void createNewTable(String tel);
//	
//
//	@Update({"CREATE TABLE `myfavo_songs${tel}`  (\r\n" + 
//			"  `id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
//			"  `sid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,\r\n" + 
//			"  PRIMARY KEY (`id`) USING BTREE\r\n" + 
//			") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;\r\n" + 
//			"" })
//	void createNewTable2(String tel);
//	
//	@Update({"CREATE TABLE `myfavo_artists${tel}`  (\r\n" + 
//			"  `id` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
//			"  `aid` int(11) NULL DEFAULT NULL,\r\n" + 
//			"  PRIMARY KEY (`id`) USING BTREE\r\n" + 
//			") ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;"})
//	void createNewTable3(String tel);
//	

	@Select("SELECT count(*) FROM myfavo_songs where sid=#{sid} and utel=#{tel}")
	int songIsMark(String sid, String tel);

	@Insert("insert into myfavo_songs(sid,utel) values(#{sid},#{tel})")
	void addToFavoSong(String sid, String tel);
	
	@Delete("delete from myfavo_songs where sid=#{sid} and utel=#{tel}")
	void deleteFromFavoSong(String sid, String tel);

	@Select("SELECT count(*) FROM myfavo_albums where aid=#{aid} and utel=#{tel}")
	int albumIsMark(String aid, String tel);

	@Insert("insert into myfavo_albums(aid,utel) values(#{aid},#{tel})")
	void addToFavoAlbum(String aid, String tel);
	
	@Delete("delete from myfavo_albums where aid=#{aid} and utel=#{tel}")
	void deleteFromFavoAlbum(String aid, String tel);

	@Select("SELECT song.* FROM myfavo_songs,song where myfavo_songs.sid=song.id and utel=#{tel} limit ${(pageNum-1)*30},30")
	List<Song> getMyFavoSong(int pageNum, String tel);

	@Select("SELECT album.* FROM myfavo_albums,album where myfavo_albums.aid=album.id and utel=#{tel} limit ${(pageNum-1)*15},15")
	List<Album> getMyFavoAlbum(int pageNum, String tel);

	@Select("SELECT `u-l`.* FROM myfavo_lists,`u-l` where myfavo_lists.lid=`u-l`.lid and myfavo_lists.utel=#{tel} limit ${(pageNum-1)*30},30")
	List<SongList> getMyFavoList(int pageNum, String tel);

	@Select("SELECT count(*) FROM myfavo_songs,song where myfavo_songs.sid=song.id and utel=#{tel}")
	int getMFSNum(String tel);

	@Select("SELECT count(*) FROM myfavo_albums,album where myfavo_albums.aid=album.id and utel=#{tel}")
	int getMFANum(String tel);

	@Select("SELECT count(*) FROM myfavo_lists,`u-l` where myfavo_lists.lid=`u-l`.lid and myfavo_lists.utel=#{tel}")
	int getMFLNum(String tel);

	@Select("SELECT count(*) FROM myfavo_lists where lid=#{lid} and utel=#{tel}")
	int listIsMark(String lid, String tel);

	@Insert("insert into myfavo_albums(lid,utel) values(#{lid},#{tel})")
	void addToFavoList(String lid, String tel);
	
	@Delete("delete from myfavo_albums where lid=#{lid} and utel=#{tel}")
	void deleteFromFavoList(String lid, String tel);

	@Select("SELECT avatar FROM user where tel=#{tel}")
	String getAvatar(String tel);


}
