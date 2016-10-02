package cn.xiedacon.read.service;

import java.util.List;

import cn.xiedacon.model.Singer;

public interface SingerReadService {

	List<Singer> selectListLimit(Integer begin, Integer limit);

	List<Singer> selectListOrderByCollectionNumLimit(Integer begin, Integer limit);

	List<Singer> selectListByClassifyIdOrderByCollectionNumLimit(Integer classifyId, Integer begin, Integer limit);

	Singer selectById(String id);

	String selectIntroductionById(String id);
}
