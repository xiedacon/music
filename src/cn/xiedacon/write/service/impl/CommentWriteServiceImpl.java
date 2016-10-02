package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.xiedacon.model.Comment;
import cn.xiedacon.write.dao.CommentWriteDao;
import cn.xiedacon.write.service.CommentWriteService;

public class CommentWriteServiceImpl implements CommentWriteService {

	@Autowired
	private CommentWriteDao commentDao;

	@Override
	public void insertSongMenuComment(Comment comment) {
		commentDao.insertComment_songMenu(comment);
	}

	@Override
	public void insertAlbumComment(Comment comment) {
		commentDao.insertComment_album(comment);
	}

	@Override
	public void insertSongComment(Comment comment) {
		commentDao.insertComment_song(comment);
	}

	@Override
	public void insertSongListComment(Comment comment) {
		commentDao.insertComment_songlist(comment);
	}

	@Override
	public void updateSongMenuCommentAgreeNumById(Integer agreeNum, String id) {
		commentDao.updateSongMenuCommentAgreeNumById(agreeNum, id);
	}

	@Override
	public void updateAlbumCommentAgreeNumById(Integer agreeNum, String id) {
		commentDao.updateAlbumCommentAgreeNumById(agreeNum, id);
	}

	@Override
	public void updateSongListCommentAgreeNumById(Integer agreeNum, String id) {
		commentDao.updateSongListCommentAgreeNumById(agreeNum, id);
	}

	@Override
	public void updateSongCommentAgreeNumById(Integer agreeNum, String id) {
		commentDao.updateSongCommentAgreeNumById(agreeNum, id);
	}

}
