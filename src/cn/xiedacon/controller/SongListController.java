package cn.xiedacon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.CommentService;
import cn.xiedacon.service.SongListService;
import cn.xiedacon.service.SongService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.CommentVo;
import cn.xiedacon.vo.CommentsVo;
import cn.xiedacon.vo.SimpleSongVo;
import cn.xiedacon.vo.SongListVo;

@Controller
@ResponseBody
@RequestMapping("/songList")
public class SongListController {

	@Autowired
	private SongListService songListService;
	@Autowired
	private SongService songService;
	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public SongListVo getSongListById(@PathVariable("id") String id) {
		return songListService.selectById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<SongListVo> getSongLists() {
		return songListService.selectList();
	}

	@RequestMapping(value = "/{songListId:\\w+}/songs", method = RequestMethod.GET)
	public List<SimpleSongVo> getSongsBySongListId(@PathVariable("songListId") String songListId) {
		return songService.selectListBySongListIdOrderByRank(songListId);
	}

	@RequestMapping(value = "/{songListId:\\w+}/comments", method = RequestMethod.GET)
	public CommentsVo getCommentListById(@PathVariable("songListId") String songListId) {
		CommentsVo comments = new CommentsVo();

		List<CommentVo> hotList = commentService.selectHotBySongListId(songListId);
		PageBean<CommentVo> pageBean = commentService.selectPageBeanBySongListIdLimit(songListId, 1);

		comments.setHotList(hotList);
		comments.setPageBean(pageBean);
		return comments;
	}

	@RequestMapping(value = "/{songListId:\\w+}/comments/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<CommentVo> getCommentListByIdLimit(@PathVariable("songListId") String songListId,
			@PathVariable("page") Integer page) {
		return commentService.selectPageBeanBySongListIdLimit(songListId, page);
	}
}
