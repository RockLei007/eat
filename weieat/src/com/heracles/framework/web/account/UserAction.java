package com.heracles.framework.web.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.dao.HibernateUtils;
import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.entity.account.User;
import com.heracles.framework.entity.account.UserInfo;
import com.heracles.framework.security.MD5Util;
import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.service.account.AccountManager;
import com.heracles.framework.service.account.OrganizationManager;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.CrudActionSupport;
import com.heracles.framework.web.RoleAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 用户管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author calvin
 */
//定义URL映射对应/account/user.action
@Namespace("/system")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "user.action", type = "redirect") })
public class UserAction extends CrudActionSupport<User> {

	private static final long serialVersionUID = 8683878162525847072L;

	private AccountManager accountManager;
	private OrganizationManager organizationManager;

	//-- 页面属性 --//
	private Long id;
	private User entity;
	private UserInfo userInfo;
	private Page<User> page = new Page<User>(size);
	private List<Long> checkedRoleIds; //页面中钩选的角色id列表
	private Long checkedOrgId;

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	public User getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = accountManager.getUser(id);
			userInfo = accountManager.getUserInfo(entity.getId());
		} else {
			entity = new User();
			userInfo = new UserInfo();
			entity.setCreateDate(Datetime.getNow());
		}
		if (entity.getOrganization() == null){
			entity.setOrganization(getUserDetails().getOrganization());
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}

		boolean hasAdminAuth = false;
		List<Role> roles = getUserDetails().getUserRoles();
		for (Role role : roles) {
			if (role.getId().equals(1L)){
				hasAdminAuth = true;
			}
		}
		if (hasAdminAuth){
			page = accountManager.searchUser(page, filters);
		}else{
			Long orgId = getUserDetails().getOrganization().getId();
			if (orgId != null){
				page = organizationManager.getUserByOrg(page, orgId);
				//page = accountManager.searchUserByOrg(page, filters, orgId);
			}
		}

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedRoleIds = entity.getRoleIds();
		return INPUT;
	}

	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String save() throws Exception {
		try {
			//根据页面上的checkbox选择 整合User的Roles Set
			HibernateUtils.mergeByCheckedIds(entity.getRoleList(), checkedRoleIds, Role.class);
			Organization org = organizationManager.getOrg(getCheckedOrgId());
			if (Unit.isNotNull(entity.getPassword())){
				String password = MD5Util.MD5(entity.getPassword());
				entity.setPassword(password);
			}
			entity.setOrganization(org);
			userInfo.setUser(entity);
			entity.setUserInfo(userInfo);
			accountManager.saveUser(entity);
			addActionMessage(Language.getMessage("UserAction.save.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("UserAction.save.fail"));
		}
		return RELOAD;
	}

	@Override
	@RecordOperation(fieldId = "id", filedName = "loginName")
	public String delete() throws Exception {
		try {
			accountManager.deleteUser(id);
			addActionMessage(Language.getMessage("UserAction.delete.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("UserAction.delete.fail"));
		}
		return RELOAD;
	}

	@RecordOperation(fieldId = "id", filedName = "loginName")
	public String active(){
		try {
			accountManager.activeUser(id);
			addActionMessage(Language.getMessage("UserAction.active.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("UserAction.active.fail"));
		}
		return RELOAD;
	}
	
	//-- 其他Action函数 --//
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkLoginName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newLoginName = request.getParameter("loginName");
		String oldLoginName = request.getParameter("oldLoginName");

		if (accountManager.isLoginNameUnique(newLoginName, oldLoginName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<User> getPage() {
		return page;
	}

	/**
	 * input页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return accountManager.getAllRole();
	}
	
	/**
	 * input页面显示所有组织机构列表.
	 */
	public List<Organization> getAllOrgList() {
		return organizationManager.getAll();
	}

	/**
	 * input页面显示用户拥有的角色.
	 */
	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}
	
	/**
	 * input页面显示用户的组织机构.
	 */
	public Long getCheckedOrgId() {
		return this.checkedOrgId;
	}	
	
	/**
	 * input页面判断用户的角色.
	 */
	public boolean getAdminAuth(){
		return RoleAuth.hasAdminAuth();
	}

	/**
	 * input页面提交用户拥有的角色.
	 */
	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
	}

	/**
	 * input页面提交用户拥有的角色.
	 */
	public void setCheckedOrgId(Long checkedOrgId) {
		if (checkedOrgId != null && checkedOrgId > 0L && RoleAuth.hasAdminAuth()){
			this.checkedOrgId = checkedOrgId;
		}else{
			this.checkedOrgId = RoleAuth.getOrgId();
		}
	}
	
	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	@Autowired
	public void setOrganizationManager(OrganizationManager organizationManager) {
		this.organizationManager = organizationManager;
	}

}
