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

}
