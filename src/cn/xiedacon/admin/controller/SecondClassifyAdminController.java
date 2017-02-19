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
import cn.xiedacon.admin.service.SecondClassifyAdminService;
import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.FirstClassify;
import cn.xiedacon.model.SecondClassify;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.UUIDUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/secondClassify")
public class SecondClassifyAdminController {

	@Autowired
	private SecondClassifyAdminService secondlassifyService;
	@Autowired
	private FirstClassifyAdminService firstClassifyService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> selectList() {
		return MessageUtils.success(secondlassifyService.selectList());
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> insert(@RequestParam("name") String name,
			@RequestParam("firstClassifyId") String firstClassifyId) {
		FirstClassify firstClassify = firstClassifyService.selectById(firstClassifyId);
		if (firstClassify == null) {
			return MessageUtils.error("firstClassifyId", "一级分类错误");
		}
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.error("name", "分类名不能为空");
		}

		SecondClassify secondClassify = factory.get(SecondClassify.class);
		secondClassify.setId(UUIDUtils.randomUUID());
		secondClassify.setName(name);
		secondClassify.setFirstClassifyId(firstClassify.getId());

		secondlassifyService.insert(secondClassify);
		return MessageUtils.success();
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.PUT)
	public Map<String, Object> update(@PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("firstClassifyId") String firstClassifyId) {
		SecondClassify secondClassify = secondlassifyService.selectById(id);
		if(secondClassify == null){
			return MessageUtils.error("id", "分类不存在");
		}
		
		FirstClassify firstClassify = firstClassifyService.selectById(firstClassifyId);
		if (firstClassify == null) {
			return MessageUtils.error("firstClassifyId", "一级分类错误");
		}
		if (name == null || name.trim().isEmpty()) {
			return MessageUtils.error("name", "分类名不能为空");
		}
		
		secondClassify.setName(name);
		secondClassify.setFirstClassifyId(firstClassify.getId());
		
		secondlassifyService.update(secondClassify);
		return MessageUtils.success();
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable String id) {
		SecondClassify secondClassify = secondlassifyService.selectById(id);
		if(secondClassify != null){
			secondlassifyService.delete(secondClassify);
		}
		
		return MessageUtils.success();
	}
}
