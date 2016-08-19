package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SongMenuTagDao;
import cn.xiedacon.vo.SongMenuTagVo;

@Service
@Transactional
public class SongMenuTagService implements cn.xiedacon.service.SongMenuTagService {

	@Autowired
	private SongMenuTagDao tagDao;
	
	@Override
	public List<SongMenuTagVo> selectAll() {
		return tagDao.selectAll();
	}

}
