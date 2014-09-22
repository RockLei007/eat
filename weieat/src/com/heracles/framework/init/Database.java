package com.heracles.framework.init;

import com.heracles.framework.dao.DaoUtils;
import com.heracles.framework.dao.HibernateConfigure;
import com.heracles.framework.file.ConfigureFile;
import com.heracles.framework.tools.Unit;

/**
 * 数据库操作类，执行数据初始化.
 * 
 * @author yinzj
 */

public class Database {
	
	public static final int MYSQL_TYPE = 0;
	public static final int SQLSERVER_TYPE = 1;
	public static final int ORALCE_TYPE = 2;
	public static final int DB2_TYPE = 3;
	
	private DaoUtils dao = null;
	private HibernateConfigure config = null;
	
	private String databaseName = "";
	private String userName = "";
	private String password = "";
	private String host = "";
	
	private Database(){
		
	}
	
	public static Database getInstance(){
		return new Database();
	}
	
	public boolean createConnection(String databaseName, int databaseType, String host, String userName, String password){
		if (Unit.isNotNull(databaseName)) this.databaseName = databaseName;
		if (Unit.isNotNull(userName)) this.userName = userName;
		if (password != null) this.password = password;
		if (Unit.isNotNull(host)) this.host = host;
		ConfigureFile conf = ConfigureFile.getInstance("application.properties");
		config = HibernateConfigure.getInstance();
		
		boolean result = false;
		if (databaseType == MYSQL_TYPE){
			result = createMysql(conf);
		}else
		if (databaseType == SQLSERVER_TYPE){
			result = createSqlserver(conf);
		}else
		if (databaseType == ORALCE_TYPE){
			result = createOracle(conf);
		}else
		if (databaseType == DB2_TYPE){
			result = createDB2(conf);
		}	
		return result;
	}
	
	private boolean createMysql(ConfigureFile conf){		
		config.setDialect("org.hibernate.dialect.MySQL5InnoDBDialect");
		config.setDriver("com.mysql.jdbc.Driver");
		config.setPassword(this.password);
		config.setUrl("jdbc:mysql://" + this.host + "/" + this.databaseName + "?useUnicode=true&characterEncoding=utf-8");
		config.setUserName(this.userName);
		dao = DaoUtils.getInstance(config);

		String sql = "create database " + this.databaseName;
		boolean result = dao.naturalExcuteSql(sql);
		if (result){
			conf.setValue("jdbc.driver", "com.mysql.jdbc.Driver");
			conf.setValue("jdbc.url", "jdbc:mysql://" + this.host + "/" + this.databaseName + "?useUnicode=true&characterEncoding=utf-8");
			conf.setValue("jdbc.username", this.userName);
			conf.setValue("jdbc.password", this.password);
			conf.setValue("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		}
		conf.save();
		return result;
	}

	private boolean createSqlserver(ConfigureFile conf){
		return false;
	}
	
	private boolean createOracle(ConfigureFile conf){
		return false;
	}
	
	private boolean createDB2(ConfigureFile conf){
		return false;
	}
	
	
}
