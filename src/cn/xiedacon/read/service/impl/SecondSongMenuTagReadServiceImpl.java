package cn.xiedacon.read.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.xiedacon.admin.dao.BatchSqlDao;
import cn.xiedacon.model.SongMenuSecondTag;
import cn.xiedacon.read.service.SecondSongMenuTagReadService;

@Service
public class SecondSongMenuTagReadServiceImpl implements SecondSongMenuTagReadService {

	@Qualifier("batchSqlDaoImpl")
	@Autowired
	private BatchSqlDao batchDao;

	@Override
	public Map<String, SongMenuSecondTag> batchSelectById(List<String> tagIdList) {
		return batchDao.selectSecondSongMenuTagById(tagIdList);
	}

}
