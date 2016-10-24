package cn.xiedacon.admin.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin/album/tag")
@ResponseBody
public class AlbumTagAdminController {

	//@Autowired
	//private AlbumTagAdminService albumTagService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean() {
		return null;
	}
}
