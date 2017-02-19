package cn.xiedacon.write.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.Comment;
import cn.xiedacon.write.dao.AlbumWriteDao;
import cn.xiedacon.write.dao.CommentWriteDao;
import cn.xiedacon.write.dao.SongListWriteDao;
import cn.xiedacon.write.dao.SongMenuWriteDao;
import cn.xiedacon.write.dao.SongWriteDao;
import cn.xiedacon.write.service.CommentWriteService;

@Service
public class CommentWriteServiceImpl implements CommentWriteService {

	@Autowired
	private CommentWriteDao commentDao;
	@Autowired
	private SongMenuWriteDao songMenuDao;
	@Autowired
	private AlbumWriteDao albumDao;
	@Autowired
	private SongWriteDao songDao;
	@Autowired
	private SongListWriteDao songListDao;

	@Override
	public void insertSongMenuComment(Comment comment) {
		commentDao.insertComment_songMenu(comment);
		songMenuDao.updateCommentNumById(comment.getTotal(), comment.getSongMenuId());
	}

	@Override
	public void insertAlbumComment(Comment comment) {
		commentDao.insertComment_album(comment);
		albumDao.updateCommentNumById(comment.getTotal(), comment.getSongMenuId());
	}

	@Override
	public void insertSongComment(Comment comment) {
		commentDao.insertComment_song(comment);
		songDao.updateCommentNumById(comment.getTotal(), comment.getSongMenuId());
	}

	@Override
	public void insertSongListComment(Comment comment) {
		commentDao.insertComment_songlist(comment);
		songListDao.updateCommentNumById(comment.getTotal(), comment.getSongMenuId());
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
