package com.heracles.framework.cache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.heracles.framework.tools.Unit;
import com.heracles.framework.xml.ReadXMLFile;

/*
 * 静态cache的工厂类，实现数据缓存到内存中，对于大数据及频繁变化的数据不推荐使用.
 * @author 31307@sohu.com
 * 
 */

public class AppFactory {

	private ReadXMLFile file; 
	private WebApplicationContext ctx;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/*
	 * 实例化方法 
	 */
	public static AppFactory getInstances(){
		return new AppFactory();
	}
	
	private AppFactory(){
		file = ReadXMLFile.getInstance(Unit.getWebInfPath(),"appStaticeCache");
	}

	/*
	 * 根据 appStaticeCache.xml配置文件自动加载需要缓存的数据
	 */
	public void createCache(WebApplicationContext ctx) {
		this.ctx = ctx;
		Iterator<Element> iterInner = null;
		String className = "";
		String clazz = "";
		Class<?> c = null;
		
		if (file.readNode("bean") != null){
			for (iterInner = file.readNode("bean"); iterInner.hasNext();) { 
				Element beanEle = iterInner.next();
				className = beanEle.attribute("id").getValue();
				clazz = beanEle.attribute("class").getValue();
				try {
					c = Class.forName(clazz);
				} catch (ClassNotFoundException e) {
					log.error(e.getMessage());
				}
				bind(beanEle, className, c);
			}
		}
	}

	private void bind(Element beanEle, String className,  Class<?> c){
		Object object = ctx.getBean(className);
		Iterator<Element>  methodIter = beanEle.elementIterator("method");
		if (methodIter != null){
			String methodType = "";
			String paramName = "";
			String methodName = "";
			while (methodIter.hasNext()){
				Element methodEle = methodIter.next();
				methodType = methodEle.attribute("returnType").getValue();
				paramName = methodEle.attribute("param").getValue();
				methodName = methodEle.getText();
				Method m = null;
				try {
					m = c.getMethod(methodName, null);
				} catch (SecurityException e) {
					log.error(e.getMessage());
				} catch (NoSuchMethodException e) {
					log.error(e.getMessage());
				}
				
				if (methodType.equals("list")){
					try {
						AppCache.setObject(paramName, (List<?>) m.invoke(object, null));
						log.info("static cache. The " + className + ":" + methodName + " is bind! The paramter name is :" + paramName);
					} catch (IllegalArgumentException e) {
						log.error(e.getMessage());
					} catch (IllegalAccessException e) {
						log.error(e.getMessage());
					} catch (InvocationTargetException e) {
						log.error(e.getMessage());
					}
				}else
				if (methodType.equals("map")){
					try {
						AppCache.setObject(paramName, (Map<?, ?>) m.invoke(object, null));
						log.info("static cache. The " + className + ":" + methodName + " is bind! The paramter name is :" + paramName);
					} catch (IllegalArgumentException e) {
						log.error(e.getMessage());
					} catch (IllegalAccessException e) {
						log.error(e.getMessage());
					} catch (InvocationTargetException e) {
						log.error(e.getMessage());
					}
				}
			}
		}
	}
	
}
