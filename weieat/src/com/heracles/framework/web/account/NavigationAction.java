package com.heracles.framework.web.account;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.heracles.eat.service.account.FoodManager;
import com.heracles.eat.service.account.OrderDishesManager;
import com.heracles.eat.service.account.TableManager;
import com.heracles.eat.service.account.TableReserveManager;
import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.entity.account.User;
import com.heracles.framework.service.account.AccountManager;
import com.heracles.framework.web.CrudActionSupport;
import com.heracles.framework.web.RoleAuth;



/**
 * 用户注册、忘记密码、修改密码管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author yinzj
 */
//定义URL映射对应/register.action
@Namespace("/")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "navigation.action", type = "redirect") })
public class NavigationAction extends CrudActionSupport<User> {
	
	private static final long serialVersionUID = 6376098937792347819L;

	private User entity;
	private TableReserveManager reserveManager;
	private OrderDishesManager dishesManager;
	private AccountManager accountManager;
	private FoodManager foodManager;
	private TableManager tableManager;
	
	private Long orgId;
	
	public String getRegisterOrg() throws Exception{
		Long userId = getUserDetails().getUserInfo().getId();
		User user = accountManager.getUser(userId);
		Organization org = user.getOrganization();
		this.orgId = org.getId();
		if (orgId.equals(2L)){
			return "0";
		}
		return "1";
	}
	
	public String getOrgId(){
		return String.valueOf(orgId);
	}

	public String getFoodCount() throws Exception{
		return String.valueOf(foodManager.getFoodCount(RoleAuth.getOrgId()));
	}
	
	public String getTableCount() throws Exception{
		return String.valueOf(tableManager.getFoodCount(RoleAuth.getOrgId()));
	}
	
	public String getNewReserveCount() throws Exception{
		return String.valueOf(reserveManager.getNewReserveCount(RoleAuth.getOrgId()));
	}
	
	public String getNewDishesCount() throws Exception{
		return String.valueOf(dishesManager.getNewDishesCount(RoleAuth.getOrgId()));
	}
	
	public String getAddDishesCount() throws Exception{
		return String.valueOf(dishesManager.getAddDishesCount(RoleAuth.getOrgId()));
	}
	
	@Override
	public User getModel() {
		return entity;
	}
	
	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public void setTableReserveManager(TableReserveManager tableReserveManager) {
		this.reserveManager = tableReserveManager;
	}
	
	@Autowired
	public void setOrderDishesManager(OrderDishesManager orderDishesManager) {
		this.dishesManager = orderDishesManager;
	}
	
	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@Autowired
	public void setFoodManager(FoodManager foodManager) {
		this.foodManager = foodManager;
	}

	@Autowired
	public void setTableManager(TableManager tableManager) {
		this.tableManager = tableManager;
	}
	
}
