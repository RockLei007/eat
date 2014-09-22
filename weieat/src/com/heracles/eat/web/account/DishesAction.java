package com.heracles.eat.web.account;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.OrderDishes;
import com.heracles.eat.entity.account.TableReserve;
import com.heracles.eat.helper.Cart;
import com.heracles.eat.helper.Order;
import com.heracles.eat.service.account.OrderDishesManager;
import com.heracles.eat.service.account.TableManager;
import com.heracles.eat.service.account.TableReserveManager;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.FormatJson;
import com.heracles.framework.tools.ReadJson;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.CrudActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 菜品管理Action.
 * 
 * 
 * 
 * @author 31307@sohu.com
 */
//定义URL映射对应/eat/dishes.action
@Namespace("/eat")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "dishes.action", type = "redirect") })
public class DishesAction extends CrudActionSupport<OrderDishes> {

	private static final long serialVersionUID = -5440676009005560489L;
	
	private OrderDishesManager orderDishesManager;
	private TableManager tableManager;
	private TableReserveManager reserveManager;
	private OrderDishes entity;
	private Page<OrderDishes> page = new Page<OrderDishes>(size);

	//-- 页面属性 --//
	private List<Order> orderList;
	private Long tableId;
	private String winxinId;
	private Long foodId;
	private Double number;
	private Double price;
	private String name;
	private int type;
	private Long orgId;
	private Long id;

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	public OrderDishes getModel() {
		return entity;
	}
	
	public List<Order> getOrderList(){
		return orderList;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void setTableId(Long tableId){
		this.tableId = tableId;
	}
	
	public Long getTableId(){
		return this.tableId;
	}
	
	public void setWinxinId(String winxinId){
		this.winxinId = winxinId;
	}
	
	public String getWinxinId(){
		return this.winxinId;
	}

	public void setFoodId(Long foodId){
		this.foodId = foodId;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = orderDishesManager.getOrderDishes(id);
		} else {
			entity = new OrderDishes();
		}
	}

	//-- CRUD Action 函数 --//
	//从菜单中删除某一个菜
	
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("datetime");
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
			page = orderDishesManager.searchOrderDishes(page, filters);
		}else{
			Long orgId = getUserDetails().getOrganization().getId();
			if (orgId != null){
				page = orderDishesManager.searchOrderDishes(page, filters, orgId);
			}
		}
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String delete() throws Exception {
		try {
			OrderDishes order = orderDishesManager.getOrderDishes(id);
			if (order.getState() == OrderDishes.CANCEL_STATE){
				orderDishesManager.delete(id);
				addActionMessage(Language.getMessage("DishesAction.delete.success"));
			}else
				addActionMessage(Language.getMessage("DishesAction.delete.disable"));
		}catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("DishesAction.delete.fail"));
		}
		return RELOAD;
	}

	@Override
	@RecordOperation(fieldId = "id", filedName = "name")
	public String save() throws Exception {
		try {
			entity.setDatetime(Datetime.LongFormat(Cart.getDatetimeFormCart(tableId)));
			entity.setOrgId(orgId);
			entity.setEatTable(tableManager.getEatTable(tableId));
			entity.setMoney(Cart.getTotalFormCart(tableId));
			entity.setState(OrderDishes.NEW_STATE);
			entity.setType(type);
			entity.setFoodArray(FormatJson.listToJson(Cart.getOrderListFormCart(tableId)));
			orderDishesManager.save(entity);
			addActionMessage(Language.getMessage("DishesAction.save.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("DishesAction.save.fail"));
		}
		return RELOAD;
	}

	public String cancel() throws Exception {
		try {
			OrderDishes dishes = orderDishesManager.getOrderDishes(id);
			orderDishesManager.change(dishes, OrderDishes.CANCEL_STATE);
			addActionMessage(Language.getMessage("DishesAction.cancel.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("DishesAction.cancel.fail"));
		}
		return RELOAD;
	}
	
	public String verify() throws Exception {
		try {
			OrderDishes dishes = orderDishesManager.getOrderDishes(id);
			orderDishesManager.change(dishes, OrderDishes.VERIFY_STATE);
			addActionMessage(Language.getMessage("DishesAction.verify.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("DishesAction.verify.fail"));
		}
		return RELOAD;
	}

	public String finish() throws Exception {
		try {
			OrderDishes dishes = orderDishesManager.getOrderDishes(id);
			orderDishesManager.change(dishes, OrderDishes.FINISH_STATE);
			if (dishes.getType() == OrderDishes.ORDER_TYPE && dishes.getReserveId() != null){
				TableReserve reserve = reserveManager.getTableReserve(dishes.getReserveId());
				reserveManager.change(reserve, TableReserve.FINISH_STATE);
			}
			addActionMessage(Language.getMessage("DishesAction.finish.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("DishesAction.finish.fail"));
		}
		return RELOAD;
	}

	//加菜
	public String addSave() throws Exception {
		try {
			OrderDishes dishes = orderDishesManager.getOrderDishesByTableId(orgId, tableId);
			List<Order> list = mapListToOrderList(ReadJson.readJson2List(dishes.getFoodArray()));
			list = Cart.addList(list, addOrder());
			dishes.setFoodArray(FormatJson.listToJson(list));
			orderDishesManager.change(dishes, OrderDishes.ADD_STATE);
			addActionMessage(Language.getMessage("DishesAction.add.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("DishesAction.add.fail"));
		}
		return RELOAD;
	}
	
	//
	public String getFoodArray() throws Exception {
		OrderDishes dishes = orderDishesManager.getOrderDishes(this.id);
		Struts2Utils.renderText("{\"foodArray\":"+dishes.getFoodArray()+"}");
		return null;
	}
	
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<OrderDishes> getPage() {
		return page;
	}

	@Autowired
	public void setOrderDishesManager(OrderDishesManager orderDishesManager) {
		this.orderDishesManager = orderDishesManager;
	}

	@Autowired
	public void setTableManager(TableManager tableManager) {
		this.tableManager = tableManager;
	}

	@Autowired
	public void setTableReserveManager(TableReserveManager reserveManager) {
		this.reserveManager = reserveManager;
	}	
	
	private Order addOrder(){
		Order order = new Order();
		order.setFoodId(this.foodId);
		order.setName(this.name);
		order.setNumber(this.number);
		order.setPrice(this.price);
		order.setWeixinId(this.winxinId);
		return order;
	}
	
	private Order mapToObject(Map<String,Object> map){
		Order order = null;
		if (map != null && map.size() > 0){
			order = new Order();
			order.setFoodId((Long) map.get("foodId"));
			order.setName((String)map.get("name"));
			order.setNumber((Double)map.get("number"));
			order.setPrice((Double)map.get("price"));
			order.setWeixinId((String)map.get("winxinId"));
			order.setDatetime((Long)map.get("datetime"));
		}
		return order;
	}

	private List<Order> mapListToOrderList(List<LinkedHashMap<String,Object>> list){
		List<Order> orderList = new ArrayList<Order>();
		if (Unit.isNotNull(list)){
			for (Map<String,Object> map : list){
				orderList.add(mapToObject(map));
			}	
		}
		return orderList;
	}
	
	
	
}
