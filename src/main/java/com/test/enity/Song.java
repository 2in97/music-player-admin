package com.test.enity;

public class Song {
	private String id;
	private String name;
	private String artist;
	private String album;
	private String pic;
	private String lyric;
	private String detail;
	private String address;
	private String albumName;
	private String type;
	private String typeID;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getLyric() {
		return lyric;
	}
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getTypeID() {
		return typeID;
	}
	public void setTypeID(String typeID) {
		this.typeID = typeID;
	}
	public Song(String id, String name, String artist, String album, String pic, String lyric, String detail,
			String address, String albumName, String type, String typeID) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.pic = pic;
		this.lyric = lyric;
		this.detail = detail;
		this.address = address;
		this.albumName = albumName;
		this.type = type;
		this.typeID = typeID;
	}
	public Song() {
		super();
	}
	
}
