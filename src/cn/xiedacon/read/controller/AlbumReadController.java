package cn.xiedacon.read.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.read.service.AlbumReadService;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping(value = "/album", method = RequestMethod.GET)
public class AlbumReadController {

	@Autowired
	private AlbumReadService albumService;

	@RequestMapping("/{id:\\w+}")
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(albumService.selectById(id));
	}

	@RequestMapping("/tagId_{tagId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectPageBeanByTagId(@PathVariable("tagId") String tagId,
			@PathVariable("page") Integer page) {
		if ("all".equals(tagId)) {
			return MessageUtils.createSuccess(albumService.selectPageBeanOrderByCreateTime(page));
		}
		return MessageUtils.createSuccess(albumService.selectPageBeanByTagIdOrderByCreateTime(tagId, page));
	}

	@RequestMapping("/singerId_{singerId:\\w+}/{page:[1-9]\\d*}")
	public Map<String, Object> selectPageBeanBySingerId(@PathVariable("singerId") String singerId,
			@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(albumService.selectPageBeanBySingerIdOrderByCreateTime(singerId, page));
	}
}
