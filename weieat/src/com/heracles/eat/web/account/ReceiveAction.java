package com.heracles.eat.web.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.ReceiveMessage;
import com.heracles.eat.service.account.ReceiveMessageManager;

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
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "receive.action", type = "redirect") })
public class ReceiveAction extends CrudActionSupport<ReceiveMessage> {

	private static final long serialVersionUID = 5213013353567677325L;

	private ReceiveMessageManager receiveMessageManager;

	//-- 页面属性 --//

	private ReceiveMessage entity;
	private Page<ReceiveMessage> page = new Page<ReceiveMessage>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public ReceiveMessage getModel() {
		return entity;
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
		page = receiveMessageManager.searchReceiveMessage(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<ReceiveMessage> getPage() {
		return page;
	}

	@Autowired
	public void setReceiveMessageManager(ReceiveMessageManager receiveMessageManager) {
		this.receiveMessageManager = receiveMessageManager;
	}

	

	
}
