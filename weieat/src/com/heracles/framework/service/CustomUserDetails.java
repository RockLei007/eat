package com.heracles.framework.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.entity.account.UserInfo;

public class CustomUserDetails extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Organization org;
	private UserInfo userInfo;
	private List<Role> roles;

	public CustomUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, Organization org, UserInfo userInfo, List<Role> roles) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.org = org;
		this.userInfo = userInfo;
		this.roles = roles;
	}
	
	public Organization getOrganization(){
		return this.org;
	}
	
	public UserInfo getUserInfo(){
		return this.userInfo;
	}
	
	public List<Role> getUserRoles(){
		return this.roles;
	}


}
