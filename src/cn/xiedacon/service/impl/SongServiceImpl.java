package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongDao;
import cn.xiedacon.model.Song;
import cn.xiedacon.service.SongService;

@Service
@Transactional
public class SongServiceImpl implements SongService {

	@Autowired
	private SongDao songDao;

	@Override
	public List<Song> selectListBySongMenuIdOrderByRank(String songMenuId) {
		return songDao.selectListBySongMenuIdOrderByRank(songMenuId);
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

	@Override
	public void updateCommentNumById(Integer commentNum, String id) {
		songDao.updateCommentNumById(commentNum,id);
	}

}
