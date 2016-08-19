package cn.xiedacon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.BuilderUtils;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.UserVo;

@Controller
@ResponseBody
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private SongMenuService songMenuService;
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{userId:\\w+}", method = RequestMethod.GET)
	public UserVo getUser(@PathVariable("userId") String userId) {
		return BuilderUtils.buildFrom(UserVo.class, userService.selectById(userId));
	}

	@RequestMapping(value = "/{userId:\\w+}/songMenu_create", method = RequestMethod.GET)
	public List<SimpleSongMenuVo> getCreateSongMenu(@PathVariable("userId") String userId) {
		return songMenuService.selectListByCreatorId(userId);
	}

	@RequestMapping(value = "/{userId:\\w+}/songMenu_collect", method = RequestMethod.GET)
	public List<SimpleSongMenuVo> getCollectSongMenu(@PathVariable("userId") String userId) {
		return songMenuService.selectListByCollectorId(userId);
	}
}
