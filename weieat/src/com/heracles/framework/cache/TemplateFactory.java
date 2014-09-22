package com.heracles.framework.cache;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heracles.framework.file.ListDirFile;
import com.heracles.framework.file.ReadFile;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.xml.ReadXMLFile;

public class TemplateFactory {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/*
	 * 实例化方法 
	 */
	public static TemplateFactory getInstances(){
		return new TemplateFactory();
	}
	
	private TemplateFactory(){
	}
	
	public void createXmlCache(){
		String path = Unit.getRootPath()+"WEB-INF/xml";
		List<String> templateList = ListDirFile.list(path, "xml");
		if (Unit.isNotNull(templateList)){
			log.info("The xml template cache is create:");
			for(String s : templateList){
				try {
					ReadXMLFile file = ReadXMLFile.getInstance(path + "/", s);
					TemplateCache.setXmlTemplate(s, file.getContent());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
				log.info(s + ".xml is load." );
			}
			log.info("The " + templateList.size() + " xml template is success load!");
		}else
			log.info("The xml template is null!");
	}
	
	public void createHtmlCache(){
		String path = Unit.getRootPath()+"template";
		String ext = "html";
		List<String> templateList = ListDirFile.list(path, ext);
		if (Unit.isNotNull(templateList)){
			log.info("The html template cache is create:");
			for(String s : templateList){
				ReadFile file = ReadFile.getInstance(path + "/", s+"."+ext);
				TemplateCache.setHtmlTemplate(s, file.getContent());
				file.release();
				log.info(s + ".html is load." );
			}
			log.info("The " + templateList.size() + " html template is success load!");
		}else
			log.info("The html template is null!");
	}
	
}
