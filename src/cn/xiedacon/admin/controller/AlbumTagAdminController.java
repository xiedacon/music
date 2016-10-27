package cn.xiedacon.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.AlbumTagAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.AlbumTag;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/albumTag")
public class AlbumTagAdminController {

	@Autowired
	private Factory factory;
	@Autowired
	private AlbumTagAdminService albumTagService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> selectList() {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, albumTagService.selectList());
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		AlbumTag albumTag = albumTagService.selectById(id);
		if (albumTag != null) {
			albumTagService.delete(albumTag);
		}

		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, @RequestParam("name") String name) {
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "分类名称不能为空");
		}

		AlbumTag albumTag = albumTagService.selectById(id);
		if (albumTag == null) {
			return MessageUtils.createError("id", "分类不存在");
		}

		albumTag.setName(name);
		albumTagService.update(albumTag);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(@RequestParam("name") String name) {
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "分类名称不能为空");
		}

		AlbumTag albumTag = factory.get(AlbumTag.class);
		albumTag.setId(UUIDUtils.randomUUID());
		albumTag.setName(name);

		albumTagService.insert(albumTag);
		return MessageUtils.createSuccess();
	}
}
