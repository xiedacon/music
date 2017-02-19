package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.BatchSqlDao;
import cn.xiedacon.admin.dao.SongList_SongGLDao;
import cn.xiedacon.admin.service.SongList_SongGLService;
import cn.xiedacon.model.SongList_SongGL;

@Service
public class SongList_SongGLServiceImpl implements SongList_SongGLService {

	@Qualifier("batchSqlDaoImpl")
	@Autowired
	private BatchSqlDao batchSqlDao;
	@Autowired
	private SongList_SongGLDao songList_SongGLDao;

	@Override
	public void batchDeleteThenInsert(String id, List<SongList_SongGL> songList_SongGLList) {
		batchSqlDao.deleteSongList_SongGLBySongListId(id);
		batchSqlDao.insertSongList_SongGL(songList_SongGLList);
	}

	@Override
	public List<SongList_SongGL> selectListBySongListIdOrderByRank(String id) {
		return songList_SongGLDao.selectListBySongListIdOrderByRank(id);
	}

}
