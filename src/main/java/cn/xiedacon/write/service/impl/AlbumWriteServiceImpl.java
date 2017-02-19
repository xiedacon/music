package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.write.dao.AlbumWriteDao;
import cn.xiedacon.write.service.AlbumWriteService;

@Service
public class AlbumWriteServiceImpl implements AlbumWriteService {

	@Autowired
	private AlbumWriteDao albumDao;

	@Override
	public void updatePlayNumById(Integer playNum, String id) {
		albumDao.updatePlayNumById(playNum, id);
	}

	@Override
	public void updateCommentNumById(Integer commentNum, String id) {
		albumDao.updateCommentNumById(commentNum, id);
	}

}
