
package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;
import cn.xiedacon.model.User_SongMenuGL;
import cn.xiedacon.util.UUIDUtils;
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
	public String selectIdBySongMenuIdAndCollectorId(String songMenuId, String collectorId) {
		return user_SongMenuGLDao.selectIdBySongMenuIdAndCollectorId(songMenuId, collectorId);
	}

	@Override
	public void insert(User dataUser, SongMenu songMenu) {
		songMenu.setCollectionNum(songMenu.getCollectionNum() + 1);
		dataUser.setCollectSongMenuNum(dataUser.getCollectSongMenuNum() + 1);
		user_SongMenuGLDao.insert(new User_SongMenuGL(UUIDUtils.randomUUID(), dataUser.getId(), songMenu.getId()));
		songMenuDao.updateCollectionNumById(songMenu.getCollectionNum(), songMenu.getId());
		userDao.updateCollectSongMenuNumById(dataUser.getCollectSongMenuNum(), dataUser.getId());
	}

}
