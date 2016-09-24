package cn.xiedacon.controller.admin;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.User;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/user")
public class AdminUserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/page_{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.createSuccess("data", userService.selectPageBean(page));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> deleteUser(@PathVariable("id") String id) {
		User user = userService.selectById(id);
		if (user != null) {
			userService.deleteUser(user);
		}
		return MessageUtils.createSuccess();
	}
}
