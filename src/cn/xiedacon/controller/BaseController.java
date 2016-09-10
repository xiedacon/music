package cn.xiedacon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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

	@RequestMapping(value = "/html/{name:.+}", method = RequestMethod.GET)
	public String html(@PathVariable("name") String name) {
		return "stage/" + name;
	}

	@RequestMapping(value = "/json/{name:.+}", method = RequestMethod.GET)
	public void json(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) {
		writeToResponse("jsons/" + name + ".json", request, response);
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
		response.setContentType("audio/mp3;");
		writeToResponse(songService.selectFileUriById(songId), request, response);
	}

	private void writeToResponse(String uri, HttpServletRequest request, HttpServletResponse response) {
		InputStream in = request.getServletContext().getResourceAsStream(uri);
		if (in == null) {
			return;
		}
		try {
			response.setCharacterEncoding("utf-8");
			IOUtils.copy(in, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getResourceAsString(HttpServletRequest request, String uri) {
		InputStream in = request.getServletContext().getResourceAsStream(uri);
		if (in == null) {
			return null;
		}
		try {
			return IOUtils.toString(in);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
