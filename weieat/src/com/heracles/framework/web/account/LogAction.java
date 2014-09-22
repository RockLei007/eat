package com.heracles.framework.web.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.framework.entity.account.OperationLog;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.service.account.OperationLogManager;
import com.heracles.framework.web.CrudActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 日志查询Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 
 * @author yinzj
 */
//定义URL映射对应/system/log.action
@Namespace("/system")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "log.action", type = "redirect") })
public class LogAction extends CrudActionSupport<OperationLog> {

	private static final long serialVersionUID = -9096330811463499985L;

	private OperationLogManager operationLogManager;

	//-- 页面属性 --//
	private Long id;
	private OperationLog entity;
	private Page<OperationLog> page = new Page<OperationLog>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	public OperationLog getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = operationLogManager.getOperationLog(id);
		} else {
			entity = new OperationLog();
		}
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
			page = operationLogManager.searchLog(page, filters);
		}else{
			page = operationLogManager.searchLog(page, filters, getUserDetails().getOrganization().getId());
		}
		
		return SUCCESS;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}
	
	@Override
	public String delete() throws Exception {
		return null;
	}

	public Page<OperationLog> getPage() {
		return page;
	}

	@Autowired
	public void setOperationLogManager(OperationLogManager logManager){
		this.operationLogManager = logManager;
	}



}
