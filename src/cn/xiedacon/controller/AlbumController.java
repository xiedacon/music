package cn.xiedacon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.AlbumService;
import cn.xiedacon.service.AlbumTagService;
import cn.xiedacon.service.CommentService;
import cn.xiedacon.service.SongService;
import cn.xiedacon.util.PageBean;
import cn.xiedacon.vo.AlbumTagVo;
import cn.xiedacon.vo.AlbumVo;
import cn.xiedacon.vo.CommentVo;
import cn.xiedacon.vo.CommentsVo;
import cn.xiedacon.vo.SimpleAlbumVo;
import cn.xiedacon.vo.SimpleSongVo;

@Controller
@ResponseBody
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private SongService songService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private AlbumTagService tagService;

	@RequestMapping(value = "/album/{id:\\w+}", method = RequestMethod.GET)
	public AlbumVo getAlbum(@PathVariable("id") String id) {
		return albumService.selectById(id);
	}

	@RequestMapping(value = "/album/{id:\\w+}/songList", method = RequestMethod.GET)
	public List<SimpleSongVo> getSongList(@PathVariable("id") String albumId) {
		return songService.selectListByAlbumIdOrderByRank(albumId);
	}

	@RequestMapping(value = "/album/{id:\\w+}/comments", method = RequestMethod.GET)
	public CommentsVo getComments(@PathVariable("id") String albumId) {
		List<CommentVo> hotList = commentService.selectForHotByAlbumId(albumId);
		PageBean<CommentVo> pageBean = commentService.selectPageBeanByAlbumId(albumId, 1);
		CommentsVo comments = new CommentsVo();
		comments.setHotList(hotList);
		comments.setPageBean(pageBean);
		return comments;
	}

	@RequestMapping(value = "/album/{id:\\w+}/comments/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<CommentVo> getCommentList(@PathVariable("id") String albumId, @PathVariable("page") int page) {
		return commentService.selectPageBeanByAlbumId(albumId, page);
	}

	@RequestMapping(value = "/albums/hot", method = RequestMethod.GET)
	public List<SimpleAlbumVo> getHotList() {
		return albumService.selectHotList();
	}

	@RequestMapping(value = "/albums/{tagId:\\w+}/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public PageBean<SimpleAlbumVo> getAlbumList(@PathVariable("tagId") String tagId,
			@PathVariable("page") Integer page) {
		if ("all".equals(tagId)) {
			tagId = null;
		}
		return albumService.selectPageBeanByTagIdOrderByCreateTimeLimit(tagId, page);
	}

	@RequestMapping(value = "/albums/tags", method = RequestMethod.GET)
	public List<AlbumTagVo> getTagList() {
		return tagService.selectList();
	}
}
