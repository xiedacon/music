package cn.xiedacon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.CommentService;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.service.SongMenuTagService;
import cn.xiedacon.service.SongService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.CommentVo;
import cn.xiedacon.vo.CommentsVo;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.SimpleSongVo;
import cn.xiedacon.vo.SongMenuTagVo;
import cn.xiedacon.vo.SongMenuVo;

@Controller
@ResponseBody
public class SongMenuController {

	@Autowired
	private SongMenuService songMenuService;
	@Autowired
	private SongService songService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private SongMenuTagService tagService;

	@RequestMapping(value = "/songMenu/{id:\\w+}", method = RequestMethod.GET)
	public SongMenuVo getSongMenu(@PathVariable("id") String id) {
		return songMenuService.selectSongMenuById(id);
	}

	@RequestMapping(value = "/songMenu/{id:\\w+}/songList", method = RequestMethod.GET)
	public List<SimpleSongVo> getSongList(@PathVariable("id") String songMenuId) {
		return songService.selectListBySongMenuIdOrderByRank(songMenuId);
	}

	@RequestMapping(value = "/songMenu/{id:\\w+}/comments", method = RequestMethod.GET)
	public CommentsVo getComments(@PathVariable("id") String songMenuId) {
		List<CommentVo> hotList = commentService.selectForHotBySongMenuId(songMenuId);

		int page = 1;
		PageBean<CommentVo> pageBean = commentService.selectPageBeanBySongMenuId(songMenuId, page);

		CommentsVo comments = new CommentsVo();
		comments.setHotList(hotList);
		comments.setPageBean(pageBean);
		return comments;
	}

	@RequestMapping(value = "/songMenu/{id:\\w+}/comments/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<CommentVo> getCommentList(@PathVariable("id") String songMenuId, @PathVariable("page") int page) {
		return commentService.selectPageBeanBySongMenuId(songMenuId, page);
	}

	@RequestMapping(value = "/songMenus", method = RequestMethod.GET)
	public PageBean<SimpleSongMenuVo> getSongMenuList() {
		return getNewSongMenuListByPage(1);
	}

	@RequestMapping(value = "/songMenus/new/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<SimpleSongMenuVo> getNewSongMenuListByPage(@PathVariable("page") Integer page) {
		return songMenuService.selectPageBean(page);
	}

	@RequestMapping(value = "/songMenus/hot/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<SimpleSongMenuVo> getHotSongMenuListByPage(@PathVariable("page") Integer page) {
		return songMenuService.selectPageBeanOrderByCollectionNum(page);
	}

	@RequestMapping(value = "/songMenus/tags", method = RequestMethod.GET)
	public List<SongMenuTagVo> getSongMenuSecondTags() {
		return tagService.selectAll();
	}

	@RequestMapping(value = "/songMenus/{secondTagId:\\w+}", method = RequestMethod.GET)
	public PageBean<SimpleSongMenuVo> getSongMenuBySecondTagId(@PathVariable("secondTagId") String secondTagId) {
		return getHotSongMenuBySecondTagId(secondTagId, 1);
	}

	@RequestMapping(value = "/songMenus/{secondTagId:\\w+}/hot/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<SimpleSongMenuVo> getHotSongMenuBySecondTagId(@PathVariable("secondTagId") String secondTagId,
			@PathVariable("page") Integer page) {
		return songMenuService.selectPageBeanBySecondTagIdOrderByCollectionNum(secondTagId, page);
	}

	@RequestMapping(value = "/songMenus/{secondTagId:\\w+}/new/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<SimpleSongMenuVo> getNewSongMenuBySecondTagId(@PathVariable("secondTagId") String secondTagId,
			@PathVariable("page") Integer page) {
		return songMenuService.selectPageBeanBySecondTagId(secondTagId, page);
	}
}
