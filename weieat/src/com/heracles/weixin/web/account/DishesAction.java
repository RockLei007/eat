package com.heracles.weixin.web.account;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.convention.annotation.InterceptorRef;

import com.heracles.eat.entity.account.EatTable;
import com.heracles.eat.entity.account.Food;
import com.heracles.eat.entity.account.OrderDishes;
import com.heracles.eat.helper.Cart;
import com.heracles.eat.helper.Categories;
import com.heracles.eat.helper.Order;
import com.heracles.eat.service.account.FoodManager;
import com.heracles.eat.service.account.OrderDishesManager;
import com.heracles.eat.service.account.TableManager;

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
@Namespace("/wx")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "dishes.action", type = "redirect")})
@InterceptorRefs({@InterceptorRef(value="token",params={"includeMethods","save,confirm,remove"}),@InterceptorRef("defaultStack") })
public class DishesAction extends CrudActionSupport<Order> {

	private static final long serialVersionUID = 2010437774401753864L;
	
	private OrderDishesManager orderDishesManager;
	private TableManager tableManager;
	private FoodManager foodManager;
	private Order entity;
	private Page<Order> page = new Page<Order>(size);
	private Page<Food> foodPage = new Page<Food>(size);
	private Food food;

	//-- 页面属性 --//
	private List<Order> orderList;
	private String tableId;
	private String weixinId;
	private String foodId;
	private Double number;
	private Double price;
	private String name;
	private String orgId;
	private String checkedTableIds;
	private String identitys;

	//-- ModelDriven 与 Preparable函数 --//
	public void setCheckedTableIds(String checkedTableIds){
		this.checkedTableIds = checkedTableIds;
	}

	public String getCheckedTableIds(){
		if (Unit.isNotNull(this.checkedTableIds)){
			return this.checkedTableIds;
		}else
			return (String)getSession().getAttribute("checkedTableIds");
	}
	
	public void setIdentitys(String identitys){
		this.identitys = identitys;
	}
	
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	
	public String getOrgId(){
		if (Unit.isNotNull(this.orgId)){
			return this.orgId; 
		}else
			return (String)getSession().getAttribute("orgId");
	}
	
	public List<EatTable> getTableList(){
		return tableManager.getTableByOrgId(Long.valueOf(getOrgId()));
	}
	
	public Order getModel() {
		return entity;
	}
	
	public List<Order> getOrderList(){
		if (Unit.isNotNull(getCheckedTableIds())){
			Long tableId = Long.valueOf(getCheckedTableIds().split(",")[0]);
			this.orderList = Cart.getOrderListFormCart(tableId);
		}
		return orderList;
	}
	
	public String getTableNames(){
		StringBuffer sb = new StringBuffer();
		if (Unit.isNotNull(getCheckedTableIds())){
			String[] tables = getCheckedTableIds().split(",");
			for(String s : tables){
				EatTable table = tableManager.getEatTable(Long.valueOf(s));
				sb.append(table.getName()+";");
			}
		}
		return sb.toString();
	}
	
	public void setTableId(String tableId){
		this.tableId = tableId;
	}
	
	public String getTableId(){
		return this.tableId;
	}
	
	public void setWeixinId(String weixinId){
		this.weixinId = weixinId;
	}
	
	public String getWeixinId(){
		if (Unit.isNotNull(this.weixinId)){
			return this.weixinId;
		}else
			return (String)getSession().getAttribute("weixinId");
	}

	public void setFoodId(String foodId){
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

	@Override
	public String list() throws Exception {
		return null;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		
	}

	//-- CRUD Action 函数 --//
	@Override
	public String input() throws Exception {
		if (Unit.isNotNull(this.orgId) && Unit.isNotNull(this.weixinId)){
			getSession().setAttribute("orgId", this.orgId);
			getSession().setAttribute("weixinId", this.weixinId);
		}else
			addActionMessage("您访问的页面有错误！");
		return INPUT;
	}
	
	@Override
	public String delete() throws Exception {
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		add();
		return "order";
	}
	
	public String addToMenu() throws Exception {
		add();
		return "menu";
	}

	public String menu() throws Exception {
		if (Unit.isNotNull(getCheckedTableIds())){
			String[] tableIds = getCheckedTableIds().split(",");
			for(int i = 0; i < tableIds.length; i++){
				this.tableId = tableIds[i];
			}
		}
		return "menu";
	}
	
	public int getCount(){
		if (Unit.isNotNull(this.tableId)){
			if (Cart.getFoodCount(Long.valueOf(this.tableId)) > 0){
				return Cart.getFoodCount(Long.valueOf(this.tableId));
			}else{
				OrderDishes dishes = orderDishesManager.getOrderDishesByTableId(Long.valueOf(getOrgId()), Long.valueOf(this.tableId));
				int i = mapListToOrderList(ReadJson.readJson2List(dishes.getFoodArray())).size();
				if (i > 0){
					return i;
				}else
					return 0;
			}
		}else
			return 0;
	}
	
	public String getSum(){
		if (!Unit.isNotNull(this.tableId)) return "0";
		Double sum = Cart.getTotalFormCart(Long.valueOf(this.tableId));
		if (sum > 0){
			return String.valueOf(sum);
		}else{
			return "0";
		}
	}
	
	public String order() throws Exception {
		if (Unit.isNotNull(getOrgId())){
			if (Unit.isNotNull(getCheckedTableIds())){
				this.tableId = getCheckedTableIds().split(",")[0];
			}
		}
		return "order";
	}
	
	public String confirm() throws Exception {
		if (Unit.isNotNull(getCheckedTableIds())){
			String[] tableIds = getCheckedTableIds().split(",");
			if (Cart.getFoodCount(Long.valueOf(tableIds[0])) == 0){
				addActionMessage("您还没有点菜！");
				return "order";
			}
			for(int i = 0; i < tableIds.length; i++){
				Long tableId = Long.valueOf(tableIds[i]);
				OrderDishes dishes = new OrderDishes();
				dishes.setDatetime(Datetime.LongFormat(Cart.getDatetimeFormCart(tableId)));
				dishes.setOrgId(Long.valueOf(getOrgId()));
				dishes.setEatTable(tableManager.getEatTable(tableId));
				dishes.setMoney(Cart.getTotalFormCart(tableId));
				dishes.setState(OrderDishes.NEW_STATE);
				dishes.setType(OrderDishes.NOW_TYPE);
				dishes.setFoodArray(FormatJson.listToJson(Cart.getOrderListFormCart(tableId)));
				orderDishesManager.save(dishes);
				Cart.removeCart(tableId);
			}
		}
		addActionMessage("您的点菜已经提交，在已点菜中可查看您的点菜！");
		return "menu";
	}
	
	public String check() throws Exception {
		if (Unit.isNotNull(checkedTableIds) && Unit.isNotNull(identitys)){
			String[] tableIds = getCheckedTableIds().split(",");
			String[] idents = identitys.split(",");
			if (tableIds.length != idents.length){
				addActionMessage("您输入的验证码少于您选择的餐桌号！");
				return INPUT;
			}else{
				boolean result = false;
				for(int i = 0; i < tableIds.length; i++){
					Long tableId = Long.valueOf(tableIds[i]);
					this.tableId = String.valueOf(tableId);
					EatTable table = tableManager.getEatTable(tableId);
					if (table.getIdentity().equals(idents[i])){
						result = true;
					}else{
						addActionMessage("您输入的验证码不正确！");
					}
				}
				if (result) {
					getSession().setAttribute("checkedTableIds", this.checkedTableIds);
					return "order";
				}else
					return INPUT;
			}
		}else{
			addActionMessage("请选择您就餐的餐桌号及该餐桌的验证码！");
			return INPUT;
		}
	}

	public String remove() throws Exception {
		subtract();
		return "order";
	}
	
	public String removeToMenu() throws Exception {
		subtract();
		return "menu";
	}
	
	
	public String foodDetail() throws Exception {
		this.food = foodManager.getFood(Long.valueOf(this.foodId.trim()));
		return "food";
	}
	
	public Food getFood(){
		return this.food;
	}
	
	public List<Order> getCartOrder(){
		return Cart.getOrderListFormCart(Long.valueOf(this.tableId));
	}
	
	public String view(){
		if (Unit.isNotNull(getCheckedTableIds())){
			String[] tableIds = getCheckedTableIds().split(",");
			for(int i = 0; i < tableIds.length; i++){
				this.tableId = tableIds[i];
			}
		}
		return "view";
	}
	
	public List<OrderDishes> getDishesList(){
		List<OrderDishes> dishesList = orderDishesManager.getDishesByTableId(Long.valueOf(getOrgId()), Long.valueOf(getTableId()), getWeixinId());
		if (Unit.isNotNull(dishesList)){
			for (OrderDishes dishes : dishesList){
				List<Order> orderList = mapListToOrderList(ReadJson.readJson2List(dishes.getFoodArray())); 
				dishes.setFoodList(orderList); 
			}
		}
		return dishesList;
	}
	
	//加菜
	/*public String add() throws Exception {
		try {
			OrderDishes dishes = orderDishesManager.getOrderDishesByTableId(Long.valueOf(orgId), Long.valueOf(tableId));
			List<Order> list = mapListToOrderList(ReadJson.readJson2List(dishes.getFoodArray()));
			Food food = foodManager.getFood(Long.valueOf(foodId));	
			if (food != null){
				list = Cart.addList(list, addOrder(food));
			}
			dishes.setFoodArray(FormatJson.listToJson(list));
			orderDishesManager.change(dishes, OrderDishes.ADD_STATE);
			addActionMessage(Language.getMessage("DishesAction.add.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("DishesAction.add.fail"));
		}
		return RELOAD;
	}*/
	
	//-- 页面属性访问函数 --//
	public Page<Order> getPage() {
		return page;
	}
	
	public Page<Food> getFoodPage() {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		if (getSession().getAttribute("filters") != null && filters.size() == 0){
			filters = (List<PropertyFilter>) getSession().getAttribute("filters");
		}else
			getSession().setAttribute("filters", filters);
		if (filters.size() > 0){
			if (filters.get(0).getMatchValue().toString().equals("all")){
				filters.remove(0);
			}
		}
		return foodManager.searchFood(foodPage, filters, Long.valueOf(getOrgId()));
	}
	
	public List<String> getFoodCate(){
		return Categories.getFoodCate();
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
	public void setFoodManager(FoodManager foodManager) {
		this.foodManager = foodManager;
	}	
	
	private Order addOrder(Food food){
		Order order = new Order();
		order.setFoodId(food.getId());
		order.setName(food.getName());
		order.setNumber(this.number);
		order.setPrice(food.getPrice());
		order.setCategories(food.getCategories());
		order.setWeixinId(getWeixinId());
		order.setDatetime(System.currentTimeMillis());
		return order;
	}
	
	private Order mapToObject(Map<String,Object> map){
		Order order = null;
		if (map != null && map.size() > 0){
			order = new Order();
			order.setName((String)map.get("name"));
			order.setNumber((Double)map.get("number"));
			order.setPrice((Double)map.get("price"));
			order.setWeixinId((String)map.get("weixinId"));
			order.setDatetime((Long)map.get("datetime"));
			order.setFoodId(Long.valueOf(map.get("foodId").toString().trim()));
			order.setCategories((String)map.get("categories"));
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
	
	private void add(){
		String foodName = "";
		this.number = 1d;
		if (Unit.isNotNull(getCheckedTableIds())){
			String[] tableIds = getCheckedTableIds().split(",");
			Food food = foodManager.getFood(Long.valueOf(foodId));	
			if (food != null){
				for(int i = 0; i < tableIds.length; i++){
					Cart.addCart(Long.valueOf(tableIds[i]), addOrder(food));
					this.tableId = tableIds[i];
				}
				foodName = food.getName();
			}
		}
		foodPage = foodManager.getFoodGroupByCate(foodPage, Long.valueOf(getOrgId()));
		addActionMessage("您选择的"+foodName+"，已经加入到点菜单中！");
	}
	
	private void subtract(){
		number = 1d;
		if (Unit.isNotNull(getCheckedTableIds())){
			String[] tableIds = getCheckedTableIds().split(",");
			for(int i = 0; i < tableIds.length; i++){
				Long tableId = Long.valueOf(tableIds[i]);
				Food food = foodManager.getFood(Long.valueOf(foodId));	
				this.tableId = String.valueOf(tableId);
				addActionMessage(Cart.removeOrder(tableId, food.getId(), food.getType(), number));
			}
		}
	}
	
	
}
