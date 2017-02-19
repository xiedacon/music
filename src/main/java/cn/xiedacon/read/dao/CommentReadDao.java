package cn.xiedacon.read.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Comment;

public interface CommentReadDao {

	List<Comment> selectListBySongMenuIdAndMINAgreeNum(@Param("songMenuId") String songMenuId,
			@Param("agreeNum") Integer agreeNum);

	int selectCountBySongMenuId(String songMenuId);

	List<Comment> selectListBySongMenuIdLimit(@Param("songMenuId") String songMenuId, @Param("begin") Integer begin,
			@Param("limit") Integer limit);

	List<Comment> selectListByAlbumIdAndMINAgreeNum(@Param("albumId") String albumId,
			@Param("agreeNum") Integer agreeNum);

	int selectCountByAlbumId(String albumId);

	List<Comment> selectListByAlbumIdLimit(@Param("albumId") String albumId, @Param("begin") Integer begin,
			@Param("limit") Integer limit);

	List<Comment> selectListBySongListIdAndMINAgreeNum(@Param("songListId") String songListId,
			@Param("agreeNum") Integer agreeNum);

	int selectCountBySongListId(String songListId);

	List<Comment> selectListBySongListIdLimit(@Param("songListId") String songListId, @Param("begin") Integer begin,
			@Param("limit") Integer limit);

	List<Comment> selectListBySongIdAndMINAgreeNum(@Param("songId") String songId, @Param("agreeNum") Integer agreeNum);

	int selectCountBySongId(String songId);

	List<Comment> selectListBySongIdLimit(@Param("songId") String songId, @Param("begin") Integer begin,
			@Param("limit") Integer limit);

	Comment selectSongMenuCommentById(String commentId);

	Comment selectAlbumCommentById(String commentId);

	Comment selectSongListCommentById(String commentId);

	Comment selectSongCommentById(String commentId);

}
