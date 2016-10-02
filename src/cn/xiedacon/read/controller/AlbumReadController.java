package cn.xiedacon.read.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.Album;
import cn.xiedacon.read.service.AlbumReadService;
import cn.xiedacon.util.PageBean;

@Controller
@ResponseBody
@RequestMapping(value = "/album", method = RequestMethod.GET)
public class AlbumReadController {

	@Autowired
	private AlbumReadService albumService;

	@RequestMapping("/{id:\\w+}")
	public Album getAlbumById(@PathVariable("id") String id) {
		return albumService.selectById(id);
	}

	@RequestMapping("/s/tagId_{tagId:\\w+}/{page:[1-9]\\d*}")
	public PageBean<Album> getPageBeanByTagId(@PathVariable("tagId") String tagId, @PathVariable("page") Integer page) {
		if ("all".equals(tagId)) {
			tagId = null;
		}
		return albumService.selectPageBeanByTagIdOrderByCreateTimeLimit(tagId, page);
	}

	@RequestMapping("/s/singerId_{singerId:\\w+}/{page:[1-9]\\d*}")
	public PageBean<Album> getPageBeanBySingerId(@PathVariable("singerId") String singerId,
			@PathVariable("page") Integer page) {
		return albumService.selectPageBeanBySingerIdOrderByCreateTimeLimit(singerId, page);
	}
}
