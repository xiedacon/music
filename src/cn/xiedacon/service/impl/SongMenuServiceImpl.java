package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuDao;
import cn.xiedacon.dao.SongMenuTagDao;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.SongMenuSecondTag;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.util.UUIDUtils;

@Service
@Transactional
public class SongMenuServiceImpl implements SongMenuService {

	@Autowired
	private SongMenuDao songMenuDao;
	@Autowired
	private SongMenuTagDao tagDao;

	@Override
	public SongMenu selectById(String id) {
		return songMenuDao.selectSongMenuById(id);
	}

	@Override
	public PageBean<SongMenu> selectPageBean(Integer page) {
		PageBean<SongMenu> pageBean = new PageBean<>();
		int limit = 10;
		int count = songMenuDao.selectCount();
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : totalPage + 1;

		int begin = limit * (page - 1);

		List<SongMenu> beans = songMenuDao.selectByLimit(begin, limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public PageBean<SongMenu> selectPageBeanOrderByCollectionNum(Integer page) {
		PageBean<SongMenu> pageBean = new PageBean<>();
		int limit = 10;
		int count = songMenuDao.selectCount();
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : totalPage + 1;

		int begin = limit * (page - 1);

		List<SongMenu> beans = songMenuDao.selectByLimitOrderByCollectionNum(begin, limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public PageBean<SongMenu> selectPageBeanBySecondTagId(String secondTagId, Integer page) {
		PageBean<SongMenu> pageBean = new PageBean<>();
		int limit = 10;
		int count = songMenuDao.selectCountBySecondTagId(secondTagId);
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : totalPage + 1;

		int begin = limit * (page - 1);

		List<SongMenu> beans = songMenuDao.selectBySecondTagIdLimit(secondTagId, begin, limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public PageBean<SongMenu> selectPageBeanBySecondTagIdOrderByCollectionNum(String secondTagId, Integer page) {
		PageBean<SongMenu> pageBean = new PageBean<>();
		int limit = 10;
		int count = songMenuDao.selectCountBySecondTagId(secondTagId);
		int totalPage = count / limit;
		totalPage = count % limit == 0 ? 0 : totalPage + 1;

		int begin = limit * (page - 1);

		List<SongMenu> beans = songMenuDao.selectBySecondTagIdOrderByCollectionNumLimit(secondTagId, begin, limit);

		pageBean.setPage(page);
		pageBean.setLimit(limit);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public List<SongMenu> selectListByCreatorId(String creatorId) {
		return songMenuDao.selectListByCreatorId(creatorId);
	}

	@Override
	public List<SongMenu> selectListByCollectorId(String collectorId) {
		return songMenuDao.selectListByCollectorId(collectorId);
	}

	@Override
	public void insert(SongMenu songMenu) {
		songMenuDao.insertSongMenu_base(songMenu);
		songMenuDao.insertSongMenu_record(songMenu);
	}

	@Override
	public void delete(SongMenu songMenu) {
		songMenuDao.delete(songMenu);
	}

	@Override
	public void update(SongMenu songMenu) {
		songMenuDao.update(songMenu);
		tagDao.deleteGLBySongMenuId(songMenu.getId());
		for (SongMenuSecondTag tag : songMenu.getSongMenuSecondTagList()) {
			tagDao.insertGL(UUIDUtils.randomUUID(), songMenu.getId(), tag.getId());
		}
	}

	@Override
	public void updateIconById(String icon, String songMenuId) {
		songMenuDao.updateIconById(icon, songMenuId);
	}

	@Override
	public void updatePlayNumById(Integer playNum, String songMenuId) {
		songMenuDao.updatePlayNumById(playNum, songMenuId);
	}

	@Override
	public void updateCommentNumById(Integer commentNum, String id) {
		songMenuDao.updateCommentNumById(commentNum, id);
	}

}
