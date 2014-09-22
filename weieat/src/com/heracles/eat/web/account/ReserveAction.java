package com.heracles.eat.web.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.EatTable;
import com.heracles.eat.entity.account.TableReserve;
import com.heracles.eat.service.account.TableManager;
import com.heracles.eat.service.account.TableReserveManager;

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
 * 餐桌包厢预订管理Action.
 * 
 * 
 * @author 31307@sohu.com
 */
//定义URL映射对应/eat/table.action
@Namespace("/eat")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "reserve.action", type = "redirect") })
public class ReserveAction extends CrudActionSupport<TableReserve> {

	private static final long serialVersionUID = 5213013353567677325L;

	private TableReserveManager tableReserveManager;
	private TableManager tableManager;
	
	private Long id;

	//-- 页面属性 --//

	private TableReserve entity;
	private Page<TableReserve> page = new Page<TableReserve>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public TableReserve getModel() {
		return entity;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		boolean hasAdminAuth = false;
		List<Role> roles = getUserDetails().getUserRoles();
		for (Role role : roles) {
			if (role.getId().equals(1L)){
				hasAdminAuth = true;
			}
		}
		if (hasAdminAuth){
			page = tableReserveManager.searchTableReserve(page, filters);
		}else{
			Long orgId = getUserDetails().getOrganization().getId();
			if (orgId != null){
				page = tableReserveManager.searchTableReserve(page, filters, orgId);
			}
		}	
		return SUCCESS;
	}

	public List<EatTable> getAllTable(){
		Long orgId = getUserDetails().getOrganization().getId();
		return tableManager.getTableByOrgId(orgId);
	}
	
	@Override
	public String input() throws Exception {

		return INPUT;
	}
	
	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String delete() throws Exception {
		try {
			TableReserve reserve = tableReserveManager.getTableReserve(id);
			if (reserve.getState() == TableReserve.CANCEL_STATE){
				tableReserveManager.delete(id);
				addActionMessage(Language.getMessage("ReserveAction.delete.success"));
			}else
				addActionMessage(Language.getMessage("ReserveAction.delete.disable"));
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("ReserveAction.delete.fail"));
		}
		return RELOAD;
	}
	
	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return RELOAD;
	}
	
	public String cancel() throws Exception {
		try {
			TableReserve reserve = tableReserveManager.getTableReserve(id);
			tableReserveManager.change(reserve, TableReserve.CANCEL_STATE);
			addActionMessage(Language.getMessage("ReserveAction.cancel.success"));
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("ReserveAction.cancel.fail"));
		}
		return RELOAD;
	}
	
	public String verify() throws Exception {
		try {
			TableReserve reserve = tableReserveManager.getTableReserve(id);
			tableReserveManager.change(reserve, TableReserve.VERIFY_STATE);
			addActionMessage(Language.getMessage("ReserveAction.verify.success"));
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("ReserveAction.verify.fail"));
		}
		return RELOAD;
	}
	
	public String finish() throws Exception {
		try {
			TableReserve reserve = tableReserveManager.getTableReserve(id);
			tableReserveManager.change(reserve, TableReserve.FINISH_STATE);
			addActionMessage(Language.getMessage("ReserveAction.finish.success"));
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("ReserveAction.finish.fail"));
		}
		return RELOAD;
	}
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<TableReserve> getPage() {
		return page;
	}

	@Autowired
	public void setTableReserveManager(TableReserveManager tableReserveManager) {
		this.tableReserveManager = tableReserveManager;
	}

	@Autowired
	public void setTableManager(TableManager tableManager) {
		this.tableManager = tableManager;
	}	

	
}
