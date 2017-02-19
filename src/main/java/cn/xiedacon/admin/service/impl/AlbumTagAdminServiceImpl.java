package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.AlbumTagAdminDao;
import cn.xiedacon.admin.service.AlbumTagAdminService;
import cn.xiedacon.model.AlbumTag;

@Service
public class AlbumTagAdminServiceImpl implements AlbumTagAdminService {

	@Autowired
	private AlbumTagAdminDao albumTagDao;

	@Override
	public List<AlbumTag> selectList() {
		return albumTagDao.selectList();
	}

	@Override
	public AlbumTag selectById(String id) {
		return albumTagDao.selectById(id);
	}

	@Override
	public void delete(AlbumTag classify) {
		albumTagDao.delete(classify);
	}

	@Override
	public void update(AlbumTag classify) {
		albumTagDao.update(classify);
	}

	@Override
	public void insert(AlbumTag classify) {
		albumTagDao.insert(classify);
	}

}
