package com.heracles.eat.web.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.EatTable;
import com.heracles.eat.helper.Categories;
import com.heracles.eat.service.account.TableManager;
import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.entity.account.Role;

import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.web.CrudActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 餐桌包厢管理Action.
 * 
 * 
 * @author 31307@sohu.com
 */
//定义URL映射对应/eat/table.action
@Namespace("/eat")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "table.action", type = "redirect") })
public class TableAction extends CrudActionSupport<EatTable> {

	private static final long serialVersionUID = -5440676009005560489L;
	
	private TableManager tableManager;

	//-- 页面属性 --//
	private Long id;
	private EatTable entity;
	private Page<EatTable> page = new Page<EatTable>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	public EatTable getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = tableManager.getEatTable(id);
		} else {
			entity = new EatTable();
			
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
			page = tableManager.searchEatTable(page, filters);
		}else{
			Long orgId = getUserDetails().getOrganization().getId();
			if (orgId != null){
				page = tableManager.searchEatTable(page, filters, orgId);
			}
		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}
	
	public List<String> getTableCate(){
		return Categories.getTableCate();
	}

	public List<String> getTableType(){
		return Categories.getTableType();
	}
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String save() throws Exception {
		try {
			if (id == null){
				entity.setOrgId(getUserDetails().getOrganization().getId());
			}
			tableManager.save(entity);
			addActionMessage(Language.getMessage("TableAction.save.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("TableAction.save.fail"));
		}
		return RELOAD;
	}

	public String disable() throws Exception {
		try {
			tableManager.disable(id);
			addActionMessage(Language.getMessage("TableAction.disable.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("TableAction.disable.fail"));
		}
		return RELOAD;
	}

	public String enable(){
		try {
			tableManager.enable(id);
			addActionMessage(Language.getMessage("TableAction.enable.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("TableAction.enable.fail"));
		}
		return RELOAD;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<EatTable> getPage() {
		return page;
	}

	@Autowired
	public void setTableManager(TableManager tableManager) {
		this.tableManager = tableManager;
	}

	
}
