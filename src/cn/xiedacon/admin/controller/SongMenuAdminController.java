package cn.xiedacon.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.SongMenuAdminService;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.util.PageBean;

@Controller
@ResponseBody
@RequestMapping(value = "/admin/songMenu", method = RequestMethod.GET)
public class SongMenuAdminController {

	@Autowired
	private SongMenuAdminService songMenuService;

	@RequestMapping("/page_{page:[1-9]\\d*}")
	public PageBean<SongMenu> getNewPageBean(@PathVariable("page") Integer page) {
		return songMenuService.selectPageBean(page);
	}
}
