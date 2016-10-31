package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.BatchSqlDao;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.write.dao.SongMenu_SongMenuTagGLDao;
import cn.xiedacon.write.dao.SongMenuWriteDao;
import cn.xiedacon.write.service.SongMenuWriteService;

@Service
public class SongMenuWriteServiceImpl implements SongMenuWriteService {

	@Autowired
	private SongMenuWriteDao songMenuDao;
	@Autowired
	@Qualifier("batchSqlDaoImpl")
	private BatchSqlDao batchDao;
	@Autowired
	private SongMenu_SongMenuTagGLDao songMenu_SongMenuTagGLDao;

	@Override
	public void insert(SongMenu songMenu) {
		songMenuDao.insertSongMenu_base(songMenu);
		songMenuDao.insertSongMenu_record(songMenu);
	}

	@Override
	public void delete(SongMenu songMenu) {
		songMenuDao.delete(songMenu);
	}

	@Override
	public void update(SongMenu songMenu) {
		songMenuDao.update(songMenu);
		songMenu_SongMenuTagGLDao.deleteBySongMenuId(songMenu.getId());
		batchDao.insertSongMenu_SongMenuTagGL(songMenu.getSongMenu_SongMenuTagGLList());
	}

	@Override
	public void updateIconById(String icon, String id) {
		songMenuDao.updateIconById(icon, id);
	}

	@Override
	public void updatePlayNumById(Integer playNum, String id) {
		songMenuDao.updatePlayNumById(playNum, id);
	}

	@Override
	public void updateCommentNumById(Integer commentNum, String id) {
		songMenuDao.updateCommentNumById(commentNum, id);
	}

}
