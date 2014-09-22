package com.heracles.framework.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.heracles.framework.cache.AppFactory;
import com.heracles.framework.cache.TemplateFactory;
import com.heracles.framework.file.ConfigureFile;
import com.heracles.framework.file.ConfigureHelper;
import com.heracles.framework.general.DispatchServices;
import com.heracles.framework.task.control.TaskParameter;

public class HeraclesServlet implements Servlet{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletConfig.getServletContext());
		/*实现部分service类的结果集静态化到内容中*/
		AppFactory app = AppFactory.getInstances();
		app.createCache(ctx);
		TemplateFactory template = TemplateFactory.getInstances();
		template.createXmlCache();
		template.createHtmlCache();
		
		DispatchServices.setWebAppContext(ctx);
		TaskParameter.setStartTime(System.currentTimeMillis());
		ConfigureFile file = ConfigureFile.getInstance("config.properties");
		ConfigureHelper.setconfigMap(file.getAllLine());
		
		logger.info("Heracles servlet is start");
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
	}
	

}
