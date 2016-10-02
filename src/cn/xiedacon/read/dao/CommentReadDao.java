package cn.xiedacon.read.dao;

import java.util.List;

import cn.xiedacon.model.Comment;

public interface CommentReadDao {

	List<Comment> selectListBySongMenuIdAndMINAgreeNum(String songMenuId, Integer agreeNum);

	int selectCountBySongMenuId(String songMenuId);

	List<Comment> selectListBySongMenuIdLimit(String songMenuId, Integer begin, Integer limit);

	List<Comment> selectListByAlbumIdAndMINAgreeNum(String albumId, Integer agreeNum);

	int selectCountByAlbumId(String albumId);

	List<Comment> selectListByAlbumIdLimit(String albumId, Integer begin, Integer limit);

	List<Comment> selectListBySongListIdAndMINAgreeNum(String songListId, Integer cOMMENT_HOT_AGREENUM);

	int selectCountBySongListId(String songListId);

	List<Comment> selectListBySongListIdLimit(String songListId, Integer begin, Integer limit);

	List<Comment> selectListBySongIdAndMINAgreeNum(String songId, Integer cOMMENT_HOT_AGREENUM);

	int selectCountBySongId(String songId);

	List<Comment> selectListBySongIdLimit(String songId, int i, int limit);

	Comment selectSongMenuCommentById(String commentId);

	Comment selectAlbumCommentById(String commentId);

	Comment selectSongListCommentById(String commentId);

	Comment selectSongCommentById(String commentId);

}
