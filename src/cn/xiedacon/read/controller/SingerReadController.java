package cn.xiedacon.read.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.Singer;
import cn.xiedacon.read.service.SingerReadService;
import cn.xiedacon.util.Constant;

@Controller
@ResponseBody
@RequestMapping(value = "/singer", method = RequestMethod.GET)
public class SingerReadController {

	@Autowired
	private SingerReadService singerService;

	@RequestMapping("/s")
	public List<Singer> getList() {
		return singerService.selectListLimit(Constant.BEGIN_DEFAULT, Constant.SINGER_SHOW_NUM);
	}

	@RequestMapping("/s/hot")
	public List<Singer> getHotList() {
		return singerService.selectListOrderByCollectionNumLimit(Constant.BEGIN_DEFAULT, Constant.SINGER_SHOW_NUM);
	}

	@RequestMapping("/s/classifyId_{classifyId:\\d{4}}")
	public List<Singer> getListByClassifyId(@PathVariable("classifyId") Integer classifyId) {
		return singerService.selectListByClassifyIdOrderByCollectionNumLimit(classifyId, Constant.BEGIN_DEFAULT,
				Constant.SINGER_SHOW_NUM);
	}

	@RequestMapping("/{id:\\w+}")
	public Singer getById(@PathVariable("id") String id) {
		return singerService.selectById(id);
	}

	@RequestMapping("/{id:\\w+}/introduction")
	public Map<String, Object> getIntroductionById(@PathVariable("id") String id) {
		Map<String, Object> result = new HashMap<>();
		String introduction = singerService.selectIntroductionById(id);

		// 可能扩展为富文本
		result.put("code", Constant.SUCCESS);
		result.put("introduction", introduction);

		return result;
	}
}
