package cn.xiedacon.read.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.read.service.SongMenuTagReadService;

@Controller
@ResponseBody
@RequestMapping(value = "/songMenuTag", method = RequestMethod.GET)
public class SongMenuTagReadController {

	@Autowired
	private SongMenuTagReadService tagService;

	@RequestMapping("/s")
	public List<SongMenuFirstTag> getList() {
		return tagService.selectList();
	}
}
