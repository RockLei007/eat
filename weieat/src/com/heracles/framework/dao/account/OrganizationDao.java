package com.heracles.framework.dao.account;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.tools.Unit;

@Component
public class OrganizationDao extends HibernateDao<Organization, Long> {
	
	public List<Organization> getRootOrg(){
		String hql = "from Organization where status = 0 and parent = 0 order by id";
		return find(hql);
	}
	
	public List<Organization> getChildOrg(long parentId){
		String hql = "from Organization where status = 0 and parent = " + parentId + " order by id";
		return find(hql);
	}
	
	public List<Organization> getOrgByName(String name){
		String hql = "from Organization where status = 0 and name like '%" + name + "%'";
		return find(hql);
	}
	
	public Organization getOrgByWeixin(String weixin){
		String hql = "from Organization where status = 0 and  weixin = '" + weixin + "'";
		List<Organization> orgList = find(hql);
		if (Unit.isNotNull(orgList)){
			return orgList.get(0);
		}else
			return null;
	}
	
	public Organization getOrg(Long id){
		Organization org = get(id);
		if (org.getStatus() == Organization.FORBIDDEN_STATUS){
			return null;
		}else
			return org;
	}

	public void delete(Long id){
		Organization org = get(id);
		org.setStatus(Organization.FORBIDDEN_STATUS);
		save(org);
	}
	
	public void active(Long id){
		Organization org = get(id);
		org.setStatus(Organization.ACTIVE_STATUS);
		save(org);
	}
	
}
