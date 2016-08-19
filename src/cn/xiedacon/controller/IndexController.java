package cn.xiedacon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.AlbumService;
import cn.xiedacon.service.SongListService;
import cn.xiedacon.service.SongMenuSecondTagService;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.service.UserService;
import cn.xiedacon.vo.SimpleAlbumVo;
import cn.xiedacon.vo.SimpleSongListVo;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.SimpleUserVo;
import cn.xiedacon.vo.SongMenuSecondTagVo;

@ResponseBody
@RequestMapping("/index")
@Controller
public class IndexController {

	@Autowired
	private AlbumService albumService;
	@Autowired
	private SongMenuService songMenuService;
	@Autowired
	private SongMenuSecondTagService songMenuSecondTagService;
	@Autowired
	private UserService userService;
	@Autowired
	private SongListService songListService;

	@ResponseBody
	@RequestMapping(value = "/ablum", method = RequestMethod.GET)
	public List<SimpleAlbumVo> getAblumList() {
		return albumService.selectHotList();
	}

	@ResponseBody
	@RequestMapping(value = "/songMenuSecondTag", method = RequestMethod.GET)
	public List<SongMenuSecondTagVo> getSongMenuSecondTagList() {
		return songMenuSecondTagService.selectForIndex();
	}

	@ResponseBody
	@RequestMapping(value = "/songMenu", method = RequestMethod.GET)
	public List<SimpleSongMenuVo> getSongMenuList() {
		return songMenuService.selectForIndex();
	}

	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<SimpleUserVo> getUserList() {
		return userService.selectForIndex();
	}

	@ResponseBody
	@RequestMapping(value = "/songList", method = RequestMethod.GET)
	public List<SimpleSongListVo> getSongList() {
		return songListService.selectForIndex();
	}
}
