package com.heracles.framework.web.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.entity.account.Menu;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.service.account.AccountManager;
import com.heracles.framework.service.account.MenuManager;
import com.heracles.framework.service.account.NavigationDispatch;

import com.heracles.framework.tools.FormatJson;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.CrudActionSupport;
import com.heracles.framework.web.value.NavigationMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 菜单管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author yinzj
 */
//定义URL映射对应/system/menu.action
@Namespace("/system")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "menu.action", type = "redirect") })
public class MenuAction extends CrudActionSupport<Menu> {

	private static final long serialVersionUID = 6984748474990880603L;
	
	private MenuManager menuManager;
	private AccountManager accountManager;

	//-- 页面属性 --//
	private Long id;
	private Long parentId;
	private Menu entity;
	private Page<Menu> page = new Page<Menu>(size);
	private List<Long> checkedRoleIds; //页面中钩选的角色id列表

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}

	public Menu getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = menuManager.getMenu(id);
		} else {
			entity = new Menu();
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
		page = menuManager.searchMenu(page, accountManager.getAllRoleToMap(), filters);
		return SUCCESS;
	}

	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String save() throws Exception {
		entity.setRole(listToString(checkedRoleIds));
		menuManager.saveMenu(entity);
		addActionMessage(Language.getMessage("MenuAction.save.success"));
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		if (Unit.isNotNull(entity.getRole())){
			checkedRoleIds = StringsToList(entity.getRole().split(","));
		}
		return INPUT;
	}
	
	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String delete() throws Exception {
		try {
			menuManager.deleteMenu(id);
			addActionMessage(Language.getMessage("MenuAction.delete.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("MenuAction.delete.fail"));
		}
		return RELOAD;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return accountManager.getAllRole();
	}
	
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<Menu> getPage() {
		return page;
	}

	/**
	 * input页面显示用户拥有的角色.
	 */
	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	/**
	 * input页面提交用户拥有的角色.
	 */
	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
	}

	public String getRootMenu(){
		List<Menu> menuList = menuManager.getRootMenu();
		String beanString = FormatJson.listToJson(menuList);
		Struts2Utils.renderText("{\"parentMenu\":"+beanString+"}");
		return null;
	}
	
	//根据输入的上级取得下线组织机构
	public String getChildMenu(){
		List<Menu> menuList = menuManager.getChildMenu(this.parentId);
		String beanString = FormatJson.listToJson(menuList);
		Struts2Utils.renderText("{\"childMenu\":"+beanString+"}");
		return null;		
	}
	
	public String getNavigationMenu(){
		List<NavigationMenu> resultList = new ArrayList<NavigationMenu>();
		if (id != null && id > 0){
			Menu menu = NavigationDispatch.getMenu(id);
			NavigationMenu navMenu = new NavigationMenu();
			navMenu.setName(menu.getName());
			navMenu.setPath(menu.getPath());
			navMenu.setTarget(menu.getTarget());
			resultList.add(navMenu);
			NavigationDispatch.retrospect(resultList, menu.getParent());
		}
		String beanString = FormatJson.listToJson(resultList);
		Struts2Utils.renderText("{\"navigationMenu\":"+beanString+"}");
		return null;
	}
	
	@Autowired
	public void setMenuManager(MenuManager menuManager){
		this.menuManager = menuManager;
	}
	
	@Autowired
	public void setAccountManager(AccountManager accountManager){
		this.accountManager = accountManager;
	}

	private List<Long> StringsToList(String[] s){
		List<Long> resultList = null; 
		if (s != null && s.length > 0){
			resultList = new ArrayList<Long>();
			for (int i = 0; i < s.length; i++){
				resultList.add(Long.valueOf(s[i]));
			}
		}
		return resultList;
	}

	private String listToString(List<Long> list){
		StringBuffer sb = new StringBuffer();
		int n = list.size();
		if (list != null && n > 0){
			for (int i = 0; i < n; i++){
				sb.append(list.get(i));
				if (i < n - 1) sb.append(","); 
			}
		} 
		return sb.toString();
	}

}
