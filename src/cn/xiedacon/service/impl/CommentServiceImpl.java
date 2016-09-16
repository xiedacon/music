package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.CommentDao;
import cn.xiedacon.model.Comment;
import cn.xiedacon.service.CommentService;
import cn.xiedacon.util.PageBean;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> selectForHotBySongMenuId(String songMenuId) {
		int agreeNum = 10;

		return commentDao.selectListBySongMenuIdAndMINAgreeNum(songMenuId, agreeNum);
	}

	@Override
	public PageBean<Comment> selectPageBeanBySongMenuId(String songMenuId, Integer page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		int limit = 10;
		int begin = limit * (page - 1);
		int count = commentDao.selectCountBySongMenuId(songMenuId);
		int totalPage = count % limit == 0 ? count / limit : count / limit + 1;

		List<Comment> beans = commentDao.selectListBySongMenuIdLimit(songMenuId, begin, limit);

		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setTotalPage(totalPage);
		pageBean.setCount(count);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public List<Comment> selectForHotByAlbumId(String albumId) {
		int agreeNum = 10;

		return commentDao.selectListByAlbumIdAndMINAgreeNum(albumId, agreeNum);
	}

	@Override
	public PageBean<Comment> selectPageBeanByAlbumId(String albumId, Integer page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		int limit = 10;
		int begin = limit * (page - 1);
		int count = commentDao.selectCountByAlbumId(albumId);
		int totalPage = count % limit == 0 ? count / limit : count / limit + 1;

		List<Comment> beans = commentDao.selectListByAlbumIdLimit(albumId, begin, limit);

		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public List<Comment> selectHotBySongListId(String songListId) {
		int agreeNum = 10;

		return commentDao.selectListBySongListIdAndMINAgreeNum(songListId, agreeNum);
	}

	@Override
	public PageBean<Comment> selectPageBeanBySongListIdLimit(String songListId, Integer page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		int limit = 10;
		int begin = limit * (page - 1);
		int count = commentDao.selectCountBySongListId(songListId);
		int totalPage = count % limit == 0 ? count / limit : count / limit + 1;

		List<Comment> beans = commentDao.selectListBySongListIdLimit(songListId, begin, limit);

		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public List<Comment> selectForHotBySongId(String songId) {
		int agreeNum = 10;

		return commentDao.selectListBySongIdAndMINAgreeNum(songId, agreeNum);
	}

	@Override
	public PageBean<Comment> selectPageBeanBySongId(String songId, Integer page) {
		PageBean<Comment> pageBean = new PageBean<Comment>();
		int limit = 10;
		int begin = limit * (page - 1);
		int count = commentDao.selectCountBySongId(songId);
		int totalPage = count % limit == 0 ? count / limit : count / limit + 1;

		List<Comment> beans = commentDao.selectListBySongIdLimit(songId, begin, limit);

		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

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
	public Comment selectSongMenuCommentById(String commentId) {
		return commentDao.selectSongMenuCommentById(commentId);
	}

	@Override
	public void updateSongMenuCommentAgreeNumById(Integer agreeNum, String id) {
		commentDao.updateSongMenuCommentAgreeNumById(agreeNum, id);
	}

	@Override
	public Comment selectAlbumCommentById(String commentId) {
		return commentDao.selectAlbumCommentById(commentId);
	}

	@Override
	public void updateAlbumCommentAgreeNumById(Integer agreeNum, String id) {
		commentDao.updateAlbumCommentAgreeNumById(agreeNum, id);
	}

	@Override
	public Comment selectSongListCommentById(String commentId) {
		return commentDao.selectSongListCommentById(commentId);
	}

	@Override
	public void updateSongListCommentAgreeNumById(Integer agreeNum, String id) {
		commentDao.updateSongListCommentAgreeNumById(agreeNum, id);
	}

	@Override
	public Comment selectSongCommentById(String commentId) {
		return commentDao.selectSongCommentById(commentId);
	}

	@Override
	public void updateSongCommentAgreeNumById(Integer agreeNum, String id) {
		commentDao.updateSongCommentAgreeNumById(agreeNum, id);
	}

}
