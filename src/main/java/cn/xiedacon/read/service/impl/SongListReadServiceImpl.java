
package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.SongList;
import cn.xiedacon.read.dao.SongListReadDao;
import cn.xiedacon.read.service.SongListReadService;

@Service
public class SongListReadServiceImpl implements SongListReadService {

	@Autowired
	private SongListReadDao songListDao;

	@Override
	public SongList selectById(String id) {
		return songListDao.selectById(id);
	}

	@Override
	public List<SongList> selectList() {
		return songListDao.selectList();
	}

}
