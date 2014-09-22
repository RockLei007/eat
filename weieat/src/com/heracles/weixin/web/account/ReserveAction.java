package com.heracles.weixin.web.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.Customer;
import com.heracles.eat.entity.account.EatTable;
import com.heracles.eat.entity.account.TableReserve;
import com.heracles.eat.helper.Categories;
import com.heracles.eat.service.account.CustomerManager;
import com.heracles.eat.service.account.TableManager;
import com.heracles.eat.service.account.TableReserveManager;

import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.CrudActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;


/**
 * 餐桌包厢预订管理Action.
 * 
 * 
 * @author 31307@sohu.com
 */
//定义URL映射对应/eat/table.action
@Namespace("/wx")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "reserve.action", type = "redirect") })
@InterceptorRefs({@InterceptorRef(value="token",params={"includeMethods","confirm"}),@InterceptorRef("defaultStack") })
public class ReserveAction extends CrudActionSupport<TableReserve> {

	private static final long serialVersionUID = 5213013353567677325L;

	private TableReserveManager tableReserveManager;
	private CustomerManager customerManager;
	private TableManager tableManager;
	private EatTable table;

	private String id;
	private String orgId;
	private String weixinId;
	private String tableId;
	private String categories;
	private String year;
	private String month;
	private String day;
	private String beginHour;
	private String beginMinute;
	private String endHour;
	private String endMinute;
	private String name;
	private String phone;

	//-- 页面属性 --//

	private TableReserve entity;
	private Page<EatTable> page = new Page<EatTable>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public TableReserve getModel() {
		return entity;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getOrgId(){
		if (Unit.isNotNull(this.orgId)){
			return this.orgId; 
		}else
			return (String)getSession().getAttribute("orgId");
	}
	
	public void setWeixinId(String weixinId){
		this.weixinId = weixinId.trim();
	}
	
	public String getWeixinId(){
		if (Unit.isNotNull(this.weixinId)){
			return this.weixinId.trim();
		}else
			return ((String)getSession().getAttribute("weixinId")).trim();
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
	public void setTableId(String tableId){
		this.tableId = tableId;
	}
	
	public String getTableId(){
		return this.tableId;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setBeginHour(String beginHour) {
		this.beginHour = beginHour;
	}

	public void setBeginMinute(String beginMinute) {
		this.beginMinute = beginMinute;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}	

	@Override
	protected void prepareModel() throws Exception {

	}

	public Page<EatTable> getPage(){
		return this.page;
	}
	
	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		return null;
	}
	
	@Override
	public String input() throws Exception {
		if (Unit.isNotNull(getOrgId()) && Unit.isNotNull(getWeixinId())){
			getSession().setAttribute("orgId", getOrgId());
			getSession().setAttribute("weixinId", getWeixinId());
		}else
			addActionMessage("您访问的页面有错误！");
		return INPUT;
	}
	
	@Override
	public String delete() throws Exception {
		return null;
	}
	
	public String cancel() throws Exception {
		TableReserve tableReserve = tableReserveManager.getTableReserve(Long.valueOf(this.id));
		tableReserveManager.change(tableReserve, TableReserve.CANCEL_STATE);
		return "order";
	}
	
	@Override
	public String save() throws Exception {
		Customer customer = new Customer();
		customer.setCreateDate(Datetime.getNow());
		customer.setName(this.name);
		customer.setOrgId(Long.valueOf(getOrgId()));
		customer.setPhone(this.phone);
		customer.setWeixinId(getWeixinId().trim());
		customerManager.save(customer);
		customer = customerManager.getCustomerByWeixinId(Long.valueOf(getOrgId()), getWeixinId().trim());
		if (customer != null){
			TableReserve reserve = new TableReserve();
			reserve.setUserId(customer.getId());
			reserve.setOrgId(Long.valueOf(getOrgId()));
			reserve.setFirst(0);
			reserve.setEatTable(tableManager.getEatTable(Long.valueOf(tableId)));
			reserve.setBeginTime((String)getSession().getAttribute("beginTime"));
			reserve.setEndTime((String)getSession().getAttribute("endTime"));
			reserve.setDatetime(Datetime.getNow());
			tableReserveManager.saveWithoutFirst(reserve);
		}
		return "order";
	}
	
	public String check() throws Exception{
		String beginTime = year + "-" + month + "-" + day + " " + beginHour + ":" + beginMinute + ":" + "00";
		String endTime = year + "-" + month + "-" + day + " " + endHour + ":" + endMinute + ":" + "00";
		List<EatTable> tableList = tableReserveManager.searchWithoutReserve(Long.valueOf(getOrgId()), this.categories, beginTime, endTime);
		this.page.setResult(tableList);
		if (Unit.isNotNull(tableList)){
			getSession().setAttribute("beginTime", beginTime);
			getSession().setAttribute("endTime", endTime);
		}else
			addActionMessage("没有符合您查询条件的预订！");
		return "list";
	}
	
	public String confirm() throws Exception {
		Customer customer = customerManager.getCustomerByWeixinId(Long.valueOf(getOrgId()), getWeixinId().trim());
		if (customer != null){
			TableReserve reserve = new TableReserve();
			reserve.setUserId(customer.getId());
			reserve.setOrgId(Long.valueOf(getOrgId()));
			reserve.setFirst(1);
			reserve.setEatTable(tableManager.getEatTable(Long.valueOf(tableId)));
			reserve.setBeginTime((String)getSession().getAttribute("beginTime"));
			reserve.setEndTime((String)getSession().getAttribute("endTime"));
			reserve.setDatetime(Datetime.getNow());
			tableReserveManager.saveWithoutFirst(reserve);
			return "order";
		}else{
			setTableId(tableId);
			return "customer";
		}
			
	}
	
	public String order() throws Exception {
		return "order";
	}
	
	public String customer() throws Exception {
		return "customer";
	}
	
	//-- 页面属性访问函数 --//
	public List<String> getTableCate(){
		return Categories.getTableCate();
	}
	
	public List<String> getYearList(){
		List<String> list = new ArrayList<String>();
		String year = Datetime.getYear();
		list.add(year);
		list.add(String.valueOf(Integer.valueOf(year)+1));
		return list;
	}
	
	public List<TableReserve> getTableReserve(){
		Customer customer = customerManager.getCustomerByWeixinId(Long.valueOf(getOrgId()), getWeixinId().trim());
		return tableReserveManager.getTableReserve(Long.valueOf(getOrgId()), customer.getId());
	}
	
	public String tableDetail(){
		this.table = tableManager.getEatTable(Long.valueOf(this.tableId));
		return "table"; 
	}
	
	public EatTable getTable(){
		return this.table;
	}
	
	@Autowired
	public void setTableReserveManager(TableReserveManager tableReserveManager) {
		this.tableReserveManager = tableReserveManager;
	}
	
	@Autowired
	public void setCustomerManager(CustomerManager customerManager){
		this.customerManager = customerManager;
	}

	@Autowired
	public void setTableManager(TableManager tableManager){
		this.tableManager = tableManager;
	}
	
}
