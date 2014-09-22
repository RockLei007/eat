package com.heracles.eat.service.account;

import java.util.List;

import com.heracles.eat.dao.account.ReceiveMessageDao;
import com.heracles.eat.entity.account.ReceiveMessage;

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
public class ReceiveMessageManager {

	private ReceiveMessageDao receiveMessageDao;

	public void save(ReceiveMessage entity) {
		receiveMessageDao.save(entity);
	}

	/**
	 * 使用属性过滤条件查询.
	 */
	@Transactional(readOnly = true)
	public Page<ReceiveMessage> searchReceiveMessage(final Page<ReceiveMessage> page, final List<PropertyFilter> filters) {
		return receiveMessageDao.findPage(page, filters);
	}
	
	/**
	 * 按id得到一个实体.
	 */	
	@Transactional(readOnly = true)
	public ReceiveMessage getReceiveMessage(Long id){
		return receiveMessageDao.get(id);
	}

	@Autowired
	public void setReceiveMessageDao(ReceiveMessageDao receiveMessageDao) {
		this.receiveMessageDao = receiveMessageDao;
	}
	
	
}
