package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuTagDao;
import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.model.SongMenuSecondTag;

@Service
@Transactional
public class SongMenuTagService implements cn.xiedacon.service.SongMenuTagService {

	@Autowired
	private SongMenuTagDao tagDao;
	
	@Override
	public List<SongMenuFirstTag> selectList() {
		return tagDao.selectList();
	}

	@Override
	public SongMenuSecondTag selectSecondTagById(String id) {
		return tagDao.selectSecondTagById(id);
	}

}
