package cn.xiedacon.service;

import java.util.List;

import cn.xiedacon.vo.SimpleSingerVo;
import cn.xiedacon.vo.SingerVo;

public interface SingerService {

	List<SimpleSingerVo> selectListLimit(Integer begin, Integer limit);

	List<SimpleSingerVo> selectListOrderByCollectionNumLimit(Integer begin, Integer limit);

	List<SimpleSingerVo> selectListByClassifyIdOrderByCollectionNumLimit(Integer classifyId, Integer begin,
			Integer limit);

	SingerVo selectById(String id);

	String selectIntroductionById(String id);
}
