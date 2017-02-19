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
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.PageBean;

@Controller
@ResponseBody
@RequestMapping(value = "/comment", method = RequestMethod.GET)
public class CommentReadController {

	@Autowired
	private CommentReadService commentService;

	@RequestMapping("/albumId_{albumId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectCommentsByAlbumId(@PathVariable("albumId") String albumId,
			@PathVariable("page") Integer page) {
		List<Comment> hotList = null;
		if(page == 1){
			hotList = commentService.selectForHotByAlbumId(albumId);			
		}
		PageBean<Comment> pageBean = commentService.selectPageBeanByAlbumId(albumId, page);
		return MessageUtils.success(new Comments(hotList, pageBean));
	}

	@RequestMapping("/songId_{songId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectCommentsBySongId(@PathVariable("songId") String songId,
			@PathVariable("page") Integer page) {
		List<Comment> hotList = null;
		if(page == 1){
			hotList = commentService.selectForHotBySongId(songId);
		}
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongId(songId, page);
		return MessageUtils.success(new Comments(hotList, pageBean));
	}
	
	@RequestMapping("/songListId_{songListId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectCommentsBySongListId(@PathVariable("songListId") String songListId,
			@PathVariable("page") Integer page) {
		List<Comment> hotList = null;
		if(page == 1){
			hotList = commentService.selectForHotBySongListId(songListId);
		}
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongListId(songListId, page);
		return MessageUtils.success(new Comments(hotList, pageBean));
	}

	@RequestMapping("/songMenuId_{songMenuId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectCommentsBySongMenuId(@PathVariable("songMenuId") String songMenuId,
			@PathVariable("page") Integer page) {
		List<Comment> hotList =  null;
		if(page == 1){
			hotList = commentService.selectForHotBySongMenuId(songMenuId);
		}
		PageBean<Comment> pageBean = commentService.selectPageBeanBySongMenuId(songMenuId, page);
		return MessageUtils.success(new Comments(hotList, pageBean));
	}
}
