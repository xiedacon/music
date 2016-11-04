package cn.xiedacon.read.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.read.service.SongReadService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.ResourceLoader;

@Controller
@ResponseBody
@RequestMapping(value = "/song", method = RequestMethod.GET)
public class SongReadController {

	@Autowired
	private SongReadService songService;

	@RequestMapping("/{id:\\w+}")
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(songService.selectById(id));
	}

	@RequestMapping("/albumId_{albumId:\\w+}")
	public Map<String, Object> selectListByAlbumIdOrderByRank(@PathVariable("albumId") String albumId) {
		return MessageUtils.createSuccess(songService.selectListByAlbumIdOrderByRank(albumId));
	}

	@RequestMapping("/songMenuId_{songMenuId:\\w+}")
	public Map<String, Object> selectListBySongMenuIdOrderByTime(@PathVariable("songMenuId") String songMenuId) {
		return MessageUtils.createSuccess(songService.selectListBySongMenuIdOrderByTime(songMenuId));
	}

	@RequestMapping("/songListId_{songListId:\\w+}")
	public Map<String, Object> selectListBySongListIdOrderByRank(@PathVariable("songListId") String songListId) {
		return MessageUtils.createSuccess(songService.selectListBySongListIdOrderByRank(songListId));
	}

	@RequestMapping("/songListId_{songListId:\\w+}/{limit:[0-9]\\d*}")
	public Map<String, Object> selectListBySongListIdOrderByRankLimit(@PathVariable("songListId") String songListId,
			@PathVariable("limit") Integer limit) {
		return MessageUtils.createSuccess(songService.selectListBySongListIdOrderByRankLimit(songListId, limit));
	}

	@RequestMapping("/singerId_{singerId:\\w+}")
	public Map<String, Object> selectListBySingerIdOrderByCollectionNumLimit(
			@PathVariable("singerId") String singerId) {
		return MessageUtils.createSuccess(songService.selectListBySingerIdOrderByCollectionNumLimit(singerId,
				Constant.BEGIN_DEFAULT, Constant.SINGER_SHOW_SONGNUM));
	}

	@RequestMapping("/{id:\\w+}/lrc")
	public Map<String, Object> selectLrcById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(ResourceLoader.getResourceAsString(songService.selectLyricUriById(id)));
	}
}
