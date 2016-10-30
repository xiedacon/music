package cn.xiedacon.read.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.Comment;
import cn.xiedacon.model.Comments;
import cn.xiedacon.read.service.CommentReadService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.PageBean;

@Controller
@ResponseBody
@RequestMapping(value = "/comment", method = RequestMethod.GET)
public class CommentReadController {

	@Autowired
	private CommentReadService commentService;

	@RequestMapping("/albumId_{albumId:\\w+}")
	public Map<String, Object> selectCommentsByAlbumId(@PathVariable("albumId") String albumId) {
		List<Comment> hotList = commentService.selectForHotByAlbumId(albumId);
		PageBean<Comment> pageBean = commentService.selectPageBeanByAlbumId(albumId, Constant.PAGE_DEFAULT);
		return MessageUtils.createSuccess(new Comments(hotList, pageBean));
	}

	@RequestMapping("/albumId_{albumId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectPageBeanByAlbumId(@PathVariable("albumId") String albumId,
			@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(commentService.selectPageBeanByAlbumId(albumId, page));
	}

	@RequestMapping("/songId_{songId:\\w+}")
	public Map<String, Object> selectCommentsBySongId(@PathVariable("songId") String songId) {
		List<Comment> hotList = commentService.selectForHotBySongId(songId);
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongId(songId, Constant.PAGE_DEFAULT);
		return MessageUtils.createSuccess(new Comments(hotList, pageBean));
	}

	@RequestMapping("/songId_{songId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectPageBeanBySongId(@PathVariable("songId") String songId,
			@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(commentService.selectPageBeanBySongId(songId, page));
	}

	@RequestMapping("/songListId_{songListId:\\w+}")
	public Map<String, Object> selectCommentsBySongListId(@PathVariable("songListId") String songListId) {
		List<Comment> hotList = commentService.selectForHotBySongListId(songListId);
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongListId(songListId, Constant.PAGE_DEFAULT);
		return MessageUtils.createSuccess(new Comments(hotList, pageBean));
	}

	@RequestMapping("/songListId_{songListId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectPageBeanBySongListId(@PathVariable("songListId") String songListId,
			@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(commentService.selectPageBeanBySongListId(songListId, page));
	}

	@RequestMapping("/songMenuId_{songMenuId:\\w+}")
	public Map<String, Object> selectCommentsBySongMenuId(@PathVariable("songMenuId") String songMenuId) {
		List<Comment> hotList = commentService.selectForHotBySongMenuId(songMenuId);
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongMenuId(songMenuId, Constant.PAGE_DEFAULT);
		return MessageUtils.createSuccess(new Comments(hotList, pageBean));
	}

	@RequestMapping("/songMenuId_{songMenuId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectPageBeanBySongMenuId(@PathVariable("songMenuId") String songMenuId,
			@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(commentService.selectPageBeanBySongMenuId(songMenuId, page));
	}
}
