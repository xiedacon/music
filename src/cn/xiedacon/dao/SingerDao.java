package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Singer;

public interface SingerDao {

	List<Singer> selectListByClassifyIdOrderByCollectionNumLimit(@Param("classifyId") Integer classifyId,
			@Param("begin") Integer begin, @Param("limit") Integer limit);

	List<Singer> selectListOrderByCollectionNumLimit(@Param("begin") Integer begin,
			@Param("limit") Integer limit);

	List<Singer> selectListLimit(@Param("begin") Integer begin, @Param("limit") Integer limit);

	Singer selectById(String id);

	String selectIntroductionById(String id);

}
