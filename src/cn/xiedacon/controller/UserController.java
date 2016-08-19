package cn.xiedacon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.model.User;
import cn.xiedacon.service.SongMenuService;
import cn.xiedacon.service.UserService;
import cn.xiedacon.util.BuilderUtils;
import cn.xiedacon.vo.SimpleSongMenuVo;
import cn.xiedacon.vo.UserVo;

@ResponseBody
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private SongMenuService songMenuService;
	
	@RequestMapping(value = "/{id:\\w{32}}/changeUsername", method = RequestMethod.POST)
	public Map<String, Object> updateUsername(HttpServletRequest request) {
		String id = request.getParameter("id");
		String username = request.getParameter("username");

		if (username == null || username.replaceAll("\\d", "").length() < 4) {
			return createErrorMessage("username", "昵称格式错误！");
		}

		Map<String, Object> result = new HashMap<>();
		User dataUser = userService.selectById(id);

		if (dataUser == null) { // 正常情况下不可能发生
			System.out.println("用户不存在：id=" + id);
			return null;
		}

		dataUser.setName(username);
		userService.updateUsername(dataUser);

		result.put("code", 200);
		dataUser.setPassword(null);
		result.put("user", BuilderUtils.buildFrom(UserVo.class, dataUser));
		return result;
	}

	@RequestMapping(value = "/{id:\\w+}", method = RequestMethod.GET)
	public UserVo getUser(@PathVariable("id") String id) {
		return BuilderUtils.buildFrom(UserVo.class, userService.selectById(id));
	}
	
	@RequestMapping(value = "/{userId:\\w+}/createdSongMenu", method = RequestMethod.GET)
	public List<SimpleSongMenuVo> getCreatedSongMenus(@PathVariable("userId") String userId) {
		return songMenuService.selectListByCreatorId(userId);
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
