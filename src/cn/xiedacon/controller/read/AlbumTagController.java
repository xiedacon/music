package cn.xiedacon.controller.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.AlbumTag;
import cn.xiedacon.service.AlbumTagService;

@Controller
@ResponseBody
@RequestMapping(value = "/albumTag", method = RequestMethod.GET)
public class AlbumTagController {

	@Autowired
	private AlbumTagService tagService;

	@RequestMapping(value = "/s")
	public List<AlbumTag> getList() {
		return tagService.selectList();
	}
}
