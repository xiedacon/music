package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.SecondSongMenuTagAdminDao;
import cn.xiedacon.admin.service.SecondSongMenuTagAdminService;
import cn.xiedacon.model.SongMenuSecondTag;

@Service
public class SecondSongMenuTagAdminServiceImpl implements SecondSongMenuTagAdminService {

	@Autowired
	private SecondSongMenuTagAdminDao tagDao;

	@Override
	public List<SongMenuSecondTag> selectList() {
		return tagDao.selectList();
	}

	@Override
	public SongMenuSecondTag selectById(String id) {
		return tagDao.selectById(id);
	}

	@Override
	public void delete(SongMenuSecondTag tag) {
		tagDao.delete(tag);
	}

	@Override
	public void update(SongMenuSecondTag tag) {
		tagDao.update(tag);
	}

	@Override
	public void insert(SongMenuSecondTag tag) {
		tagDao.insert(tag);
	}

}
