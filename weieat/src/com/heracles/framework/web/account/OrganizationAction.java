package com.heracles.framework.web.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.entity.account.User;

import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.service.account.AccountManager;
import com.heracles.framework.service.account.OrganizationManager;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.FormatJson;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.CrudActionSupport;

/**
 * 组织机构管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author yinzj
 */
//定义URL映射对应/system/org.action
@Namespace("/system")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "organization.action", type = "redirect") })
public class OrganizationAction extends CrudActionSupport<Organization> {

	private static final long serialVersionUID = -2830847303480955669L;
	
	private Long id;
	private Long parentId;
	private String orgName;
	private Organization entity;
	private OrganizationManager organizationManager;
	private AccountManager accountManager;
	private Page<Organization> page = new Page<Organization>(size);

	public void setId(Long id) {
		this.id = id;
	}

	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	
	public void setName(String orgName){
		this.orgName = orgName;
	}
	
	@Override
	public Organization getModel() {
		if (entity == null){
			entity = organizationManager.getOrg(id);
		}
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = organizationManager.getOrg(id);
		} else {
			entity = new Organization();
		}
		
	}

	public Page<Organization> getPage() {
		return page;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = organizationManager.searchUser(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String save() throws Exception {
		if (entity.getParent() == null || entity.getParent() < 0){ 
			entity.setParent(0L);
		}
		if (this.id == null){
			String orgName = entity.getName();
			List<Organization> orgList = organizationManager.getOrgByName(orgName);
			if (orgList != null && orgList.size() > 0){
				addActionMessage(Language.getMessage("OrganizationAction.save.fail"));
			}else{
				addActionMessage(Language.getMessage("OrganizationAction.save.success"));
				entity.setCreateDate(Datetime.getNow());
				organizationManager.save(entity);
			}
			if (getUserDetails().getOrganization().getId().equals(2L)){
				orgList = organizationManager.getOrgByName(orgName);
				if (Unit.isNotNull(orgList)){
					User user = accountManager.getUser(getUserDetails().getUserInfo().getId());
					user.setOrganization(orgList.get(0));
					accountManager.saveUser(user);
					Struts2Utils.getResponse().sendRedirect("/navigation.action");
					return null;
				}
			}
		}else{
			addActionMessage(Language.getMessage("OrganizationAction.save.success"));
			organizationManager.save(entity);
		}
		return RELOAD;
	}
	
	public String add() throws Exception {
		return "add";
	}	

	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String delete() throws Exception {
		try {
			organizationManager.delete(id);
			addActionMessage(Language.getMessage("OrganizationAction.delete.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("OrganizationAction.delete.fail"));
		}
		return RELOAD;
	}
	
	@RecordOperation(fieldId = "id", filedName = "name")
	public String active() throws Exception {
		try {
			organizationManager.active(id);
			addActionMessage(Language.getMessage("OrganizationAction.active.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("OrganizationAction.active.fail"));
		}
		return RELOAD;
	}

	public String getRootOrg() throws Exception {
		List<Organization> orgList = organizationManager.getRootOrg();
		String beanString = FormatJson.listToJson(orgList);
		Struts2Utils.renderText("{\"parentOrg\":"+beanString+"}");
		return null;
	}
	
	//根据输入的上级取得下线组织机构
	public String getChildOrg() throws Exception {
		List<Organization> orgList = organizationManager.getChildOrg(parentId);
		String beanString = FormatJson.listToJson(orgList);
		Struts2Utils.renderText("{\"childOrg\":"+beanString+"}");
		return null;		
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkOrgName() throws Exception  {
		List<Organization> orgList = organizationManager.getOrgByName(this.orgName); 
		if (orgList != null && orgList.size() > 0) {
			Struts2Utils.renderText("false");
		} else {
			Struts2Utils.renderText("true");
		}
		return null;
	}
	
	@Autowired
	public void setOrganizationManager(OrganizationManager organizationManager){
		this.organizationManager = organizationManager;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager){
		this.accountManager = accountManager;
	}
	
}
