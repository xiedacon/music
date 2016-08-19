package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.xiedacon.vo.AlbumVo;
import cn.xiedacon.vo.SimpleAlbumVo;

@Repository
public interface AlbumDao {

	List<SimpleAlbumVo> selectAblumListByIds(@Param("albumIds") String[] albumIds);

	AlbumVo selectById(String id);

	int selectCountByTagId(@Param("tagId") String tagId);

	List<SimpleAlbumVo> selectListByTagIdOrderByCreateTimeLimit(@Param("tagId") String tagId, @Param("begin") int begin,
			@Param("limit") int limit);

	int selectCountBySingerId(String singerId);

	List<SimpleAlbumVo> selectListBySingerIdOrderByCreateTimeLimit(@Param("singerId") String singerId,
			@Param("begin") int begin, @Param("limit") int limit);

}
