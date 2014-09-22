package com.heracles.eat.service.account;

import java.util.List;

import com.heracles.eat.dao.account.HelpInfoDao;
import com.heracles.eat.entity.account.HelpInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;


/**
 * 接收消息的管理类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class HelpInfoManager {

	private HelpInfoDao helpInfoDao;

	public void save(HelpInfo entity) {
		helpInfoDao.save(entity);
	}

	/**
	 * 使用属性过滤条件查询.
	 */
	@Transactional(readOnly = true)
	public Page<HelpInfo> searchHelpInfo(final Page<HelpInfo> page, final List<PropertyFilter> filters) {
		return helpInfoDao.findPage(page, filters);
	}
	
	/**
	 * 按id得到一个实体.
	 */	
	@Transactional(readOnly = true)
	public HelpInfo getHelpInfo(Long orgId){
		return helpInfoDao.getHelpInfo(orgId);
	}

	@Autowired
	public void setHelpInfoDao(HelpInfoDao helpInfoDao) {
		this.helpInfoDao = helpInfoDao;
	}
	
	
}
