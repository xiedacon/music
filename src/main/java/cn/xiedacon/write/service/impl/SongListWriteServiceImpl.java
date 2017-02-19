package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.write.dao.SongListWriteDao;
import cn.xiedacon.write.service.SongListWriteService;

@Service
public class SongListWriteServiceImpl implements SongListWriteService {

	@Autowired
	private SongListWriteDao songListDao;

	@Override
	public void updatePlayNumById(Integer playNum, String id) {
		songListDao.updatePlayNumById(playNum, id);
	}

	@Override
	public void updateCommentNumById(Integer commentNum, String id) {
		songListDao.updateCommentNumById(commentNum, id);
	}

}
