
package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.User_SongMenuGL;
import cn.xiedacon.write.dao.User_SongMenuGLDao;
import cn.xiedacon.write.dao.SongMenuWriteDao;
import cn.xiedacon.write.dao.UserWriteDao;
import cn.xiedacon.write.service.User_SongMenuGLService;

@Service
public class User_SongMenuGLServiceImpl implements User_SongMenuGLService {

	@Autowired
	private User_SongMenuGLDao user_SongMenuGLDao;
	@Autowired
	private SongMenuWriteDao songMenuDao;
	@Autowired
	private UserWriteDao userDao;

	@Override
	public User_SongMenuGL selectExistBySongMenuIdAndCollectorId(String songMenuId, String collectorId) {
		return user_SongMenuGLDao.selectExistBySongMenuIdAndCollectorId(songMenuId, collectorId);
	}

	@Override
	public void insert(User_SongMenuGL user_SongMenuGL) {
		user_SongMenuGLDao.insert(user_SongMenuGL);
		songMenuDao.updateCollectionNumById(user_SongMenuGL.getCollectionNum(), user_SongMenuGL.getSongMenuId());
		userDao.updateCollectSongMenuNumById(user_SongMenuGL.getSongMenuNum(), user_SongMenuGL.getUserId());
	}

}
