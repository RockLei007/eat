package com.heracles.framework.service.account;

import java.util.List;

import com.heracles.framework.dao.account.OperationLogDao;
import com.heracles.framework.entity.account.OperationLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;


/**
 * 操作日志记录类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class OperationLogManager {

	private OperationLogDao operationLogDao;

	public void saveLog(OperationLog entity) {
		operationLogDao.save(entity);
	}

	@Transactional(readOnly = true)
	public Page<OperationLog> searchLog(final Page<OperationLog> page, final List<PropertyFilter> filters) {
		return operationLogDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public Page<OperationLog> searchLog(final Page<OperationLog> page, final List<PropertyFilter> filters, Long orgId) {
		filters.add(new PropertyFilter("EQL_orgId", String.valueOf(orgId)));
		return operationLogDao.findPage(page, filters);
	}
	
	@Transactional(readOnly = true)
	public OperationLog getOperationLog(Long id) {
		return operationLogDao.get(id);
	}
	
	public void deleteLosteEffectiveness(int space) {
		operationLogDao.deleteLosteEffectiveness(space);
	}
	
	@Autowired
	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}


}
