package com.test.service.impl;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.net.URL;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.Album;
import com.test.enity.Page;
import com.test.enity.Song;
import com.test.enity.SongList;
import com.test.mapper.SongMapper;
import com.test.service.SongService;

import net.sourceforge.pinyin4j.PinyinHelper;

@Service
public class SongServiceImpl implements SongService {
	@Autowired
	SongMapper songmapper;
	
	Page page = new Page();
	@Override
	public int getNumOfSongs() {
		// TODO Auto-generated method stub
		return songmapper.getNumOfSongs();
	}

	@Override
	public int getNumOfSongsByName(String search) {
		// TODO Auto-generated method stub
		return songmapper.getNumOfSongsByName(search);
	}
	@Override
	public int getNumOfSongsByArtist(String search) {
		// TODO Auto-generated method stub
		return songmapper.getNumOfSongsByArtist(search);
	}
	@Override
	public int getNumOfSongsByAlbum(String search) {
		// TODO Auto-generated method stub
		return songmapper.getNumOfSongsByAlbum(search);
	}
	@Override
	public String isNewAlbum(String artist, String album) {
		// TODO Auto-generated method stub
		return songmapper.isNewAlbum(artist, album);
	}

	@Override
	public void createNewAlbum(String album, String artist, String time,String picAddress) {
		// TODO Auto-generated method stub
		songmapper.createNewAlbum(album, artist, time,picAddress);
	}

	@Override
	public boolean uploadSong(String songID,String name, String picAddress, String artist, String albumID, String songAddress,
			 String detail,String lyric,String type) {
		// TODO Auto-generated method stub
		return songmapper.uploadSong(songID,name, picAddress, artist, albumID, songAddress, detail,lyric,type);
	}

	@Override
	public String generateID() {
		String val = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字
 
            if ("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
                // int choice = 97; // 指定字符串为小写字母
                val += (char) (choice + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return System.currentTimeMillis()+val;
	}
	@Override
	public Page getPage(String searchType, String search) {
		if (searchType.equals("1")) {
			page.setRecordsNum(getNumOfSongsByName(search));
		}
		if (searchType.equals("2")) {
			page.setRecordsNum(getNumOfSongsByArtist(search));
		}
		if (searchType.equals("3")) {
			page.setRecordsNum(getNumOfSongsByAlbum(search));
		}else {
		page.setRecordsNum(getNumOfSongs());///////////////报错找着
		}
		page.setPageTotal();
		return page;
	};
	
	@Override
	public List<Song> getSongsPerPage(int pageNum, String searchType, String search) {
		// TODO Auto-generated method stub
		if(page.getRecordsNum()==-1)
			getPage(null,null);
		page.setPageNum(pageNum);
		if (searchType.equals("1")) {
			return songmapper.searchByName(pageNum, search);
		}
		if (searchType.equals("2")) {
			return songmapper.searchByArtist(pageNum, search);
		}
		if (searchType.equals("3")) {
			return songmapper.searchByAlbum(pageNum, search);
		}
		else {
			return songmapper.getSongsPerPage(pageNum);
		}
	}

	@Override
	public void updateAlbum(String albumID, String album, String artist) {
		// TODO Auto-generated method stub
		songmapper.updateAlbum(albumID,album,artist);
	}

	@Override
	public void updateSong(String sid, String name, String picAddress, String artist, String albumID, String detail, String type,String lyric) {
		// TODO Auto-generated method stub
		songmapper.updateSong(sid, name, picAddress,  artist,  albumID,  detail,type,lyric);
	}

	@Override
	public void deleteSong(String sid) {
		// TODO Auto-generated method stub
		songmapper.deleteSong(sid);
	}

	@Override
	public Song getSongById(String sid) {
		// TODO Auto-generated method stub
		return songmapper.getSongById(sid);
	}

	@Override
	public void insertLastListened(String sid) {
		// TODO Auto-generated method stub
		songmapper.deleteLastBySid(sid);
		if(songmapper.getNumOfLast()>=50) {
			songmapper.deleteLastListened();
		}
		songmapper.insertLastListened(sid);
	}

	@Override
	public List<Song> getSongsLastListened(int pageNum, String searchType, String search) {
		// TODO Auto-generated method stub
		if(page.getRecordsNum()==-1)
			getPage(null,null);
		page.setPageNum(pageNum);
		return songmapper.getSongsLastListened(pageNum);
	}

	@Override
	public String isNewArtist(String artist) {
		// TODO Auto-generated method stub
		return songmapper.isNewArtist(artist);
	}

	@Override
	public void addNewArtist(String artist, String picAddress) {
		// TODO Auto-generated method stub
		String initial=getPinyinInitials(artist);
		songmapper.addNewArtist(artist,picAddress,initial);
	}

	/**
     * 获取汉字首字母
     *
     * @param text 文本
     * @return {@link String}
     */
    public static String getPinyinInitials(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String[] s = PinyinHelper.toHanyuPinyinStringArray(ch);
            if (s != null) {
                sb.append(s[0].charAt(0));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
	
	@Override
	public void deleteSongbyArtist(String id) {
		// TODO Auto-generated method stub
		songmapper.deleteSongbyArtist(id);

	}

	@Override
	public void deleteSongbyAlbum(String id) {
		// TODO Auto-generated method stub
		songmapper.deleteSongbyAlbum(id);
	}

	@Override
	public String getLyric(String sid) throws  IOException {
		// TODO Auto-generated method stub
		String file=songmapper.getLyric(sid);
		URL url = new URL(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"GBK"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line + '\n');
        }
        reader.close();
		return sb.toString();
	}

	@Override
	public List<Song> getSongsByArtist(String id,int pageNum) {
		// TODO Auto-generated method stub
		return songmapper.getSongsByArtist(id,pageNum);
	}

	@Override
	public Page getSBAPage(String id) {
		// TODO Auto-generated method stub
		page.setRecordsNum(songmapper.getSBANum(id));///////////////报错找着
		page.setPageSize(30);
		page.setPageTotal();
		return page;
	}

	@Override
	public List<Song> getSongsByAlbum(String id) {
		// TODO Auto-generated method stub
		return songmapper.getSongsByAlbum(id);
	}

	@Override
	public List<SongList> getMySongLists(String tel) {
		// TODO Auto-generated method stub
		return songmapper.getMySongLists(tel);
	}

	@Override
	public String insertThisList(String lid, String sid) {
		// TODO Auto-generated method stub
		if(songmapper.isExist(lid,sid)==1) {
			return "歌曲已存在";
		}
		songmapper.insertThisList(lid,sid);
		return "保存成功";
	}


	@Override
	public void createSongList(String name, String cover, String tag, String detail, String tel, String time,
			String lid) {
		// TODO Auto-generated method stub
		songmapper.createSongList(name,cover,tag,detail,tel,time,lid);
	}

	@Override
	public List<SongList> getListsPerPage(int pageNum, String tag) {
		// TODO Auto-generated method stub
		return songmapper.getListsPerPage(tag,pageNum);
	}

	@Override
	public Page getPage(String tag) {
		// TODO Auto-generated method stub
		page.setRecordsNum(songmapper.getPage(tag));///////////////报错找着
		page.setPageSize(100);
		page.setPageTotal();
		return page;
	}

	@Override
	public SongList getSongListById(String lid) {
		// TODO Auto-generated method stub
		int count=songmapper.getClick(lid);
		songmapper.setClickOnce(lid,++count);
		return songmapper.getSongListById(lid);
	}

	@Override
	public List<Song> getSongsByList(int pageNum, String lid) {
		// TODO Auto-generated method stub
		return songmapper.getSongsByList(lid,pageNum);
	}

	@Override
	public Page getSBLPage(String id) {
		// TODO Auto-generated method stub
		page.setRecordsNum(songmapper.getSBLPage(id));///////////////报错找着
		page.setPageSize(50);
		page.setPageTotal();
		return page;
	}
	
	@Override
	public List<Song> searchSong(int pageNum, String search) {
		// TODO Auto-generated method stub
		return songmapper.searchSong(search,pageNum);
	}

	@Override
	public Page getSSongPage(String search) {
		// TODO Auto-generated method stub
		page.setRecordsNum(songmapper.getSSongPage(search));///////////////报错找着
		page.setPageSize(50);
		page.setPageTotal();
		return page;
	}
}
