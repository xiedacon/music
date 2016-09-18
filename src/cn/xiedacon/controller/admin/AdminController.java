package cn.xiedacon.controller.admin;

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

@Controller
@ResponseBody
@RequestMapping("/admin")
public class AdminController {

	private Properties adminProperties;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestParam("name") String name, @RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		Properties properties = getAdminProperties(request);
		String dataName = (String) properties.get("name");
		String dataPassword = (String) properties.get("password");

		dataPassword = MD5Util.encode(dataPassword);
		if (dataName.equals(name) && dataPassword.equals(password)) {
			AdminManager.sign(name, response);
			return MessageUtils.createSuccess("url","admin/html/index");
		}
		return MessageUtils.createError("password", "密码错误");
	}

	private Properties getAdminProperties(HttpServletRequest request) {
		adminProperties = new Properties();
		try {
			adminProperties.load(request.getServletContext().getResourceAsStream("WEB-INF/classes/admin.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return adminProperties;
	}
}
