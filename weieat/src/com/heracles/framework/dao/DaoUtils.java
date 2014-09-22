package com.heracles.framework.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heracles.framework.tools.Unit;


public class DaoUtils {
	
	private static Session session = null;
	public static String fileName = "application.properties"; 
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	private DaoUtils(){
	}
	
	public static DaoUtils getInstance(){
		session = HibernateSession.currentSession(HibernateConfigure.getInstance());
		return new DaoUtils();
	}
	
	public static DaoUtils getInstance(String configFile){
		if (Unit.isNotNull(configFile)){
			session = HibernateSession.currentSession(HibernateConfigure.getInstance(configFile));
		}else
			session = HibernateSession.currentSession(HibernateConfigure.getInstance(fileName));
		return new DaoUtils();
	}
	
	public static DaoUtils getInstance(HibernateConfigure config){
		session = HibernateSession.currentSession(config);
		return new DaoUtils();
	}
	
	public boolean naturalExcuteSql(String sql){
		Transaction tx = session.beginTransaction();  
        Connection con = session.connection();  
        Statement cstmt = null;
        boolean result = false;
		try {
			cstmt = con.createStatement();
			cstmt.execute(sql);
			con.close();
			result = true;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			result = false;
		}  
        tx.commit(); 
   		return result;
	}

	public String batchNaturalSql(String[] s){
		StringBuffer result = new StringBuffer();
		if (s != null && s.length > 0){
			for (int i = 0; i < s.length; i++){
				result.append(String.valueOf(naturalExcuteSql(s[i])) + '\n');
			}
		}
		return result.toString();
	}
	
	public List<Object> query(String sql, Class<Object> entity){
		return session.createSQLQuery(sql).addEntity(entity).list();
	}
	
}