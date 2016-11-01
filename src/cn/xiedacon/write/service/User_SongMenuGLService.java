package cn.xiedacon.write.service;

import cn.xiedacon.model.User_SongMenuGL;

public interface User_SongMenuGLService {

	User_SongMenuGL selectExistBySongMenuIdAndCollectorId(String songMenuId, String collectorId);

	void insert(User_SongMenuGL user_SongMenuGL);

}
