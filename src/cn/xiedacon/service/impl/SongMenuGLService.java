package cn.xiedacon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuDao;
import cn.xiedacon.dao.SongMenuGLDao;
import cn.xiedacon.dao.UserDao;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;

@Service
@Transactional
public class SongMenuGLService implements cn.xiedacon.service.SongMenuGLService {

	@Autowired
	private SongMenuGLDao songMenuGLDao;
	@Autowired
	private SongMenuDao songMenuDao;
	@Autowired
	private UserDao userDao;

	@Override
	public String selectIdBySongMenuIdAndCollectorId(String songMenuId, String collectorId) {
		return songMenuGLDao.selectIdBySongMenuIdAndCollectorId(songMenuId, collectorId);
	}

	@Override
	public void insert(String id, User dataUser, SongMenu songMenu) {
		songMenu.setCollectionNum(songMenu.getCollectionNum() + 1);
		dataUser.setCollectSongMenuNum(dataUser.getCollectSongMenuNum() + 1);
		songMenuGLDao.insert(id, dataUser.getId(), songMenu.getId());
		songMenuDao.updateCollectionNumById(songMenu.getCollectionNum(), songMenu.getId());
		userDao.updateCollectSongMenuNumById(dataUser.getCollectSongMenuNum(), dataUser.getId());
	}

}
