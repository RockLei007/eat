package com.heracles.framework.web.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.dao.HibernateUtils;
import com.heracles.framework.entity.account.Authority;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.service.account.AccountManager;
import com.heracles.framework.web.CrudActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 角色管理Action.
 * 
 * 演示不分页的简单管理界面.
 * 
 * @author calvin
 */
@Namespace("/system")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "role.action", type = "redirect") })
public class RoleAction extends CrudActionSupport<Role> {

	private static final long serialVersionUID = -4052047494894591406L;

	private AccountManager accountManager;

	//-- 页面属性 --//
	private Long id;
	private Role entity;
	private List<Role> allRoleList;//角色列表
	private List<Long> checkedAuthIds;//页面中钩选的权限id列表
	private Page<Role> page = new Page<Role>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public Role getModel() {
		return entity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page<Role> getPage() {
		return page;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = accountManager.getRole(id);
		} else {
			entity = new Role();
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
		page = accountManager.getAllStatusRole(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedAuthIds = entity.getAuthIds();
		return INPUT;
	}

	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String save() throws Exception {
		//根据页面上的checkbox 整合Role的Authorities Set.
		HibernateUtils.mergeByCheckedIds(entity.getAuthorityList(), checkedAuthIds, Authority.class);
		//保存用户并放入成功信息.
		try {
			accountManager.saveRole(entity);
			addActionMessage(Language.getMessage("RoleAction.save.success"));
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("RoleAction.save.fail"));
		}
		return RELOAD;
	}

	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String delete() throws Exception {
		try {
			accountManager.deleteRole(id);
			addActionMessage(Language.getMessage("RoleAction.delete.success"));
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("RoleAction.delete.fail"));
		}
		return RELOAD;
	}

	@RecordOperation(fieldId = "id", filedName = "name")
	public String active() throws Exception {
		try {
			accountManager.activeRole(id);
			addActionMessage(Language.getMessage("RoleAction.active.success"));
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("RoleAction.active.fail"));
		}
		return RELOAD;
	}
	
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return allRoleList;
	}

	/**
	 * input页面显示所有授权列表.
	 */
	public List<Authority> getAllAuthorityList() {
		return accountManager.getAllAuthority();
	}

	/**
	 * input页面显示角色拥有的授权.
	 */
	public List<Long> getCheckedAuthIds() {
		return checkedAuthIds;
	}

	/**
	 * input页面提交角色拥有的授权.
	 */
	public void setCheckedAuthIds(List<Long> checkedAuthIds) {
		this.checkedAuthIds = checkedAuthIds;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}