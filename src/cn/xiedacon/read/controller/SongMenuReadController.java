package cn.xiedacon.read.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.read.service.SongMenuReadService;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping(value = "/songMenu", method = RequestMethod.GET)
public class SongMenuReadController {

	@Autowired
	private SongMenuReadService songMenuService;

	@RequestMapping("/{id:[a-zA-Z0-9]+}")
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(songMenuService.selectById(id));
	}

	@RequestMapping("/new_{page:[1-9]\\d*}")
	public Map<String, Object> selectPageBeanOrderByCreateTime(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(songMenuService.selectPageBeanOrderByCreateTime(page));
	}

	@RequestMapping("/hot_{page:[1-9]\\d*}")
	public Map<String, Object> selectPageBeanOrderByCollectionNum(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess(songMenuService.selectPageBeanOrderByCollectionNum(page));
	}

	@RequestMapping("/hot_{page:[1-9]\\d*}/secondTagId_{secondTagId:\\w+}")
	public Map<String, Object> selectPageBeanBySecondTagIdOrderByCollectionNum(
			@PathVariable("secondTagId") String secondTagId, @PathVariable("page") Integer page) {
		return MessageUtils
				.createSuccess(songMenuService.selectPageBeanBySecondTagIdOrderByCollectionNum(secondTagId, page));
	}

	@RequestMapping("/new_{page:[1-9]\\d*}/secondTagId_{secondTagId:\\w+}")
	public Map<String, Object> selectPageBeanBySecondTagIdOrderByCreateTime(
			@PathVariable("secondTagId") String secondTagId, @PathVariable("page") Integer page) {
		return MessageUtils
				.createSuccess(songMenuService.selectPageBeanBySecondTagIdOrderByCreateTime(secondTagId, page));
	}

	@RequestMapping("/creatorId_{creatorId:\\w+}")
	public Map<String, Object> selectListByCreatorId(@PathVariable("creatorId") String creatorId) {
		return MessageUtils.createSuccess(songMenuService.selectListByCreatorId(creatorId));
	}

	@RequestMapping("/collectorId_{collectorId:\\w+}")
	public Map<String, Object> selectListByCollectorId(@PathVariable("collectorId") String collectorId) {
		return MessageUtils.createSuccess(songMenuService.selectListByCollectorId(collectorId));
	}
}
