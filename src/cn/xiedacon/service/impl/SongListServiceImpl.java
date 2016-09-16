package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongListDao;
import cn.xiedacon.model.SongList;
import cn.xiedacon.service.SongListService;

@Service
@Transactional
public class SongListServiceImpl implements SongListService {

	@Autowired
	private SongListDao songListDao;

	@Override
	public SongList selectById(String id) {
		return songListDao.selectById(id);
	}

	@Override
	public List<SongList> selectList() {
		return songListDao.selectList();
	}

	@Override
	public void updatePlayNumById(Integer playNum, String id) {
		songListDao.updatePlayNumById(playNum, id);
	}

	@Override
	public void updateCommentNumById(Integer commentNum, String id) {
		songListDao.updateCommentNumById(commentNum, id);
	}

}
