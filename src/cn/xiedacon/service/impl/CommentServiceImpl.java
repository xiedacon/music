package cn.xiedacon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xiedacon.dao.CommentDao;
import cn.xiedacon.service.CommentService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.CommentVo;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<CommentVo> selectForHotBySongMenuId(String songMenuId) {
		int agreeNum = 10;
		
		return commentDao.selectListBySongMenuIdAndMINAgreeNum(songMenuId,agreeNum);
	}

	@Override
	public PageBean<CommentVo> selectPageBeanBySongMenuId(String songMenuId, Integer page) {
		PageBean<CommentVo> pageBean = new PageBean<CommentVo>();
		int limit = 10;
		int begin = limit * (page - 1);
		int count = commentDao.selectCountBySongMenuId(songMenuId);
		int totalPage = count % limit == 0 ? count / limit : count / limit + 1;
		
		List<CommentVo> beans = commentDao.selectListBySongMenuId(songMenuId,begin,limit);

		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setTotalPage(totalPage);
		pageBean.setCount(count);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public List<CommentVo> selectForHotByAlbumId(String albumId) {
		int agreeNum = 10;

		return commentDao.selectListByAlbumIdAndMINAgreeNum(albumId,agreeNum);
	}

	@Override
	public PageBean<CommentVo> selectPageBeanByAlbumId(String albumId, Integer page) {
		PageBean<CommentVo> pageBean = new PageBean<CommentVo>();
		int limit = 10;
		int begin = limit * (page - 1);
		int count = commentDao.selectCountByAlbumId(albumId);
		int totalPage = count % limit == 0 ? count / limit : count / limit + 1;

		List<CommentVo> beans = commentDao.selectListByAlbumId(albumId,begin,limit);

		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public List<CommentVo> selectHotBySongListId(String songListId) {
		int agreeNum = 10;

		return commentDao.selectListBySongListIdAndMINAgreeNum(songListId,agreeNum);
	}

	@Override
	public PageBean<CommentVo> selectPageBeanBySongListIdLimit(String songListId, Integer page) {
		PageBean<CommentVo> pageBean = new PageBean<CommentVo>();
		int limit = 10;
		int begin = limit * (page - 1);
		int count = commentDao.selectCountBySongListId(songListId);
		int totalPage = count % limit == 0 ? count / limit : count / limit + 1;

		List<CommentVo> beans = commentDao.selectListBySongListId(songListId,begin,limit);

		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

	@Override
	public List<CommentVo> selectForHotBySongId(String songId) {
		int agreeNum = 10;

		return commentDao.selectListBySongIdAndMINAgreeNum(songId,agreeNum);
	}

	@Override
	public PageBean<CommentVo> selectPageBeanBySongId(String songId, Integer page) {
		PageBean<CommentVo> pageBean = new PageBean<CommentVo>();
		int limit = 10;
		int begin = limit * (page - 1);
		int count = commentDao.selectCountBySongId(songId);
		int totalPage = count % limit == 0 ? count / limit : count / limit + 1;

		List<CommentVo> beans = commentDao.selectListBySongId(songId,begin,limit);

		pageBean.setLimit(limit);
		pageBean.setPage(page);
		pageBean.setCount(count);
		pageBean.setTotalPage(totalPage);
		pageBean.setBeans(beans);
		return pageBean;
	}

}
