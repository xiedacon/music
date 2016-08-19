package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongListDao;
import cn.xiedacon.dao.WebDao;
import cn.xiedacon.service.SongListService;
import cn.xiedacon.vo.SimpleSongListVo;
import cn.xiedacon.vo.SongListVo;

@Service
@Transactional
public class SongListServiceImpl implements SongListService {

	@Autowired
	private SongListDao songListDao;
	@Autowired
	private WebDao webDao;

	@Override
	public List<SimpleSongListVo> selectForIndex() {
		String songListIdsString = webDao.selectSongListIds();
		String[] songListIds = songListIdsString.split("\\|");

		return songListDao.selectSongListsByIds(songListIds);
	}

	@Override
	public SongListVo selectById(String id) {
		return songListDao.selectById(id);
	}

	@Override
	public List<SongListVo> selectList() {
		return songListDao.selectList();
	}

}
