package cn.xiedacon.read.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Singer;

public interface SingerReadDao {

	List<Singer> selectListLimit(@Param("begin") Integer begin, @Param("limit") Integer limit);

	List<Singer> selectListOrderByCollectionNumLimit(@Param("begin") Integer begin, @Param("limit") Integer limit);

	List<Singer> selectListByClassifyIdOrderByCollectionNumLimit(@Param("classifyId") Integer classifyId,
			@Param("begin") Integer begin, @Param("limit") Integer limit);

	Singer selectById(String id);

	String selectIntroductionById(String id);

}
