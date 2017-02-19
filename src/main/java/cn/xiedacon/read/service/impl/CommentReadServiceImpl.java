package cn.xiedacon.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xiedacon.model.Comment;
import cn.xiedacon.read.dao.CommentReadDao;
import cn.xiedacon.read.service.CommentReadService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Service
public class CommentReadServiceImpl implements CommentReadService {

	@Autowired
	private CommentReadDao commentDao;

	@Override
	public List<Comment> selectForHotBySongMenuId(String songMenuId) {
		return commentDao.selectListBySongMenuIdAndMINAgreeNum(songMenuId, Constant.COMMENT_HOT_AGREENUM);
	}

	@Override
	public PageBean<Comment> selectPageBeanBySongMenuId(String songMenuId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = commentDao.selectCountBySongMenuId(songMenuId);
		List<Comment> beans = commentDao.selectListBySongMenuIdLimit(songMenuId, limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public List<Comment> selectForHotByAlbumId(String albumId) {
		return commentDao.selectListByAlbumIdAndMINAgreeNum(albumId, Constant.COMMENT_HOT_AGREENUM);
	}

	@Override
	public PageBean<Comment> selectPageBeanByAlbumId(String albumId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = commentDao.selectCountByAlbumId(albumId);
		List<Comment> beans = commentDao.selectListByAlbumIdLimit(albumId, limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public List<Comment> selectForHotBySongListId(String songListId) {
		return commentDao.selectListBySongListIdAndMINAgreeNum(songListId, Constant.COMMENT_HOT_AGREENUM);
	}

	@Override
	public PageBean<Comment> selectPageBeanBySongListId(String songListId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = commentDao.selectCountBySongListId(songListId);
		List<Comment> beans = commentDao.selectListBySongListIdLimit(songListId, limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public List<Comment> selectForHotBySongId(String songId) {
		return commentDao.selectListBySongIdAndMINAgreeNum(songId, Constant.COMMENT_HOT_AGREENUM);
	}

	@Override
	public PageBean<Comment> selectPageBeanBySongId(String songId, Integer page) {
		int limit = Constant.LIMIT_DEFAULT;
		int count = commentDao.selectCountBySongId(songId);
		List<Comment> beans = commentDao.selectListBySongIdLimit(songId, limit * (page - 1), limit);
		return new PageBean<>(page, limit, count, beans);
	}

	@Override
	public Comment selectSongMenuCommentById(String commentId) {
		return commentDao.selectSongMenuCommentById(commentId);
	}

	@Override
	public Comment selectAlbumCommentById(String commentId) {
		return commentDao.selectAlbumCommentById(commentId);
	}

	@Override
	public Comment selectSongListCommentById(String commentId) {
		return commentDao.selectSongListCommentById(commentId);
	}

	@Override
	public Comment selectSongCommentById(String commentId) {
		return commentDao.selectSongCommentById(commentId);
	}

}
