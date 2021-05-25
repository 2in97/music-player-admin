package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.Artist;
import com.test.enity.Page;
import com.test.enity.Song;
import com.test.mapper.ArtistMapper;
import com.test.service.ArtistService;

@Service
public class ArtistServiceImpl implements ArtistService{

	@Autowired
	ArtistMapper artistmapper;
	Page page = new Page();
	@Override
	public List<Artist> getArtistsPerPage(int pageNum, String search) {
		// TODO Auto-generated method stub
		if(page.getRecordsNum()==-1)
			getPage(search);
		page.setPageNum(pageNum);
			return artistmapper.getArtistsPerPage(pageNum,search);
	}
	
	@Override
	public Page getPage(String search) {
		page.setRecordsNum(getNumOfArtists(search));///////////////报错找着
		page.setPageTotal();
		return page;
	}

	private int getNumOfArtists(String search) { 
		// TODO Auto-generated method stub
		return artistmapper.getNumOfArtists(search);
	}

	@Override
	public void deleteArtist(String id) {
		// TODO Auto-generated method stub
		artistmapper.deleteArtist(id);
	}

	@Override
	public void updateArtist(int id, String name, String avatar, String language, String birthday, String detail) {
		// TODO Auto-generated method stub
		artistmapper.updateArtist(id,  name,  avatar,  language,  birthday,  detail);
	}

	@Override
	public List<Artist> getArtistsPerPage30(int pageNum,String language, String initial) {
		// TODO Auto-generated method stub
		if(page.getRecordsNum()==-1)
			getPage30(language,initial);
		page.setPageNum(pageNum);
		if (language==""&&initial!="") {
			return artistmapper.getArtistsPerPage30i(pageNum,initial);
		}
		if (language!=""&&initial=="") {
			return artistmapper.getArtistsPerPage30l(pageNum,language);
		}
		if (language!=""&&initial!="") {
			return artistmapper.getArtistsPerPage30li(pageNum,language,initial);
		}else {
			return artistmapper.getArtistsPerPage30(pageNum);
		}
	};
	@Override
	public Page getPage30(String language, String initial) {
		if (language==""&&initial!="") {
			page.setRecordsNum(artistmapper.getNumOfArtistsByLanguage(language));
		}
		if (language!=""&&initial=="") {
			page.setRecordsNum(artistmapper.getNumOfArtistsByinitial(initial));
		}
		if (language!=""&&initial!="") {
			page.setRecordsNum(artistmapper.getNumOfArtistsByBoth(language,initial));
		}else {
			page.setRecordsNum(getNumOfArtists(""));///////////////报错找着
		}
		page.setPageSize(30);
		page.setPageTotal();
		return page;
	}

	@Override
	public Artist getArtistById(int id) {
		// TODO Auto-generated method stub
		int count=artistmapper.getClick(id);
		artistmapper.setClickOnce(id,++count);
		return artistmapper.getArtistById(id);
	}
	
	@Override
	public List<Artist> searchArtist(int pageNum, String search) {
		// TODO Auto-generated method stub
		return artistmapper.searchArtist(search,pageNum);
	}

	@Override
	public Page getSArtistPage(String search) {
		// TODO Auto-generated method stub
		page.setRecordsNum(artistmapper.getSArtistPage(search));///////////////报错找着
		page.setPageSize(30);
		page.setPageTotal();
		return page;
	}
}
