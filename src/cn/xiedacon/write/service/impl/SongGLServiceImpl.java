package cn.xiedacon.write.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongGLDao;
import cn.xiedacon.dao.SongMenuDao;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.service.SongGLService;

@Service
@Transactional
public class SongGLServiceImpl implements SongGLService {

	@Autowired
	private SongGLDao songGLDao;
	@Autowired
	private SongMenuDao songMenuDao;

	@Override
	public void insertSongMenuGL(String id, String songId, SongMenu songMenu, Date time) {
		songGLDao.insertSongMenuGL(id, songId, songMenu.getId(), time);
		songMenuDao.updateSongNumById(songMenu.getSongNum(), songMenu.getId());
	}

	@Override
	public String selectIdBySongIdAndSongMenuId(String songId, String songMenuId) {
		return songGLDao.selectIdBySongIdAndSongMenuId(songId, songMenuId);
	}

}
