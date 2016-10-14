package cn.xiedacon.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.AlbumAdminDao;
import cn.xiedacon.admin.dao.Album_SongGLDao;
import cn.xiedacon.admin.dao.BatchSqlDao;
import cn.xiedacon.admin.dao.SongAdminDao;
import cn.xiedacon.admin.service.SongAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.Album_SongGL;
import cn.xiedacon.model.Song;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.util.UUIDUtils;

@Service
public class SongAdminServiceImpl implements SongAdminService {

	@Autowired
	private SongAdminDao songDao;
	@Autowired
	@Qualifier("batchSqlDaoImpl")
	private BatchSqlDao batchDao;
	@Autowired
	private AlbumAdminDao albumDao;
	@Autowired
	private Album_SongGLDao album_SongGlDao;
	@Autowired
	private Factory factory;

	@Override
	public PageBean<Song> selectPageBean(Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songDao.selectCount();
		List<Song> beans = songDao.selectListLimit((page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public Song selectExist(String id) {
		return songDao.selectExist(id);
	}

	@Override
	public void delete(Song song) {
		songDao.delete(song);
	}

	@Override
	public PageBean<Song> selectPageBeanByNameLike(Integer page, String name) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = songDao.selectCountByNameLike(name);
		List<Song> beans = songDao.selectListByNameLikeLimit(name, (page - 1) * limit, limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public Song selectById(String id) {
		return songDao.selectById(id);
	}

	@Override
	public void update(Song song) {
		songDao.update(song);
	}

	@Override
	public void insert(Song song) {
		songDao.insertSong_base(song);
		songDao.insertSong_record(song);
		albumDao.updateSongNumByIdAndAddNum(song.getAlbumId(), 1);

		Album_SongGL album_SongGL = factory.get(Album_SongGL.class);
		album_SongGL.setId(UUIDUtils.randomUUID());
		album_SongGL.setAlbumId(song.getAlbumId());
		album_SongGL.setSongId(song.getId());
		album_SongGlDao.insert(album_SongGL);
	}

	@Override
	public void batchInsert(List<Song> songList) {
		Map<String, Integer> albumMap = new HashMap<>();
		List<Album_SongGL> album_SongGlList = new ArrayList<>();
		for (Song song : songList) {
			String albumId = song.getAlbumId();
			if (albumId != null) {
				Integer num = albumMap.get(albumId);
				num = num == null ? 1 : ++num;
				albumMap.put(albumId, num);
				Album_SongGL album_SongGL = factory.get(Album_SongGL.class);
				album_SongGL.setId(UUIDUtils.randomUUID());
				album_SongGL.setAlbumId(albumId);
				album_SongGL.setSongId(song.getId());
				album_SongGlList.add(album_SongGL);
			}
		}

		batchDao.insertSong(songList);
		batchDao.updateAlbum(albumMap);
		batchDao.insertAlbum_SongGL(album_SongGlList);
	}

}
