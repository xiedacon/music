package cn.xiedacon.admin.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.util.AdminManager;
import cn.xiedacon.util.MD5Util;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.ResourceLoader;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class AdminController {

	private Properties adminProperties = null;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestParam("name") String name, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		Properties properties = getAdminProperties();
		String dataName = (String) properties.get("name");
		String dataPassword = (String) properties.get("password");

		dataPassword = MD5Util.encode(dataPassword);
		if (dataName.equals(name) && dataPassword.equals(password)) {
			AdminManager.sign(name, response);
			return MessageUtils.createSuccess("url", "/music/admin/html/index");
		}
		return MessageUtils.createError("password", "密码错误");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public Map<String, Object> logout(HttpServletRequest request) {
		AdminManager.signout(request);
		return MessageUtils.createSuccess("url", "/music/admin");
	}

	private synchronized Properties getAdminProperties() {
		if (adminProperties == null) {
			adminProperties = new Properties();
			try {
				adminProperties.load(ResourceLoader.loadAsStream("WEB-INF/classes/admin.properties"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return adminProperties;
	}
}
