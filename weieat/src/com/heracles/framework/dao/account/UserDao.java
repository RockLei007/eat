package com.heracles.framework.dao.account;

import java.util.List;

import com.heracles.framework.entity.account.User;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Component
public class UserDao extends HibernateDao<User, Long> {
	
	public User findUserByLoginName(String loginName){
		String sql = "from User where loginName = '"+loginName+ "' and status=0";
		List<User> userList = find(sql);
		if (userList != null && userList.size() > 0){
			return userList.get(0);
		}else
			return null;
	}
	
	public User getActiveUser(Long id){
		String sql = "from User where id = '" + id + "' and status=0";
		List<User> userList = find(sql);
		if (userList != null && userList.size() > 0){
			return userList.get(0);
		}else
			return null;
	}
	
	public List<User> getUserByOrg(Long orgId){
		String sql = "from User where org_id = '" + orgId + "' and status=0";
		return find(sql);
	}
	
	public void active(Long id){
		User entity = get(id);
		entity.setStatus(User.ACTIVE_STATUS);
		save(entity);
	}
	
	public void forbidden(Long id){
		User entity = get(id);
		entity.setStatus(User.FORBIDDEN_STATUS);
		save(entity);
	}
	
}
