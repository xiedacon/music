package cn.xiedacon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.CommentService;
import cn.xiedacon.service.SongService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.CommentVo;
import cn.xiedacon.vo.CommentsVo;
import cn.xiedacon.vo.SongVo;

@Controller
@ResponseBody
public class SongController {

	@Autowired
	private SongService songService;
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/song/{id:\\w+}", method = RequestMethod.GET)
	public SongVo getSongBy(@PathVariable("id") String id) {
		return songService.selectById(id);
	}

	@RequestMapping(value = "/song/{songId:\\w+}/comments", method = RequestMethod.GET)
	public CommentsVo getComments(@PathVariable("songId") String songId) {
		CommentsVo comments = new CommentsVo();
		List<CommentVo> hotList = commentService.selectForHotBySongId(songId);
		PageBean<CommentVo> pageBean = commentService.selectPageBeanBySongId(songId, 1);
		comments.setHotList(hotList);
		comments.setPageBean(pageBean);
		return comments;
	}

	@RequestMapping(value = "/song/{songId:\\w+}/comments/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<CommentVo> getCommentList(@PathVariable("songId") String songId,
			@PathVariable("page") Integer page) {
		return commentService.selectPageBeanBySongId(songId, page);
	}
}
