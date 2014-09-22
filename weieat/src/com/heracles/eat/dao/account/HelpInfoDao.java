package com.heracles.eat.dao.account;

import java.util.List;

import com.heracles.eat.entity.account.HelpInfo;
import com.heracles.framework.tools.Unit;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 点菜菜对象的泛型DAO类.
 * 
 * @author 31307@sohu.com
 */
@Component
public class HelpInfoDao extends HibernateDao<HelpInfo, Long> {

	public HelpInfo getHelpInfo(Long orgId){
		String hql = "from HelpInfo where orgId="+String.valueOf(orgId);
		List<HelpInfo> list = find(hql);
		if (Unit.isNotNull(list)){
			return list.get(0);
		}
		return null;
	}
	
}
