package com.heracles.framework.service.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heracles.framework.dao.account.AuthorityDao;
import com.heracles.framework.dao.account.RoleDao;
import com.heracles.framework.dao.account.UserDao;
import com.heracles.framework.dao.account.UserInfoDao;
import com.heracles.framework.entity.account.Authority;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.entity.account.User;
import com.heracles.framework.entity.account.UserInfo;
import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

/**
 * 安全相关实体的管理类, 包括用户,角色,资源与授权类.
 * 
 * @author calvin
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AccountManager {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private UserDao userDao;
	private RoleDao roleDao;
	private UserInfoDao userInfoDao;
	private AuthorityDao authorityDao;

	//-- User Manager --//
	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userDao.getActiveUser(id);
	}
	
	public UserInfo getUserInfo(Long id){
		return userInfoDao.get(id);
	}

	public void saveUser(User entity) {
		userDao.save(entity);
	}

	public void saveUserInfo(UserInfo userInfo) {
		userInfoDao.save(userInfo);
	}
	
	public List<User> getUserByOrg(Long orgId){
		return userDao.getUserByOrg(orgId);
	}
	
	/**
	 * 删除用户,如果尝试删除超级管理员将抛出异常.
	 */
	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn(Language.getMessage("AccountManager.deleteUser.warn", new String[]{SpringSecurityUtils.getCurrentUserName()}));
			throw new ServiceException(Language.getMessage("AccountManager.deleteUser.error"));
		} else
		userDao.forbidden(id);
	}
	
	public void delete(Long id){
		userDao.delete(id);
	}
	
	/**
	 * 激活用户.
	 */
	public void activeUser(Long id) {
		userDao.active(id);
	}	

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 使用属性过滤条件查询用户.
	 */
	@Transactional(readOnly = true)
	public Page<User> searchUser(final Page<User> page, final List<PropertyFilter> filters) {
		return userDao.findPage(page, filters);
	}
	
	/**
	 * 根据组织机构查询用户.
	 */
	@Transactional(readOnly = true)
	public Page<User> searchUserByOrg(final Page<User> page, final List<PropertyFilter> filters, Long orgId) {
		filters.add(new PropertyFilter("EQS_orgId", String.valueOf(orgId)));
		return userDao.findPage(page, filters);
	}

	@Transactional(readOnly = true)
	public User findUserByLoginName(String loginName) {
		return userDao.findUserByLoginName(loginName);
	}

	/**
	 * 检查用户名是否唯一.
	 *
	 * @return loginName在数据库中唯一或等于oldLoginName时返回true.
	 */
	@Transactional(readOnly = true)
	public boolean isLoginNameUnique(String newLoginName, String oldLoginName) {
		return userDao.isPropertyUnique("loginName", newLoginName, oldLoginName);
	}

	//-- Role Manager --//
	@Transactional(readOnly = true)
	public Role getRole(Long id) {
		return roleDao.getActiveRole(id);
	}

	@Transactional(readOnly = true)
	public Page<Role> getAllStatusRole(Page<Role> page, final List<PropertyFilter> filters) {
		return roleDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public List<Role> getAllRole() {
		return roleDao.getAllActiveRole();
	}
	
	@Transactional(readOnly = true)
	public Map<Long, String> getAllRoleToMap() {
		List<Role> roleList = roleDao.getAllActiveRole();
		Map<Long, String> roleMap = null; 
		if (roleList != null && roleList.size() > 0){
			roleMap = new HashMap<Long, String>();
			for(int i = 0; i < roleList.size(); i++){
				Role role = roleList.get(i);
				roleMap.put(role.getId(), role.getName());
			}
		}
		return roleMap;
	}

	public void saveRole(Role entity) {
		roleDao.save(entity);
	}

	public void deleteRole(Long id) {
		roleDao.delete(id);
	}
	
	public void activeRole(Long id){
		roleDao.active(id);
	}

	//-- Authority Manager --//
	@Transactional(readOnly = true)
	public List<Authority> getAllAuthority() {
		return authorityDao.getAllAuthority();
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Autowired
	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
	
	
}
