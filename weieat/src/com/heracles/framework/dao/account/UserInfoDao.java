package com.heracles.framework.dao.account;


import com.heracles.framework.entity.account.UserInfo;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author calvin
 */
@Component
public class UserInfoDao extends HibernateDao<UserInfo, Long> {

}
