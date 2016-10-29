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
import cn.xiedacon.admin.service.SecondSongMenuTagAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.SongMenuFirstTag;
import cn.xiedacon.model.SongMenuSecondTag;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/secondSongMenuTag")
public class SecondSongMenuTagAdminController {

	@Autowired
	private SecondSongMenuTagAdminService secondTagService;
	@Autowired
	private FirstSongMenuTagAdminService firstTagService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> selectList() {
		return MessageUtils.createSuccess(secondTagService.selectList());
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(@RequestParam("name") String name,
			@RequestParam("firstTagId") String firstTagId) {
		SongMenuFirstTag firstTag = firstTagService.selectById(firstTagId);
		if (firstTag == null) {
			return MessageUtils.createError("firstTagId", "一级分类错误");
		}
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "分类名不能为空");
		}

		SongMenuSecondTag secondTag = factory.get(SongMenuSecondTag.class);
		secondTag.setId(UUIDUtils.randomUUID());
		secondTag.setName(name);
		secondTag.setFirstTagId(firstTag.getId());

		secondTagService.insert(secondTag);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("firstTagId") String firstTagId) {
		SongMenuSecondTag secondTag = secondTagService.selectById(id);
		if (secondTag == null) {
			return MessageUtils.createError("id", "分类不存在");
		}

		SongMenuFirstTag firstTag = firstTagService.selectById(firstTagId);
		if (firstTag == null) {
			return MessageUtils.createError("firstTagId", "一级分类错误");
		}
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "分类名不能为空");
		}

		secondTag.setName(name);
		secondTag.setFirstTagId(firstTag.getId());

		secondTagService.update(secondTag);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable String id) {
		SongMenuSecondTag secondTag = secondTagService.selectById(id);
		if (secondTag != null) {
			secondTagService.delete(secondTag);
		}

		return MessageUtils.createSuccess();
	}
}
