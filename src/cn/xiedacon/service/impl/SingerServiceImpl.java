package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.SingerDao;
import cn.xiedacon.service.SingerService;
import cn.xiedacon.vo.SimpleSingerVo;
import cn.xiedacon.vo.SingerVo;

@Service
@Transactional
public class SingerServiceImpl implements SingerService {

	@Autowired
	private SingerDao singerDao;
	
	@Override
	public List<SimpleSingerVo> selectListLimit(Integer begin, Integer limit) {
		return singerDao.selectListLimit(begin, limit);
	}

	@Override
	public List<SimpleSingerVo> selectListOrderByCollectionNumLimit(Integer begin, Integer limit) {
		return singerDao.selectListOrderByCollectionNumLimit(begin,limit);
	}

	@Override
	public List<SimpleSingerVo> selectListByClassifyIdOrderByCollectionNumLimit(Integer classifyId, Integer begin,
			Integer limit) {
		return singerDao.selectListByClassifyIdOrderByCollectionNumLimit(classifyId,begin,limit);
	}

	@Override
	public SingerVo selectById(String id) {
		return singerDao.selectById(id);
	}

	@Override
	public String selectIntroductionById(String id) {
		return singerDao.selectIntroductionById(id);
	}

}
