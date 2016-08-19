package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.vo.SimpleSingerVo;
import cn.xiedacon.vo.SingerVo;

public interface SingerDao {

	List<SimpleSingerVo> selectListByClassifyIdOrderByCollectionNumLimit(@Param("classifyId") Integer classifyId,
			@Param("begin") Integer begin, @Param("limit") Integer limit);

	List<SimpleSingerVo> selectListOrderByCollectionNumLimit(@Param("begin") Integer begin,
			@Param("limit") Integer limit);

	List<SimpleSingerVo> selectListLimit(@Param("begin") Integer begin, @Param("limit") Integer limit);

	SingerVo selectById(String id);

	String selectIntroductionById(String id);

}
