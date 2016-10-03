package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.model.SongMenuSecondTag;
import cn.xiedacon.read.dao.SongMenuReadTagDao;
import cn.xiedacon.read.service.SongMenuTagReadService;

@Service
public class SongMenuTagReadServiceImpl implements SongMenuTagReadService {

	@Autowired
	private SongMenuReadTagDao tagDao;

	@Override
	public List<SongMenuFirstTag> selectList() {
		return tagDao.selectFirstTagList();
	}

	@Override
	public SongMenuSecondTag selectSecondTagById(String id) {
		return tagDao.selectSecondTagById(id);
	}

}
