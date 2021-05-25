package com.test.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String lyricFolder;
    private String musicFolder;
    private String coverFolder;
    private String uploadFolder;
    private String previewPath;
    private String lyricPath;
    private String musicPath;
    private String coverPath;

    
    public String getLyricPath() {
		return lyricPath;
	}

	public void setLyricPath(String lyricPath) {
		this.lyricPath = lyricPath;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getLyricFolder() {
		return lyricFolder;
	}

	public void setLyricFolder(String lyricFolder) {
		this.lyricFolder = lyricFolder;
	}

	public String getMusicFolder() {
		return musicFolder;
	}

	public void setMusicFolder(String musicFolder) {
		this.musicFolder = musicFolder;
	}

	public String getCoverFolder() {
		return coverFolder;
	}

	public void setCoverFolder(String coverFolder) {
		this.coverFolder = coverFolder;
	}

	public String getUploadFolder() {
        return uploadFolder;
    }

    public void setUploadFolder(String uploadFolder) {
        this.uploadFolder = uploadFolder;
    }

    public String getPreviewPath() {
        return previewPath;
    }

    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
    }
}
