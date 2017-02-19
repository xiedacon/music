package cn.xiedacon.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.SongListAdminDao;
import cn.xiedacon.admin.service.SongListAdminService;
import cn.xiedacon.model.SongList;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class SongListAdminServiceImpl implements SongListAdminService {

	@Autowired
	private SongListAdminDao songListDao;

	@Override
	public PageBean<SongList> selectPageBean(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songListDao.selectCount();
		List<SongList> beans = songListDao.selectListLimit((page - 1) * limit,limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public SongList selectExist(String id) {
		return songListDao.selectExist(id);
	}

	@Override
	public void delete(SongList songList) {
		songListDao.delete(songList);
	}

	@Override
	public PageBean<SongList> selectPageBeanByNameLike(Integer page, String nameLike) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songListDao.selectCountByNameLike(nameLike);
		List<SongList> beans = songListDao.selectListByNameLikeLimit(nameLike, (page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public SongList selectById(String id) {
		return songListDao.selectById(id);
	}

	@Override
	public void update(SongList songList) {
		songListDao.update(songList);
	}

	@Override
	public void insert(SongList songList) {
		songListDao.insertSonglist_base(songList);
		songListDao.insertSonglist_record(songList);
	}

}
