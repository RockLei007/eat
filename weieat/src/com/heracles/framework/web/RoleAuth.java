package com.heracles.framework.web;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.service.CustomUserDetails;

public class RoleAuth {

	public static boolean hasAdminAuth(){
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return hasAdminAuth(userDetails);
	}
	
	public static boolean hasAdminAuth(CustomUserDetails userDetails){
		boolean hasAdminAuth = false;
		
		List<Role> roles = userDetails.getUserRoles();
		for (Role role : roles) {
			if (role.getId().equals(1L)){
				hasAdminAuth = true;
			}
		}
		return hasAdminAuth;
	}
	
	public static Organization getOrg(){
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getOrganization();
	}
	
	public static Long getOrgId(){
		return getOrg().getId();
	}
	
	
}
