package com.heracles.framework.service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.heracles.framework.dao.account.OrganizationDao;
import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.entity.account.User;



/**
 * 组织机构的管理类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class OrganizationManager {
	
	private OrganizationDao organizationDao;
	
	@Transactional(readOnly = true)
	public Organization getOrg(Long id){
		if (id != null && id > 0L){
			return organizationDao.getOrg(id);
		}else{
			return null;
		}
	}
	
	@Transactional(readOnly = true)
	public List<Organization> getAll(){
		return organizationDao.getAll();
	}

	@Transactional(readOnly = true)
	public List<Organization> getRootOrg(){
		return organizationDao.getRootOrg();
	}
	
	@Transactional(readOnly = true)
	public List<Organization> getChildOrg(Long parentId){
		return organizationDao.getChildOrg(parentId);
	}
	
	@Transactional(readOnly = true)
	public List<Organization> getOrgByName(String name){
		return organizationDao.getOrgByName(name);
	}
	
	@Transactional(readOnly = true)
	public Organization getOrgByWeixin(String weixin){
		return organizationDao.getOrgByWeixin(weixin);
	}	
	
	public void delete(Long id){
		organizationDao.delete(id);
	}
	
	public void active(Long id){
		organizationDao.active(id);
	}
	
	public void save(Organization entity){
		organizationDao.save(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<User> getUserByOrg(Page<User> page, Long id){
		page.setResult(organizationDao.getOrg(id).getUserList());
		return page;
	}
	
	@Transactional(readOnly = true)
	public Page<Organization> searchUser(final Page<Organization> page, final List<PropertyFilter> filters) {
		return organizationDao.findPage(page, filters);
	}
	
	@Autowired
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	
}
