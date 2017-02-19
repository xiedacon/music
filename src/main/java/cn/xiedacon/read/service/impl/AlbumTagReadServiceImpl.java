package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.AlbumTag;
import cn.xiedacon.read.dao.AlbumTagReadDao;
import cn.xiedacon.read.service.AlbumTagReadService;

@Service
public class AlbumTagReadServiceImpl implements AlbumTagReadService {

	@Autowired
	private AlbumTagReadDao ablumTagDao;

	@Override
	public List<AlbumTag> selectList() {
		return ablumTagDao.selectList();
	}

}
