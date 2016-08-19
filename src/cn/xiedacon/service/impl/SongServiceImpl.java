package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongDao;
import cn.xiedacon.service.SongService;
import cn.xiedacon.vo.SimpleSongVo;
import cn.xiedacon.vo.SongVo;

@Service
@Transactional
public class SongServiceImpl implements SongService {

	@Autowired
	private SongDao songDao;

	@Override
	public List<SimpleSongVo> selectListBySongMenuIdOrderByRank(String songMenuId) {
		return songDao.selectListBySongMenuIdOrderByRank(songMenuId);
	}

	@Override
	public List<SimpleSongVo> selectListByAlbumIdOrderByRank(String albumId) {
		return songDao.selectListByAlbumIdOrderByRank(albumId);
	}

	@Override
	public List<SimpleSongVo> selectListBySongListIdOrderByRank(String songListId) {
		return songDao.selectListBySongListIdOrderByRank(songListId);
	}

	@Override
	public SongVo selectById(String id) {
		return songDao.selectById(id);
	}

	@Override
	public String selectLyricUriById(String id) {
		return songDao.selectLyricUriById(id);
	}

	@Override
	public List<SongVo> selectListBySingerIdOrderByCollectionNumLimit(String singerId, Integer begin, Integer limit) {
		return songDao.selectListBySingerIdOrderByCollectionNumLimit(singerId, begin, limit);
	}

	@Override
	public String selectFileUriById(String songId) {
		return songDao.selectFileUriById(songId);
	}

}
