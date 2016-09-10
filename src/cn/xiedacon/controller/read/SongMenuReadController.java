package cn.xiedacon.controller.read;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.SongMenu;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.util.PageBean;

@Controller
@ResponseBody
@RequestMapping(value = "/songMenu", method = RequestMethod.GET)
public class SongMenuReadController {

	@Autowired
	private SongMenuService songMenuService;

	@RequestMapping("/{id:\\w+}")
	public SongMenu getById(@PathVariable("id") String id) {
		return songMenuService.selectById(id);
	}

	@RequestMapping("/s/new_{page:[1-9]\\d*}")
	public PageBean<SongMenu> getNewPageBean(@PathVariable("page") Integer page) {
		return songMenuService.selectPageBean(page);
	}

	@RequestMapping("/s/hot_{page:[1-9]\\d*}")
	public PageBean<SongMenu> getHotPageBean(@PathVariable("page") Integer page) {
		return songMenuService.selectPageBeanOrderByCollectionNum(page);
	}

	@RequestMapping("/s/hot_{page:[1-9]\\d*}/secondTagId_{secondTagId:\\w+}")
	public PageBean<SongMenu> getHotPageBeanBySecondTagId(@PathVariable("secondTagId") String secondTagId,
			@PathVariable("page") Integer page) {
		return songMenuService.selectPageBeanBySecondTagIdOrderByCollectionNum(secondTagId, page);
	}

	@RequestMapping("/s/new_{page:[1-9]\\d*}/secondTagId_{secondTagId:\\w+}")
	public PageBean<SongMenu> getNewPageBeanBySecondTagId(@PathVariable("secondTagId") String secondTagId,
			@PathVariable("page") Integer page) {
		return songMenuService.selectPageBeanBySecondTagId(secondTagId, page);
	}

	@RequestMapping("/s/creatorId_{creatorId:\\w+}")
	public List<SongMenu> getListByCreatorId(@PathVariable("creatorId") String creatorId) {
		return songMenuService.selectListByCreatorId(creatorId);
	}

	@RequestMapping("/s/collectorId_{collectorId:\\w+}")
	public List<SongMenu> getListByCollectorId(@PathVariable("collectorId") String collectorId) {
		return songMenuService.selectListByCollectorId(collectorId);
	}
}
