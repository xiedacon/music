package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.SongMenu_SongGL;
import cn.xiedacon.write.dao.SongMenu_SongGLDao;
import cn.xiedacon.write.dao.SongMenuWriteDao;
import cn.xiedacon.write.service.SongMenu_SongGLService;

@Service
public class SongMenu_SongGLServiceImpl implements SongMenu_SongGLService {

	@Autowired
	private SongMenu_SongGLDao songGLDao;
	@Autowired
	private SongMenuWriteDao songMenuDao;

	@Override
	public SongMenu_SongGL selectExistBySongIdAndSongMenuId(String songId, String songMenuId) {
		return songGLDao.selectExistBySongIdAndSongMenuId(songId, songMenuId);
	}

	@Override
	public void insert(SongMenu_SongGL songMenu_SongGL) {
		songGLDao.insert(songMenu_SongGL);
		songMenuDao.updateSongNumById(songMenu_SongGL.getSongNum(), songMenu_SongGL.getSongMenuId());
	}

}
