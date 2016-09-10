package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.AlbumDao;
import cn.xiedacon.model.Album;
import cn.xiedacon.service.AlbumService;
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
		PageBean<Album> pageBean = new PageBean<>();

		int limit = 10;
		int count = albumDao.selectCountByTagId(tagId);
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : ++totalPage;

		int begin = limit * (page - 1);

		List<Album> beans = albumDao.selectListByTagIdOrderByCreateTimeLimit(tagId, begin, limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public PageBean<Album> selectPageBeanBySingerIdOrderByCreateTime(String singerId, Integer page) {
		PageBean<Album> pageBean = new PageBean<>();

		int limit = 10;
		int count = albumDao.selectCountBySingerId(singerId);
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : ++totalPage;

		int begin = limit * (page - 1);

		List<Album> beans = albumDao.selectListBySingerIdOrderByCreateTimeLimit(singerId, begin, limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
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
