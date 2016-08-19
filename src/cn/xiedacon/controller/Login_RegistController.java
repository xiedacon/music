package cn.xiedacon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.xiedacon.factory.Factory;
import cn.xiedacon.model.User;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.util.BuilderUtils;
import cn.xiedacon.util.MD5Util;
import cn.xiedacon.util.OAuthUtil;
import cn.xiedacon.vo.SimpleUserVo;
import cn.xiedacon.vo.UserVo;

@Controller
@RequestMapping("/user")
public class Login_RegistController {

	@Autowired
	private UserService userService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/oAuth_github", method = RequestMethod.GET)
	public void oAuth_github(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> params = OAuthUtil.oAuthGithub(request);
		String githubId = ((String) params.get("id")).split(",")[0];

		User dataUser = userService.selectByGitHubId(githubId);
		SimpleUserVo userVo = new SimpleUserVo();

		if (dataUser != null) {
			userVo.setId(dataUser.getId());
			userVo.setName(dataUser.getName());
			userVo.setIcon(userVo.getIcon());
			request.setAttribute("user", userVo);
			request.setAttribute("oauto_github_first", false);
			request.getSession().setAttribute("user", userVo);
			request.getRequestDispatcher("/WEB-INF/redirect.jsp").forward(request, response);
			return;
		}

		String id = UUIDUtils.uuid(githubId);

		dataUser = new User();
		dataUser.setId(id);
		dataUser.setGithubId(githubId);

		userService.insertUser(dataUser);

		userVo.setId(dataUser.getId());
		userVo.setName(dataUser.getName());
		userVo.setIcon("image/default.jpg");

		request.setAttribute("user", userVo);
		request.setAttribute("oauto_github_first", true);
		request.getSession().setAttribute("user", userVo);

		request.getRequestDispatcher("/WEB-INF/redirect.jsp").forward(request, response);
	}

	@RequestMapping(value = "/login_phone", method = RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		if (phone == null || !phone.matches("1[3|4|5|8]\\d{9}")) {
			return createErrorMessage("phone", "手机号格式错误！");

		}
		if (password == null || password.length() != 22) {
			return createErrorMessage("password", "密码格式错误！");
		}

		User dataUser = userService.selectByPhone(phone);

		if (dataUser == null) {
			return createErrorMessage("phone", "手机号未注册！");
		}

		if (password.equals(dataUser.getPassword())) {
			if (dataUser.getName() == null) {
				return createErrorMessage("phone", "手机号未注册！");
			}
			Map<String, Object> result = new HashMap<>();
			result.put("code", 200);
			dataUser.setPassword(null);
			result.put("user", BuilderUtils.buildFrom(UserVo.class, dataUser));
			return result;
		} else {
			return createErrorMessage("phone", "手机号或密码错误！");
		}
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public Map<String, Object> regist(HttpServletRequest request) {
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		if (phone == null || !phone.matches("1[3|4|5|8]\\d{9}")) {
			return createErrorMessage("phone", "手机号格式错误！");
		}
		if (password == null || !password.matches(".{5,15}")) {
			return createErrorMessage("password", "密码格式错误！");
		}

		User dataUser = userService.selectByPhone(phone);
		Map<String, Object> result = new HashMap<>();

		if (dataUser != null && dataUser.getName() != null) {
			result.put("code", 302);
		} else if (dataUser != null) {
			dataUser.setPassword(MD5Util.encode(password));

			userService.updatePassword(dataUser);

			result.put("code", 200);
		} else {
			String id = UUIDUtils.uuid(phone);

			dataUser = factory.get(User.class);
			dataUser.setId(id);
			dataUser.setPhone(phone);
			dataUser.setPassword(MD5Util.encode(password));

			userService.insertUser(dataUser);
			result.put("code", 200);
		}
		// 短信验证（失败，需要网址）

		result.put("user", BuilderUtils.buildFrom(UserVo.class, dataUser));

		return result;
	}

	private Map<String, Object> createErrorMessage(String errorName, String errorValue) {
		Map<String, String> error = new HashMap<>();
		error.put("name", errorName);
		error.put("value", errorValue);

		Map<String, Object> result = new HashMap<>();
		result.put("code", 500);
		result.put("error", error);
		return result;
	}
}
