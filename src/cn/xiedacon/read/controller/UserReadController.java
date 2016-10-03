package cn.xiedacon.read.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.User;
import cn.xiedacon.read.service.UserReadService;

@ResponseBody
@Controller
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserReadController {

	@Autowired
	private UserReadService userService;

	@RequestMapping(value = "/{id:\\w+}")
	public User getById(@PathVariable("id") String id) {
		return userService.selectById(id);
	}
}
