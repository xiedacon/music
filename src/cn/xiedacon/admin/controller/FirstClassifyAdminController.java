package cn.xiedacon.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.FirstClassifyAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.FirstClassify;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/firstClassify")
public class FirstClassifyAdminController {

	@Autowired
	private Factory factory;
	@Autowired
	private FirstClassifyAdminService classifyService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> selectList() {
		return MessageUtils.createSuccess(Constant.SUCCESS_RETURNNAME, classifyService.selectList());
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		FirstClassify classify = classifyService.selectById(id);
		if (classify != null) {
			classifyService.delete(classify);
		}

		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, @RequestParam("name") String name) {
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "分类名称不能为空");
		}

		FirstClassify classify = classifyService.selectById(id);
		if (classify == null) {
			return MessageUtils.createError("id", "分类不存在");
		}

		classify.setName(name);
		classifyService.update(classify);
		return MessageUtils.createSuccess();
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(@RequestParam("name") String name) {
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.createError("name", "分类名称不能为空");
		}

		FirstClassify classify = factory.get(FirstClassify.class);
		classify.setId(UUIDUtils.randomUUID());
		classify.setName(name);

		classifyService.insert(classify);
		return MessageUtils.createSuccess();
	}
}
