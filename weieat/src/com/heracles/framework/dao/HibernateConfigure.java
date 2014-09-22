package com.heracles.framework.dao;

import org.hibernate.cfg.Configuration;

import com.heracles.framework.file.ConfigureFile;
import com.heracles.framework.tools.Unit;

public class HibernateConfigure {
	
	private Configuration configuration = null;
	
	private String driver;
	private String userName;
	private String url;
	private String password;
	private String dialect;
	
	private HibernateConfigure(){
	}
	
	public static HibernateConfigure getInstance(){
		return new HibernateConfigure();
	}
	
	public static HibernateConfigure getInstance(String fileName){
		ConfigureFile file =  ConfigureFile.getInstance(fileName);
		HibernateConfigure config = new HibernateConfigure();
		config.setDriver(file.getLine("jdbc.driver"));
		config.setUserName(file.getLine("jdbc.username"));
		config.setPassword(file.getLine("jdbc.password"));
		config.setDialect(file.getLine("hibernate.dialect"));
		config.setUrl(file.getLine("jdbc.url"));
		file.release();
		return config;
	}
	
	public Configuration getConfiguration(){
		configuration = new Configuration();
		if (Unit.isNotNull(url) && Unit.isNotNull(driver)){
			configuration.setProperty("hibernate.connection.driver_class", driver);
			configuration.setProperty("hibernate.connection.url", url);
			configuration.setProperty("hibernate.connection.username", userName);
			configuration.setProperty("hibernate.connection.password", password);
			configuration.setProperty("hibernate.dialect", dialect);
			return configuration;
		}else		
		return configuration.configure();
	}
	
	public void setDriver(String driver){
		this.driver = driver;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public void setDialect(String dialect){
		this.dialect = dialect;
	}

}
