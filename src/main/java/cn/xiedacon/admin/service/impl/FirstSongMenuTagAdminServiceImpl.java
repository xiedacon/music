package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.FirstSongMenuTagAdminDao;
import cn.xiedacon.admin.service.FirstSongMenuTagAdminService;
import cn.xiedacon.model.SongMenuFirstTag;

@Service
public class FirstSongMenuTagAdminServiceImpl implements FirstSongMenuTagAdminService {

	@Autowired
	private FirstSongMenuTagAdminDao tagDao;

	@Override
	public List<SongMenuFirstTag> selectList() {
		return tagDao.selectList();
	}

	@Override
	public SongMenuFirstTag selectById(String id) {
		return tagDao.selectById(id);
	}

	@Override
	public void delete(SongMenuFirstTag tag) {
		tagDao.delete(tag);
	}

	@Override
	public void update(SongMenuFirstTag tag) {
		tagDao.update(tag);
	}

	@Override
	public void insert(SongMenuFirstTag tag) {
		tagDao.insert(tag);
	}

}
