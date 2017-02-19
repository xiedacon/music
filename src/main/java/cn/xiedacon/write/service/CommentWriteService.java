package cn.xiedacon.write.service;

import cn.xiedacon.model.Comment;

public interface CommentWriteService {

	void insertSongMenuComment(Comment comment);

	void insertAlbumComment(Comment comment);

	void insertSongComment(Comment comment);

	void insertSongListComment(Comment comment);

	void updateSongMenuCommentAgreeNumById(Integer agreeNum, String id);

	void updateAlbumCommentAgreeNumById(Integer agreeNum, String id);

	void updateSongListCommentAgreeNumById(Integer agreeNum, String id);

	void updateSongCommentAgreeNumById(Integer agreeNum, String id);
}
