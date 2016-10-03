package cn.xiedacon.write.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.Song;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.SongMenu_SongGL;
import cn.xiedacon.util.UUIDUtils;
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
	public void insert(SongMenu songMenu, Song song) {
		songGLDao.insert(
				new SongMenu_SongGL(UUIDUtils.randomUUID(), songMenu.getId(), song.getId(), new Date()));
		songMenuDao.updateSongNumById(songMenu.getSongNum(), songMenu.getId());
	}

	@Override
	public String selectIdBySongIdAndSongMenuId(String songId, String songMenuId) {
		return songGLDao.selectIdBySongIdAndSongMenuId(songId, songMenuId);
	}

}
