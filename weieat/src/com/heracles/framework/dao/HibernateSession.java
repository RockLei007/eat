package com.heracles.framework.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HibernateSession {
	public static SessionFactory sessionFactory;  
	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();  
	
	private static Logger logger = LoggerFactory.getLogger(HibernateSession.class);
	 
	public static Session currentSession(HibernateConfigure config) throws HibernateException{ 
		try{  
			Configuration configuration = config.getConfiguration();   
			sessionFactory = configuration.buildSessionFactory();  
		}catch (Throwable ex){  
			logger.error("Initial SessionFactory creation failed." + ex);  
			throw new ExceptionInInitializerError(ex);  
		}  
		
		Session s = (Session) session.get();  
		if (s == null){  
			s = sessionFactory.openSession();  
			session.set(s);  
		}  
		return s;  
	}  
	
	  
}
