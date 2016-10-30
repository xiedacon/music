package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.Song;
import cn.xiedacon.read.dao.SongReadDao;
import cn.xiedacon.read.service.SongReadService;

@Service
public class SongReadServiceImpl implements SongReadService {

	@Autowired
	private SongReadDao songDao;

	@Override
	public List<Song> selectListBySongMenuIdOrderByTime(String songMenuId) {
		return songDao.selectListBySongMenuIdOrderByTime(songMenuId);
	}

	@Override
	public List<Song> selectListByAlbumIdOrderByRank(String albumId) {
		return songDao.selectListByAlbumIdOrderByRank(albumId);
	}

	@Override
	public List<Song> selectListBySongListIdOrderByRank(String songListId) {
		return songDao.selectListBySongListIdOrderByRank(songListId);
	}

	@Override
	public Song selectById(String id) {
		return songDao.selectById(id);
	}

	@Override
	public String selectLyricUriById(String id) {
		return songDao.selectLyricUriById(id);
	}

	@Override
	public List<Song> selectListBySingerIdOrderByCollectionNumLimit(String singerId, Integer begin, Integer limit) {
		return songDao.selectListBySingerIdOrderByCollectionNumLimit(singerId, begin, limit);
	}

	@Override
	public String selectFileUriById(String songId) {
		return songDao.selectFileUriById(songId);
	}

}
