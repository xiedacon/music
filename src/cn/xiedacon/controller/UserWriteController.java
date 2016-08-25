package cn.xiedacon.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.factory.Factory;
import cn.xiedacon.factory.SongMenuEnum;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.Constant;
import cn.xiedacon.util.MD5Util;
import cn.xiedacon.util.OAuthUtil;
import cn.xiedacon.util.UUIDUtils;

@ResponseBody
@Controller
@RequestMapping("/user")
public class UserWriteController {

	@Autowired
	private UserService userService;
	@Autowired
	private SongMenuService songMenuService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/{id:\\w{32}}/username", method = RequestMethod.POST)
	public Map<String, Object> updateUsername(HttpServletRequest request) {
		String id = request.getParameter("id");
		String username = request.getParameter("username");

		if (username == null || username.replaceAll("\\d", "").length() < 4) {
			return createErrorMessage("username", "昵称格式错误！");
		}

		User dataUser = userService.selectById(id);

		if (dataUser == null) { // 正常情况下不可能发生
			System.out.println("用户不存在：id=" + id);
			return null;
		}

		dataUser.setName(username);
		userService.updateUsername(dataUser);

		dataUser.setPassword(null);
		return createMessage(Constant.SUCCESS, "user", dataUser);
	}

	@RequestMapping(value = "/oAuth_github", method = RequestMethod.GET)
	public void oAuth_github(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> params = OAuthUtil.oAuthGithub(request);
		String githubId = ((String) params.get("id")).split(",")[0];

		User dataUser = userService.selectByGitHubId(githubId);

		if (dataUser == null) {
			dataUser = factory.get(User.class);
			String id = UUIDUtils.uuid(githubId);
			dataUser.setId(id);
			dataUser.setGithubId(githubId);
			userService.insertUser(dataUser);
		} else {
			dataUser.setPassword(null);
			request.setAttribute("user", dataUser);
			request.setAttribute("oauto_github_first", false);
			request.getSession().setAttribute("user", dataUser);
			request.getRequestDispatcher("/WEB-INF/redirect.jsp").forward(request, response);
		}
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
			dataUser.setPassword(null);
			return createMessage(Constant.SUCCESS, "user", dataUser);
		} else {
			return createErrorMessage("phone", "手机号或密码错误！");
		}
	}

	@Transactional
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

		if (dataUser == null) {
			dataUser = factory.get(User.class);
			String id = UUIDUtils.uuid(phone);
			dataUser.setId(id);
			dataUser.setPhone(phone);
			dataUser.setPassword(MD5Util.encode(password));
			dataUser.setCreateSongMenuNum(1);

			userService.insertUser(dataUser);

			SongMenu songMenu = factory.get(SongMenu.class);
			String songMenuId = UUIDUtils.randomUUID();
			songMenu.setId(songMenuId);
			songMenu.setName(SongMenuEnum.LOVE.name);
			songMenu.setCreatorId(dataUser.getId());
			songMenu.setCreatorName(dataUser.getName());
			songMenu.setIcon(SongMenuEnum.LOVE.icon);
			songMenu.setCreateTime(new Date());
			songMenu.setUserId(dataUser.getId());

			songMenuService.insertSongMenu(songMenu);
		} else if (dataUser.getName() == null) {
			dataUser.setPassword(MD5Util.encode(password));
			userService.updatePassword(dataUser);
		} else {
			dataUser.setPassword(null);
			return createMessage(Constant.REDIRECT, "user", dataUser);
		}
		// 短信验证（失败，需要网址）
		dataUser.setPassword(null);
		return createMessage(Constant.SUCCESS, "user", dataUser);
	}

	private Map<String, Object> createErrorMessage(String errorName, String errorValue) {
		Map<String, String> error = new HashMap<>();
		error.put("name", errorName);
		error.put("value", errorValue);
		return createMessage(Constant.ERROR, "error", error);
	}

	private Map<String, Object> createMessage(int code, String name, Object value) {
		Map<String, Object> result = new HashMap<>();
		result.put("code", code);
		result.put(name, value);
		return result;
	}

}
