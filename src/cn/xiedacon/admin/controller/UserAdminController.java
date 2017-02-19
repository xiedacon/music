package cn.xiedacon.admin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.admin.service.UserAdminService;
import cn.xiedacon.model.User;
import cn.xiedacon.util.CharsetUtils;
import cn.xiedacon.util.MessageUtils;

@Controller
@ResponseBody
@RequestMapping("/admin/user")
public class UserAdminController {

	@Autowired
	private UserAdminService userService;

	@RequestMapping(value = "/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBean(@PathVariable("page") Integer page) {
		return MessageUtils.success(userService.selectPageBean(page));
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable("id") String id) {
		User user = userService.selectExist(id);
		if (user != null) {
			userService.delete(user);
		}
		return MessageUtils.success();
	}

	@RequestMapping(value = "/name/{name:.+}/page/{page:[1-9]\\d*}", method = RequestMethod.GET)
	public Map<String, Object> selectPageBeanByNameLike(@PathVariable("name") String name,
			@PathVariable("page") Integer page) {
		String nameLike = "%" + CharsetUtils.change(name, "ISO-8859-1", "UTF-8") + "%";
		return MessageUtils.success("data", userService.selectPageBeanByNameLike(page, nameLike));
	}
}
