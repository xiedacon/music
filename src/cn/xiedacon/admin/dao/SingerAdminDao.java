package cn.xiedacon.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Singer;

public interface SingerAdminDao {

	int selectCount();

	List<Singer> selectListLimit(@Param("begin") int begin, @Param("limit") int limit);

	Singer selectExist(String id);

	void delete(Singer singer);

	int selectCountByNameLike(String name);

	List<Singer> selectListByNameLikeLimit(@Param("name") String name, @Param("begin") int begin,
			@Param("limit") int limit);

	Singer selectById(String id);

	void update(Singer singer);

	void insert(Singer singer);

	Singer selectByName(String name);

}
