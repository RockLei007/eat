package com.heracles.framework.dao.account;

import java.util.List;

import com.heracles.framework.entity.account.Authority;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 授权对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class AuthorityDao extends HibernateDao<Authority, Long> {
	
	public List<Authority> getAll(){
		String sql = "from Authority where parent <> 0";
		return find(sql);
	}
	
	public List<Authority> getAllParent(){
		String sql = "from Authority where parent = 0";
		return find(sql);
	}
	
	public List<Authority> getChild(Long parent){
		String sql = "from Authority where parent = " + parent;
		return find(sql);
	}
	
	public List<Authority> getAllAuthority(){
		return super.getAll();
	}
	
}
