package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.write.dao.SongWriteDao;
import cn.xiedacon.write.service.SongWriteService;

@Service
public class SongWriteServiceImpl implements SongWriteService {

	@Autowired
	private SongWriteDao songDao;
	
	@Override
	public void updateCommentNumById(Integer commentNum, String id) {
		songDao.updateCommentNumById(commentNum,id);
	}

}
