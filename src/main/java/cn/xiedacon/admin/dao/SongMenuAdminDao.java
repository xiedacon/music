package cn.xiedacon.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.SongMenu;

public interface SongMenuAdminDao {

	int selectCount();

	List<SongMenu> selectOrderByCreateTimeLimit(@Param("begin") int begin, @Param("limit") int limit);

	void delete(SongMenu songMenu);

	SongMenu selectExist(String id);

	int selectCountByNameLike(String name);

	List<SongMenu> selectByNameLikeOrderByCreateTimeLimit(@Param("name") String name, @Param("begin") int begin,
			@Param("limit") int limit);

}
