package cn.xiedacon.service;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;

public interface SongMenuGLService {

	String selectIdBySongMenuIdAndCollectorId(String songMenuId, String collectorId);

	void insert(String id, User dataUser, SongMenu songMenu);

}
