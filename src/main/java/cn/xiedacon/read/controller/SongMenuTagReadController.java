package cn.xiedacon.read.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.read.service.SongMenuTagReadService;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping(value = "/songMenuTag", method = RequestMethod.GET)
public class SongMenuTagReadController {

	@Autowired
	private SongMenuTagReadService tagService;

	@RequestMapping("")
	public Map<String, Object> selectList() {
		return MessageUtils.success(tagService.selectList());
	}
}
