package cn.xiedacon.controller.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.Comment;
import cn.xiedacon.model.Comments;
import cn.xiedacon.service.CommentService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.PageBean;

@Controller
@ResponseBody
@RequestMapping(value = "/comment", method = RequestMethod.GET)
public class CommentReadController {

	@Autowired
	private CommentService commentService;

	@RequestMapping("/s/albumId_{albumId:\\w+}")
	public Comments getCommentsByAlbumId(@PathVariable("albumId") String albumId) {
		List<Comment> hotList = commentService.selectForHotByAlbumId(albumId);
		PageBean<Comment> pageBean = commentService.selectPageBeanByAlbumId(albumId, Constant.PAGE_DEFAULT);
		return new Comments(hotList, pageBean);
	}

	@RequestMapping("/s/albumId_{albumId:\\w+}/{page:[1-9]\\d*}")
	public PageBean<Comment> getPageBeanByAlbumId(@PathVariable("albumId") String albumId,
			@PathVariable("page") Integer page) {
		return commentService.selectPageBeanByAlbumId(albumId, page);
	}

	@RequestMapping("/s/songId_{songId:\\w+}")
	public Comments getCommentsBySongId(@PathVariable("songId") String songId) {
		List<Comment> hotList = commentService.selectForHotBySongId(songId);
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongId(songId, Constant.PAGE_DEFAULT);
		return new Comments(hotList, pageBean);
	}

	@RequestMapping("/s/songId_{songId:\\w+}/{page:[1-9]\\d*}")
	public PageBean<Comment> getPageBeanBySongId(@PathVariable("songId") String songId,
			@PathVariable("page") Integer page) {
		return commentService.selectPageBeanBySongId(songId, page);
	}

	@RequestMapping("/s/songListId_{songListId:\\w+}")
	public Comments getCommentsBySongListId(@PathVariable("songListId") String songListId) {
		List<Comment> hotList = commentService.selectHotBySongListId(songListId);
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongListIdLimit(songListId, Constant.PAGE_DEFAULT);
		return new Comments(hotList, pageBean);
	}

	@RequestMapping("/s/songListId_{songListId:\\w+}/{page:[1-9]\\d*}")
	public PageBean<Comment> getPageBeanBySongListId(@PathVariable("songListId") String songListId,
			@PathVariable("page") Integer page) {
		return commentService.selectPageBeanBySongListIdLimit(songListId, page);
	}

	@RequestMapping("/s/songMenuId_{songMenuId:\\w+}")
	public Comments getCommentsBySongMenuId(@PathVariable("songMenuId") String songMenuId) {
		List<Comment> hotList = commentService.selectForHotBySongMenuId(songMenuId);
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongMenuId(songMenuId, Constant.PAGE_DEFAULT);
		return new Comments(hotList, pageBean);
	}

	@RequestMapping("/s/songMenuId_{songMenuId:\\w+}/{page:[1-9]\\d*}")
	public PageBean<Comment> getPageBeanBySongMenuId(@PathVariable("songMenuId") String songMenuId,
			@PathVariable("page") Integer page) {
		return commentService.selectPageBeanBySongMenuId(songMenuId, page);
	}
}
