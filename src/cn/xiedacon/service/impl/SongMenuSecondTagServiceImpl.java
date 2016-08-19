package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuSecondTagDao;
import cn.xiedacon.dao.WebDao;
import cn.xiedacon.service.SongMenuSecondTagService;
import cn.xiedacon.vo.SongMenuSecondTagVo;

@Service
@Transactional
public class SongMenuSecondTagServiceImpl implements SongMenuSecondTagService {

	@Autowired
	private SongMenuSecondTagDao songMenuSecondTagDao;
	@Autowired
	private WebDao webDao;

	@Override
	public List<SongMenuSecondTagVo> selectForIndex() {
		String songMenuSecondTagIdsString = webDao.selectSongMenuSecondTagIds();
		String[] songMenuSecondTagIds = songMenuSecondTagIdsString.split("\\|");

		return songMenuSecondTagDao.selectSongMenuSecondTagListByIds(songMenuSecondTagIds);
	}

	@Override
	public List<SongMenuSecondTagVo> selectAll() {
		return songMenuSecondTagDao.selectAll();
	}

}
