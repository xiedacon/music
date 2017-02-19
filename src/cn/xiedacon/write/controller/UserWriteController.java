package cn.xiedacon.write.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.factory.Factory;
import cn.xiedacon.factory.SongMenuEnum;
import cn.xiedacon.model.SongMenu;
import cn.xiedacon.model.User;
import cn.xiedacon.model.User_SongMenuGL;
import cn.xiedacon.read.service.SongMenuReadService;
import cn.xiedacon.read.service.UserReadService;
import cn.xiedacon.util.MD5Util;
import cn.xiedacon.util.MessageUtils;
import cn.xiedacon.util.OAuthUtil;
import cn.xiedacon.util.UUIDUtils;
import cn.xiedacon.write.service.User_SongMenuGLService;
import cn.xiedacon.write.service.SongMenuWriteService;
import cn.xiedacon.write.service.UserWriteService;

@ResponseBody
@Controller
@RequestMapping("/user")
public class UserWriteController {

	@Autowired
	private UserWriteService userWriteService;
	@Autowired
	private UserReadService userReadService;
	@Autowired
	private SongMenuWriteService songMenuWriteService;
	@Autowired
	private SongMenuReadService songMenuReadService;
	@Autowired
	private User_SongMenuGLService user_SongMenuGLService;
	@Autowired
	private Factory factory;

	@RequestMapping(value = "/{id:\\w{32}}/username", method = RequestMethod.PUT)
	public Map<String, Object> updateUsernameById(@RequestParam("id") String id,
			@RequestParam("username") String username) {
		if (username == null || username.replaceAll("\\d", "").length() < 4) {
			return MessageUtils.error("username", "昵称格式错误！");
		}

		User dataUser = userReadService.selectById(id);
		if (dataUser == null) {
			System.out.println("用户不存在：id=" + id);
			return null;
		}

		dataUser.setName(username);
		userWriteService.updateUsernameById(dataUser.getName(), dataUser.getId());

		dataUser.setPassword(null);
		return MessageUtils.success("user", dataUser);
	}

	@RequestMapping(value = "/{id:\\w{32}}/collectSongMenu", method = RequestMethod.POST)
	public Map<String, Object> collectSongMenu(@PathVariable("id") String id,
			@RequestParam("songMenuId") String songMenuId) {
		User dataUser = userReadService.selectById(id);
		if (dataUser == null) {
			return MessageUtils.error("userId", "用户不存在");
		}
		SongMenu songMenu = songMenuReadService.selectById(songMenuId);
		if (songMenu == null) {
			return MessageUtils.error("songMenuId", "歌单不存在");
		}

		User_SongMenuGL gl = user_SongMenuGLService.selectExistBySongMenuIdAndCollectorId(songMenu.getId(),
				dataUser.getId());
		if (gl == null) {
			user_SongMenuGLService.insert(new User_SongMenuGL(dataUser.getId(), songMenu.getId(),
					songMenu.getCollectionNum() + 1, dataUser.getCollectSongMenuNum() + 1));
			return MessageUtils.success();
		} else {
			return MessageUtils.info("songMenuId", "已收藏");
		}
	}

	@RequestMapping(value = "/oAuth_github", method = RequestMethod.GET)
	public void oAuth_github(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, Object> params = OAuthUtil.oAuthGithub();
		String githubId = ((String) params.get("id")).split(",")[0];

		User dataUser = userReadService.selectByGitHubId(githubId);

		if (dataUser == null) {
			dataUser = factory.get(User.class);
			String id = UUIDUtils.uuid(githubId);
			dataUser.setId(id);
			dataUser.setGithubId(githubId);
			userWriteService.insert(dataUser);
		} else {
			dataUser.setPassword(null);
			request.setAttribute("user", dataUser);
			request.setAttribute("oauto_github_first", false);
			request.getSession().setAttribute("user", dataUser);
			request.getRequestDispatcher("/WEB-INF/redirect.jsp").forward(request, response);
		}
	}

	@RequestMapping(value = "/login_phone", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestParam("phone") String phone, @RequestParam("password") String password) {
		if (phone == null || !phone.matches("1[3|4|5|8]\\d{9}")) {
			return MessageUtils.error("phone", "手机号格式错误！");
		}
		if (password == null || password.length() != 22) {
			return MessageUtils.error("password", "密码格式错误！");
		}

		User dataUser = userReadService.selectByPhone(phone);
		if (dataUser == null) {
			return MessageUtils.error("phone", "手机号未注册！");
		}

		if (password.equals(dataUser.getPassword())) {
			if (dataUser.getName() == null) {
				return MessageUtils.error("phone", "手机号未注册！");
			}
			dataUser.setPassword(null);
			return MessageUtils.success("user", dataUser);
		} else {
			return MessageUtils.error("phone", "手机号或密码错误！");
		}
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public Map<String, Object> regist(@RequestParam("phone") String phone, @RequestParam("password") String password) {
		if (phone == null || !phone.matches("1[3|4|5|8]\\d{9}")) {
			return MessageUtils.error("phone", "手机号格式错误！");
		}
		if (password == null || !password.matches(".{5,15}")) {
			return MessageUtils.error("password", "密码格式错误！");
		}

		User dataUser = userReadService.selectByPhone(phone);

		if (dataUser == null) {
			dataUser = factory.get(User.class);
			dataUser.setId(UUIDUtils.uuid(phone));
			dataUser.setPhone(phone);
			dataUser.setPassword(MD5Util.encode(password));
			dataUser.setCreateSongMenuNum(1);

			userWriteService.insert(dataUser);

			SongMenu songMenu = factory.get(SongMenu.class);
			songMenu.setId(UUIDUtils.randomUUID());
			songMenu.setName(SongMenuEnum.LOVE.name);
			songMenu.setCreatorId(dataUser.getId());
			songMenu.setCreatorName(dataUser.getName());
			songMenu.setIcon(SongMenuEnum.LOVE.icon);
			songMenu.setCreateTime(new Date());
			songMenu.setUserId(dataUser.getId());

			songMenuWriteService.insert(songMenu);
		} else if (dataUser.getName() == null) {
			dataUser.setPassword(MD5Util.encode(password));
			userWriteService.updatePasswordById(dataUser.getPassword(), dataUser.getId());
		} else {
			dataUser.setPassword(null);
			return MessageUtils.info("user", dataUser);
		}
		// 短信验证（失败，需要网址）
		dataUser.setPassword(null);
		return MessageUtils.success("user", dataUser);
	}

}
