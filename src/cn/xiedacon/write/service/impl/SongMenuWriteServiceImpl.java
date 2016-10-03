package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.SongMenuSecondTag;
import cn.xiedacon.model.SongMenu_SongMenuTagGL;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.write.dao.SongMenu_SongMenuTagGLDao;
import cn.xiedacon.write.dao.SongMenuWriteDao;
import cn.xiedacon.write.service.SongMenuWriteService;

@Service
public class SongMenuWriteServiceImpl implements SongMenuWriteService {

	@Autowired
	private SongMenuWriteDao songMenuDao;
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
		for (SongMenuSecondTag tag : songMenu.getSongMenuSecondTagList()) {
			songMenu_SongMenuTagGLDao
					.insert(new SongMenu_SongMenuTagGL(UUIDUtils.randomUUID(), songMenu.getId(), tag.getId()));
		}
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
