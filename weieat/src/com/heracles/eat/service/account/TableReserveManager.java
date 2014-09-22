package com.heracles.eat.service.account;

import java.util.Iterator;
import java.util.List;

import com.heracles.eat.dao.account.EatTableDao;
import com.heracles.eat.dao.account.TableReserveDao;
import com.heracles.eat.entity.account.EatTable;
import com.heracles.eat.entity.account.TableReserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;


/**
 * 界面餐桌的管理类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class TableReserveManager {

	private TableReserveDao tableReserveDao;
	private EatTableDao tableDao;
	
	public void save(TableReserve entity) {
		boolean isFrist = isFrist(entity.getOrgId(), entity.getUserId());
		if (isFrist){
			entity.setFirst(0);
		}else
			entity.setFirst(1);
		tableReserveDao.save(entity);
	}
	
	public void saveWithoutFirst(TableReserve entity){
		tableReserveDao.save(entity);
	}
	
	@Transactional(readOnly = true)
	public List<EatTable> searchWithoutReserve(Long orgId, String categories, String beginTime, String endTime){
		List<EatTable> tableList = tableDao.getTableByReserve(orgId, categories);
		List<TableReserve> reserveList = getReserveTable(orgId, categories, beginTime, endTime);
		for (TableReserve reserve : reserveList){
			Iterator<EatTable> iter = tableList.iterator();
			while (iter.hasNext()){
				EatTable table = iter.next();
				if (reserve.getEatTable().getId().equals(table.getId())){
					iter.remove();
				}
			}
		}
		return tableList;
	}

	/**
	 * 使用属性过滤条件查询.
	 */
	@Transactional(readOnly = true)
	public Page<TableReserve> searchTableReserve(final Page<TableReserve> page, final List<PropertyFilter> filters) {
		page.orderBy("beginTime");
		page.order("desc");
		return tableReserveDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<TableReserve> searchTableReserve(final Page<TableReserve> page, final List<PropertyFilter> filters, Long orgId) {
		filters.add(new PropertyFilter("EQL_orgId", String.valueOf(orgId)));
		page.orderBy("beginTime");
		page.order("desc");
		return tableReserveDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public List<TableReserve> getTableReserve(Long orgId, Long userId){		
		return tableReserveDao.getTableReserve(orgId, userId);
	}
	
	/**
	 * 按id得到一个实体.
	 */	
	@Transactional(readOnly = true)
	public TableReserve getTableReserve(Long id){
		return tableReserveDao.get(id);
	}

	@Transactional(readOnly = true)
	public Long getNewReserveCount(Long orgId){
		return tableReserveDao.getNewReserveCount(orgId);
	}
	
	public void change(TableReserve reserve, int state) {
		tableReserveDao.change(reserve, state);
	}
	
	public void delete(Long id) {
		tableReserveDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public boolean isFrist(Long orgId, Long userId){
		return tableReserveDao.isFrist(orgId, userId);
	}
	
	@Autowired
	public void setTableReserveDao(TableReserveDao tableReserveDao) {
		this.tableReserveDao = tableReserveDao;
	}
	
	@Autowired
	public void setEatTableDao(EatTableDao tableDao){
		this.tableDao = tableDao;
	}
	
	private List<TableReserve> getReserveTable(Long orgId, String categories, String beginTime, String endTime){
		return tableReserveDao.getReserveTable(orgId, categories, beginTime, endTime);
	}
	
	
}
