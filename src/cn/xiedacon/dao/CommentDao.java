package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.vo.CommentVo;

public interface CommentDao {

	Integer selectCountBySongMenuId(String songMenuId);

	Integer selectCountByAlbumId(String albumId);

	List<CommentVo> selectListByAlbumId(@Param("albumId") String albumId, @Param("begin") int begin,
			@Param("limit") int limit);

	List<CommentVo> selectListByAlbumIdAndMINAgreeNum(@Param("albumId") String albumId,
			@Param("agreeNum") int agreeNum);

	List<CommentVo> selectListBySongMenuId(@Param("songMenuId") String songMenuId, @Param("begin") int begin,
			@Param("limit") int limit);

	List<CommentVo> selectListBySongMenuIdAndMINAgreeNum(@Param("songMenuId") String songMenuId,
			@Param("agreeNum") int agreeNum);

	List<CommentVo> selectListBySongListIdAndMINAgreeNum(@Param("songListId") String songListId,
			@Param("agreeNum") int agreeNum);

	Integer selectCountBySongListId(String songListId);

	List<CommentVo> selectListBySongListId(@Param("songListId") String songListId, @Param("begin") int begin,
			@Param("limit") int limit);

	List<CommentVo> selectListBySongIdAndMINAgreeNum(@Param("songId") String songId, @Param("agreeNum") int agreeNum);

	int selectCountBySongId(String songId);

	List<CommentVo> selectListBySongId(@Param("songId") String songId, @Param("begin") int begin,
			@Param("limit") int limit);

}
