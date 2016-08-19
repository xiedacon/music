package cn.xiedacon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xiedacon.service.SongService;

@Controller
public class BaseController {

	@Autowired
	private SongService songService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "stage/router";
	}

	@RequestMapping(value = "/html/{name}", method = RequestMethod.GET)
	public String html(@PathVariable("name") String name) {
		return "stage/" + name;
	}

	@RequestMapping(value = "/json/{name}", method = RequestMethod.GET)
	public void json(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) {
		String json = getResourceAsString(request, "jsons/" + name + ".json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
		}
	}

	@ResponseBody
	@RequestMapping(value = "/lrc/{songId:\\w+}", method = RequestMethod.GET)
	public Map<String, Object> getLrc(@PathVariable("songId") String songId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();

		String lyricUri = songService.selectLyricUriById(songId);
		String lrc = getResourceAsString(request, lyricUri);

		if (lrc == null) {
			result.put("code", 500);
		} else {
			result.put("code", 200);
			result.put("data", lrc);
		}
		return result;
	}

	@RequestMapping(value = "/file/{songId:\\w+}", method = RequestMethod.GET)
	public void getFile(@PathVariable("songId") String songId, HttpServletRequest request,
			HttpServletResponse response) {
		String fileUri = songService.selectFileUriById(songId);
		writeToResponse(request, response, fileUri);
	}

	private String getResourceAsString(HttpServletRequest request, String uri) {
		StringBuffer json = new StringBuffer();

		InputStream in = request.getServletContext().getResourceAsStream(uri);
		if (in == null) {
			return null;
		}

		InputStreamReader inr = null;
		try {
			inr = new InputStreamReader(in, "utf-8"); // 注意编码
			char[] temp = new char[1024];
			int num;
			while ((num = inr.read(temp)) > 0) { // 要有num值
				json.append(temp, 0, num);
			}

			return json.toString();

		} catch (IOException e) {
			e.printStackTrace(); // 暂时先这样
			return null;
		} finally {
			try {
				if (inr != null)
					inr.close();
			} catch (IOException e) {
			}
		}
	}

	private void writeToResponse(HttpServletRequest request, HttpServletResponse response, String uri) {
		InputStream in = request.getServletContext().getResourceAsStream(uri);
		if (in == null) {
			return;
		}

		try {
			byte[] temp = new byte[1024 * 1024];
			int num;
			while ((num = in.read(temp)) > 0) {
				response.getOutputStream().write(temp, 0, num);
			}
			return;
		} catch (IOException e) {
			//e.printStackTrace();
			return;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
	}
}
