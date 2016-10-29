package cn.xiedacon.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.FirstSongMenuTagAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/firstSongMenuTag")
public class FirstSongMenuTagAdminController {

	@Autowired
	private Factory factory;
	@Autowired
	private FirstSongMenuTagAdminService tagService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> selectList() {
		return MessageUtils.createSuccess(tagService.selectList());
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		SongMenuFirstTag tag = tagService.selectById(id);
		if (tag != null) {
			tagService.delete(tag);
		}

		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, @RequestParam("name") String name) {
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "分类名称不能为空");
		}

		SongMenuFirstTag tag = tagService.selectById(id);
		if (tag == null) {
			return MessageUtils.createError("id", "分类不存在");
		}

		tag.setName(name);
		tagService.update(tag);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(@RequestParam("name") String name) {
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "分类名称不能为空");
		}

		SongMenuFirstTag classify = factory.get(SongMenuFirstTag.class);
		classify.setId(UUIDUtils.randomUUID());
		classify.setName(name);

		tagService.insert(classify);
		return MessageUtils.createSuccess();
	}
}
