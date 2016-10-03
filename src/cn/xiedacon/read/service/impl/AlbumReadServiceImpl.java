package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.Album;
import cn.xiedacon.read.dao.AlbumReadDao;
import cn.xiedacon.read.service.AlbumReadService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class AlbumReadServiceImpl implements AlbumReadService {

	@Autowired
	private AlbumReadDao albumDao;

	@Override
	public Album selectById(String id) {
		return albumDao.selectById(id);
	}

	@Override
	public PageBean<Album> selectPageBeanByTagIdOrderByCreateTimeLimit(String tagId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = albumDao.selectCountByTagId(tagId);
		List<Album> beans = albumDao.selectListByTagIdOrderByCreateTimeLimit(tagId, limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public PageBean<Album> selectPageBeanBySingerIdOrderByCreateTimeLimit(String singerId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = albumDao.selectCountBySingerId(singerId);
		List<Album> beans = albumDao.selectListBySingerIdOrderByCreateTimeLimit(singerId, limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

}
