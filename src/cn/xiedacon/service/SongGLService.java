package cn.xiedacon.service;

import java.util.Date;

import cn.xiedacon.model.SongMenu;

public interface SongGLService {

	String selectIdBySongIdAndSongMenuId(String songId, String songMenuId);

	void insertSongMenuGL(String randomUUID, String songId, SongMenu songMenu, Date time);

}
