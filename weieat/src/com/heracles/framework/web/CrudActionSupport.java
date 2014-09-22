package com.heracles.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.heracles.framework.service.CustomUserDetails;
import com.heracles.framework.service.Record;
import com.heracles.framework.service.account.OperationLogManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


/**
 * Struts2中典型CRUD Action的抽象基类.
 * 
 * 主要定义了对Preparable,ModelDriven接口的使用,以及CRUD函数和返回值的命名.
 *
 * @param <T> CRUDAction所管理的对象类型.
 * 
 * @author calvin
 */
@Component
public abstract class CrudActionSupport<T> extends ActionSupport implements ModelDriven<T>, Preparable, PageSize {

	private static final long serialVersionUID = -1653204626115064950L;
	
	protected OperationLogManager operationLogManager;
	
	/** 进行增删改操作后,以redirect方式重新打开action默认页的result名.*/
	public static final String RELOAD = "reload";

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected Record record = Record.getInstance();

	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	public String execute() throws Exception {
		return list();
	}

	//-- CRUD Action函数 --//
	/**
	 * Action函数,显示Entity列表界面.
	 * 建议return SUCCESS.
	 */
	public abstract String list() throws Exception;

	/**
	 * Action函数,显示新增或修改Entity界面.
	 * 建议return INPUT.
	 */
	@Override
	public abstract String input() throws Exception;

	/**
	 * Action函数,新增或修改Entity. 
	 * 建议return RELOAD.
	 */
	public abstract String save() throws Exception;

	/**
	 * Action函数,删除Entity.
	 * 建议return RELOAD.
	 */
	public abstract String delete() throws Exception;

	//-- Preparable函数 --//
	/**
	 * 实现空的prepare()函数,屏蔽了所有Action函数都会执行的公共的二次绑定.
	 */
	public void prepare() throws Exception {
			record.setManager(operationLogManager);
			record.execute(this.getClass(), getRequest());
	}
	
	/**
	 * 定义在input()前执行二次绑定.
	 */
	public void prepareInput() throws Exception {
		prepareModel();
	}

	/**
	 * 注入操作记录类. 
	*/ 
	@Autowired
	public void setOperationLogManager(OperationLogManager operationLogManager){
		this.operationLogManager = operationLogManager;
	}
	
	/**
	 * 定义在save()前执行二次绑定.
	 */
	public void prepareSave() throws Exception {
		prepareModel();
	}

	/**
	 * 等同于prepare()的内部函数,供prepardMethodName()函数调用. 
	 */
	protected abstract void prepareModel() throws Exception;

	protected HttpServletRequest getRequest(){
		return Struts2Utils.getRequest();
	}
	
	protected HttpSession getSession(){
		return Struts2Utils.getRequest().getSession();
	}
	
	protected CustomUserDetails getUserDetails(){
		return (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	
}
