package cn.xiedacon.util;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class MyContextLoaderListener extends ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ResourceUtils.setServletContext(event.getServletContext());
	}
}
