package cn.xiedacon.read.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.read.service.AlbumTagReadService;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping(value = "/albumTag", method = RequestMethod.GET)
public class AlbumTagReadController {

	@Autowired
	private AlbumTagReadService tagService;

	@RequestMapping(value = "")
	public Map<String, Object> selectList() {
		return MessageUtils.createSuccess(tagService.selectList());
	}
}
