package cn.xiedacon.write.service;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;

public interface User_SongMenuGLService {

	String selectIdBySongMenuIdAndCollectorId(String songMenuId, String collectorId);

	void insert(User dataUser, SongMenu songMenu);

}
