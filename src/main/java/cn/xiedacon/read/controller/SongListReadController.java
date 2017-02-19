package cn.xiedacon.read.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.read.service.SongListReadService;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping(value = "/songList", method = RequestMethod.GET)
public class SongListReadController {

	@Autowired
	private SongListReadService songListService;

	@RequestMapping("/{id:\\w+}")
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.success(songListService.selectById(id));
	}

	@RequestMapping("")
	public Map<String, Object> selectList() {
		return MessageUtils.success(songListService.selectList());
	}

}
