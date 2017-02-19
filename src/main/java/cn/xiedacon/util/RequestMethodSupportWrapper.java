package cn.xiedacon.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestMethodSupportWrapper extends HttpServletRequestWrapper implements HttpServletRequest {

	private Map<String, String[]> parameterMap;

	private RequestMethodSupportWrapper(HttpServletRequest request) {
		super(request);
	}

	public RequestMethodSupportWrapper(HttpServletRequest request, Map<String, String[]> remainParameterMap) {
		this(request);
		remainParameterMap.putAll(request.getParameterMap());
		parameterMap = remainParameterMap;
	}

	@Override
	public String getParameter(String name) {
		return parameterMap.get(name)[0];
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(parameterMap.keySet());
	}

	@Override
	public String[] getParameterValues(String name) {
		return parameterMap.get(name);
	}

}
