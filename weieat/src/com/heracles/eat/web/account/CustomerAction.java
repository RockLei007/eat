package com.heracles.eat.web.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.Customer;
import com.heracles.eat.service.account.CustomerManager;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.web.CrudActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 接收信息管理Action.
 * 
 * 
 * @author 31307@sohu.com
 */
//定义URL映射对应/eat/table.action
@Namespace("/eat")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "customer.action", type = "redirect") })
public class CustomerAction extends CrudActionSupport<Customer> {

	private static final long serialVersionUID = 5213013353567677325L;

	private CustomerManager customerManager;

	//-- 页面属性 --//
	private Long id;
	private Customer entity;
	private Page<Customer> page = new Page<Customer>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public Customer getModel() {
		return entity;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = customerManager.getCustomer(id);
		} else {
			entity = new Customer();
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
			page = customerManager.searchCustomer(page, filters);
		}else{
			Long orgId = getUserDetails().getOrganization().getId();
			if (orgId != null){
				page = customerManager.searchCustomer(page, filters, orgId);
			}
		}				
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}
	
	public String black() throws Exception {
		customerManager.setBlack(id);
		return RELOAD;
	}
	
	public String cancelBlack() throws Exception {
		customerManager.cancelBlack(id);
		return RELOAD;
	}
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String save() throws Exception {
		entity.setCreateDate(Datetime.getNow());
		entity.setOrgId(getUserDetails().getOrganization().getId());
		customerManager.save(entity);
		return RELOAD;
	}
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<Customer> getPage() {
		return page;
	}

	@Autowired
	public void setCustomerManager(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}

	

	
}
