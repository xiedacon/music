package cn.xiedacon.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.UserService;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/user")
public class AdminUserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "?page={page:\\d+}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		if (page == null || page < 1) {
			page = 1;
		}
		return MessageUtils.createSuccess("data", userService.selectPageBean(page));
	}
}
