package cn.xiedacon.write.dao;

import org.apache.ibatis.annotations.Param;

import cn.xiedacon.model.Comment;

public interface CommentWriteDao {

	void insertComment_songMenu(Comment comment);

	void insertComment_album(Comment comment);

	void insertComment_song(Comment comment);

	void insertComment_songlist(Comment comment);

	void updateSongMenuCommentAgreeNumById(@Param("agreeNum") Integer agreeNum, @Param("id") String id);

	void updateAlbumCommentAgreeNumById(@Param("agreeNum") Integer agreeNum, @Param("id") String id);

	void updateSongListCommentAgreeNumById(@Param("agreeNum") Integer agreeNum, @Param("id") String id);

	void updateSongCommentAgreeNumById(@Param("agreeNum") Integer agreeNum, @Param("id") String id);

}
