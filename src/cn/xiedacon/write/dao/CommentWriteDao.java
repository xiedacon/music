package cn.xiedacon.write.dao;

import cn.xiedacon.model.Comment;

public interface CommentWriteDao {

	void insertComment_songMenu(Comment comment);

	void insertComment_album(Comment comment);

	void insertComment_song(Comment comment);

	void insertComment_songlist(Comment comment);

	void updateSongMenuCommentAgreeNumById(Integer agreeNum, String id);

	void updateAlbumCommentAgreeNumById(Integer agreeNum, String id);

	void updateSongListCommentAgreeNumById(Integer agreeNum, String id);

	void updateSongCommentAgreeNumById(Integer agreeNum, String id);

}
