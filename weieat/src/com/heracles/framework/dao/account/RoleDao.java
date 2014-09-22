package com.heracles.framework.dao.account;

import java.util.List;

import com.heracles.framework.entity.account.Role;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 角色对象的泛型DAO.
 * 
 * @author calvin
 */
@Component
public class RoleDao extends HibernateDao<Role, Long> {

	//private static final String QUERY_USER_BY_ROLEID = "select u from User u left join u.roleList r where r.id=?";

	/**
	 * 重载函数,因为Role中没有建立与User的关联,因此需要以较低效率的方式进行删除User与Role的多对多中间表.
	 
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Long id) {
		Role role = get(id);
		//查询出拥有该角色的用户,并删除该用户的角色.
		List<User> users = createQuery(QUERY_USER_BY_ROLEID, role.getId()).list();
		for (User u : users) {
			u.getRoleList().remove(role);
		}
		super.delete(role);
	}*/
	
	public void delete(Long id) {
		Role role = get(id);
		role.setStatus(Role.FORBIDDEN_STATUS);
		save(role);
	}
	
	public void active(Long id){
		Role role = get(id);
		role.setStatus(Role.ACTIVE_STATUS);
		save(role);
	}
	
	public Role getActiveRole(Long id){
		String sql = "from Role where status = 0 and id="+id;
		List<Role> roleList = find(sql);
		if (roleList != null && roleList.size() > 0){
			return roleList.get(0);
		}else
			return null;
	}
	
	public List<Role> getAllActiveRole(){
		String sql = "from Role where status = 0";
		return find(sql);
	}
	
}
