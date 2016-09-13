package cn.xiedacon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Comment;

public interface CommentDao {

	Integer selectCountBySongMenuId(String songMenuId);

	Integer selectCountByAlbumId(String albumId);

	List<Comment> selectListByAlbumIdLimit(@Param("albumId") String albumId, @Param("begin") int begin,
			@Param("limit") int limit);

	List<Comment> selectListByAlbumIdAndMINAgreeNum(@Param("albumId") String albumId, @Param("agreeNum") int agreeNum);

	List<Comment> selectListBySongMenuIdLimit(@Param("songMenuId") String songMenuId, @Param("begin") int begin,
			@Param("limit") int limit);

	List<Comment> selectListBySongMenuIdAndMINAgreeNum(@Param("songMenuId") String songMenuId,
			@Param("agreeNum") int agreeNum);

	List<Comment> selectListBySongListIdAndMINAgreeNum(@Param("songListId") String songListId,
			@Param("agreeNum") int agreeNum);

	Integer selectCountBySongListId(String songListId);

	List<Comment> selectListBySongListIdLimit(@Param("songListId") String songListId, @Param("begin") int begin,
			@Param("limit") int limit);

	List<Comment> selectListBySongIdAndMINAgreeNum(@Param("songId") String songId, @Param("agreeNum") int agreeNum);

	int selectCountBySongId(String songId);

	List<Comment> selectListBySongIdLimit(@Param("songId") String songId, @Param("begin") int begin,
			@Param("limit") int limit);

	void insertComment_songMenu(Comment comment);

	void insertComment_album(Comment comment);

	void insertComment_song(Comment comment);

	void insertComment_songlist(Comment comment);

	Comment selectSongMenuCommentById(String id);

	void updateSongMenuCommentAgreeNumById(@Param("agreeNum") Integer agreeNum, @Param("id") String id);

	Comment selectAlbumCommentById(String id);

	void updateAlbumCommentAgreeNumById(@Param("agreeNum") Integer agreeNum, @Param("id") String id);

	Comment selectSongListCommentById(String id);

	void updateSongListCommentAgreeNumById(@Param("agreeNum") Integer agreeNum, @Param("id") String id);

	Comment selectSongCommentById(String id);

	void updateSongCommentAgreeNumById(@Param("agreeNum") Integer agreeNum, @Param("id") String id);

}
