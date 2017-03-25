package cn.xiedacon.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.xiedacon.read.service.SongReadService;
import cn.xiedacon.util.ResourceLoader;

@Controller
public class BaseController {

	@Autowired
	private SongReadService songService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toIndex() {
		return index();
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return html("router");
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return adminHtml("login");
	}

	@RequestMapping(value = "/admin/html/{name:.+}", method = RequestMethod.GET)
	public String adminHtml(@PathVariable("name") String name) {
		return "backStage/" + name;
	}

	@RequestMapping(value = "/admin/html/edit/{name:.+}", method = RequestMethod.GET)
	public String adminEditHtml(@PathVariable("name") String name) {
		return "backStage/edit/" + name;
	}

	@RequestMapping(value = "/admin/html/add/{name:.+}", method = RequestMethod.GET)
	public String adminAddHtml(@PathVariable("name") String name) {
		return "backStage/add/" + name;
	}

	@RequestMapping(value = "/html/{name:.+}", method = RequestMethod.GET)
	public String html(@PathVariable("name") String name) {
		return "stage/" + name;
	}

	@RequestMapping(value = "/json/{name:.+}", method = RequestMethod.GET)
	public void json(@PathVariable("name") String name, HttpServletRequest request, HttpServletResponse response) {
		ResourceLoader.writeToResponse("jsons/" + name + ".json", response);
	}

	@RequestMapping(value = "/file/{songId:\\w+}", method = RequestMethod.GET)
	public void getFile(@PathVariable("songId") String songId, HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("audio/mpeg;");
		response.setHeader("Content-Disposition", "inline; filename=a.mp3;");
		ResourceLoader.writeToResponse(songService.selectFileUriById(songId), response);
		
	}
}
