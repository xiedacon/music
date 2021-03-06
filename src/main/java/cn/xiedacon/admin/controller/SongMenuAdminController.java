package cn.xiedacon.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.SongMenuAdminService;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.util.CharsetUtils;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping(value = "/admin/songMenu")
public class SongMenuAdminController {

	@Autowired
	private SongMenuAdminService songMenuService;

	@RequestMapping(value = "/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.success(songMenuService.selectPageBeanOrderByCreateTime(page));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		SongMenu songMenu = songMenuService.selectExist(id);
		if (songMenu != null) {
			songMenuService.delete(songMenu);
		}
		return MessageUtils.success();
	}

	@RequestMapping(value = "/name/{name:.+}/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBeanByNameLike(@PathVariable("name") String name,
			@PathVariable("page") Integer page) {
		String nameLike = "%" + CharsetUtils.change(name, "ISO-8859-1", "UTF-8") + "%";
		return MessageUtils.success("data", songMenuService.selectPageBeanByNameLike(page, nameLike));
	}
}
