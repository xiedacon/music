package cn.xiedacon.read.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.read.service.SingerReadService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping(value = "/singer", method = RequestMethod.GET)
public class SingerReadController {

	@Autowired
	private SingerReadService singerService;

	@RequestMapping("")
	public Map<String, Object> selectList() {
		return MessageUtils
				.createSuccess(singerService.selectListLimit(Constant.BEGIN_DEFAULT, Constant.SINGER_SHOW_NUM));
	}

	@RequestMapping("/hot")
	public Map<String, Object> selectForHot() {
		return MessageUtils.createSuccess(
				singerService.selectListOrderByCollectionNumLimit(Constant.BEGIN_DEFAULT, Constant.SINGER_SHOW_NUM));
	}

	@RequestMapping("/classifyId_{classifyId:\\d{4}}")
	public Map<String, Object> selectListByClassifyId(@PathVariable("classifyId") Integer classifyId) {
		return MessageUtils.createSuccess(singerService.selectListByClassifyIdOrderByCollectionNumLimit(classifyId,
				Constant.BEGIN_DEFAULT, Constant.SINGER_SHOW_NUM));
	}

	@RequestMapping("/{id:[a-zA-Z0-9]+}")
	public Map<String, Object> selectById(@PathVariable("id") String id) {
		return MessageUtils.createSuccess(singerService.selectById(id));
	}

	@RequestMapping("/{id:\\w+}/introduction")
	public Map<String, Object> selectIntroductionById(@PathVariable("id") String id) {
		// 可能扩展为富文本
		String introduction = singerService.selectIntroductionById(id);
		return MessageUtils.createSuccess(introduction);
	}
}
