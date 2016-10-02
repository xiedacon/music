package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.AlbumDao;
import cn.xiedacon.model.Album;
import cn.xiedacon.service.AlbumService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDao albumDao;

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
	public PageBean<Album> selectPageBeanBySingerIdOrderByCreateTime(String singerId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = albumDao.selectCountBySingerId(singerId);
		List<Album> beans = albumDao.selectListBySingerIdOrderByCreateTimeLimit(singerId, limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public void updatePlayNumById(Integer playNum, String id) {
		albumDao.updatePlayNumById(playNum, id);
	}

	@Override
	public void updateCommentNumById(Integer commentNum, String id) {
		albumDao.updateCommentNumById(commentNum, id);
	}

}
