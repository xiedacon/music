package cn.xiedacon.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

/**
 * 解决PUT/PATCH/DELETE请求Tomcat时，不解析请求体的BUG
 * 
 * @author xieda
 *
 */
public class RequestTypeSupportFilter implements Filter {

	private String[] emptyValues = new String[] { "" };

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;

		String method = request.getMethod();
		String contentType = request.getContentType();
		if (method == "GET" || method == "POST"
				|| (null != contentType && contentType.toLowerCase(Locale.ENGLISH).startsWith("multipart/"))) {
			chain.doFilter(req, res);
			return;
		}

		HttpServletRequest requestWapper = this.createRequestMethodSupportWrapper(request);
		chain.doFilter(requestWapper, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	private HttpServletRequest createRequestMethodSupportWrapper(HttpServletRequest request) {
		try {
			String requestBody = IOUtils.toString(request.getInputStream());
			requestBody = URLDecoder.decode(requestBody, "UTF-8");

			Map<String, String[]> remainParameterMap = new HashMap<>();
			String[] paramStrs = requestBody.split("&");

			if (paramStrs.length == 1 && paramStrs[0].isEmpty()) {
				return request;
			}

			for (String paramStr : paramStrs) {
				String[] paramEntry = paramStr.split("=");
				String name = paramEntry[0];
				String[] values = emptyValues;
				if (paramEntry.length > 1) {
					values = paramEntry[1].split(",");
				}
				remainParameterMap.put(name, values);
			}
			return new RequestMethodSupportWrapper(request, remainParameterMap);
		} catch (IOException e) {
			// 解析失败
			System.out.println("解析失败");
			return request;
		}
	}
}
