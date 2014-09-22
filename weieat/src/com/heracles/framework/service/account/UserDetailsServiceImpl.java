package com.heracles.framework.service.account;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Sets;
import com.heracles.framework.entity.account.Authority;
import com.heracles.framework.entity.account.OperationLog;
import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.entity.account.User;
import com.heracles.framework.entity.account.UserInfo;
import com.heracles.framework.general.DispatchServices;
import com.heracles.framework.general.GeneralToken;
import com.heracles.framework.security.MD5Util;
import com.heracles.framework.service.CustomUserDetails;
import com.heracles.framework.service.Language;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.Unit;

/**
 * 实现SpringSecurity的UserDetailsService接口,实现获取用户Detail信息的回调函数.
 * 
 * @author calvin
 */
@Transactional()
public class UserDetailsServiceImpl implements UserDetailsService {

	private AccountManager accountManager;
	private OperationLogManager operationLogManager;
	private Organization org = null;

	/**
	 * 获取用户Details信息的回调函数.
	 */
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		User user = accountManager.findUserByLoginName(username);

		if (user == null){
			throw new UsernameNotFoundException(Language.getMessage("User.notFind", new String[]{username}));
		}

		org = user.getOrganization();
		UserInfo userInfo = user.getUserInfo();
		List<Role> roles = user.getRoleList();
		
		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);

		//-- mini-web示例中无以下属性, 暂时全部设为true. --//
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		String messageTemplate = "UserDetailsService.successLogin.log";
		String result = "success";
		
		
		CustomUserDetails userdetails = new CustomUserDetails(user.getLoginName(), user
				.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths, org, userInfo, roles);
		
		if (!checkUserLoginSuccess(user.getPassword())){
			messageTemplate = "UserDetailsService.failLogin.log";
			result = "fail";
		}
		userInfo.setLastLoginDate(Datetime.getNow());
		accountManager.saveUserInfo(userInfo);
		
		if (GeneralToken.getUserLoginLog()){
			doLog(user, messageTemplate, result);
		}

		return userdetails;
	}

	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : user.getRoleList()) {
			for (Authority authority : role.getAuthorityList()) {
				authSet.add(new GrantedAuthorityImpl(authority.getPrefixedName()));
			}
		}
		return authSet;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	/**
	 * 注入操作记录类. 
	*/ 
	@Autowired
	public void setOperationLogManager(OperationLogManager operationLogManager){
		this.operationLogManager = operationLogManager;
	}

	private void doLog(User user, String messageTemplate, String result){
		OperationLog log = new OperationLog();
		log.setClassName("UserDetailsServiceImpl");
		log.setDatetime(Datetime.getNow());
		log.setKeyId(String.valueOf(user.getId()));
		log.setKeyName(user.getLoginName());
		log.setIp(DispatchServices.getUserIp());
		log.setMethodName("loadUserByUsername");
		log.setUserName(user.getLoginName());
		log.setDescription(Language.getMessage(messageTemplate));
		log.setResult(result);
		log.setOrgId(org.getId());
		operationLogManager.saveLog(log);
	}
	
	@SuppressWarnings("deprecation")
	private boolean checkUserLoginSuccess(String md5Password){
		String requestPassword = "";
		if (DispatchServices.getRequest() != null) {
			try{
				requestPassword = DispatchServices.getRequest().getParameter("j_password");
			} catch	(Exception e) {
				e.getStackTrace();
			}
		}
		if (Unit.isNotNull(requestPassword)){
			return md5Password.equals(MD5Util.MD5(requestPassword));
		}else
			return false;
	}
	
}
