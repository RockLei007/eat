package com.heracles.eat.web.account;

import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.ReserveAttention;
import com.heracles.eat.service.account.ReserveAttentionManager;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.entity.account.User;
import com.heracles.framework.service.account.AccountManager;
import com.heracles.framework.web.CrudActionSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 接收信息管理Action.
 * 
 * 
 * @author 31307@sohu.com
 */
//定义URL映射对应/eat/table.action
@Namespace("/eat")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "attention.action", type = "redirect") })
public class AttentionAction extends CrudActionSupport<ReserveAttention> {

	private static final long serialVersionUID = 4742103211593934370L;

	private ReserveAttentionManager reserveAttentionManager;
	private AccountManager accountManager;
	private Long orgId;
	private Long userId;

	//-- 页面属性 --//

	private ReserveAttention entity;
	private Page<ReserveAttention> page = new Page<ReserveAttention>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public ReserveAttention getModel() {
		return entity;
	}

	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	
	public Long getOrgId(){
		return getUserDetails().getOrganization().getId();
	}
	
	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		entity = new ReserveAttention();
	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		
		boolean hasAdminAuth = false;
		List<Role> roles = getUserDetails().getUserRoles();
		for (Role role : roles) {
			if (role.getId().equals(1L)){
				hasAdminAuth = true;
			}
		}
		if (hasAdminAuth){
			page = reserveAttentionManager.searchReserveAttention(page, filters);
		}else{
			Long orgId = getUserDetails().getOrganization().getId();
			if (orgId != null){
				page = reserveAttentionManager.searchReserveAttention(page, filters, orgId);
			}
		}	
		return SUCCESS;
	}
	
	public List<User> getUserList(){
		Long orgId = getUserDetails().getOrganization().getId();
		List<User> userList = null;
		if (orgId != null && orgId > 0){
			userList = accountManager.getUserByOrg(orgId);
			List<ReserveAttention> attList = reserveAttentionManager.getAttentionByOrgId(orgId);
			Iterator<User> userIter = userList.iterator();
			while (userIter.hasNext()){
				User user = (User)userIter.next();
				Iterator<ReserveAttention> attIter = attList.iterator();
				while (attIter.hasNext()){
					ReserveAttention att = attIter.next();
					if (att.getUserId().equals(user.getId())){
						userIter.remove();
						attIter.remove();
					}
				}
			}
		}
		return userList;
	}
	
	public String active() throws Exception {
		reserveAttentionManager.active(orgId, userId);
		return RELOAD;
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}
	
	@Override
	@RecordOperation(fieldId = "userId", filedName = "userName")
	public String delete() throws Exception {
		entity = new ReserveAttention();
		entity.setOrgId(orgId);
		entity.setUserId(userId);
		reserveAttentionManager.delete(entity);
		return RELOAD;
	}
	
	@Override
	@RecordOperation(fieldId = "userId", filedName = "userName")
	public String save() throws Exception {
		entity.setOrgId(getOrgId());
		User user = accountManager.getUser(this.userId);
		entity.setUserName(user.getName());
		reserveAttentionManager.save(entity);
		return RELOAD;
	}
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<ReserveAttention> getPage() {
		return page;
	}

	@Autowired
	public void setReserveAttentionManager(ReserveAttentionManager reserveAttentionManager) {
		this.reserveAttentionManager = reserveAttentionManager;
	}
	
	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	

	
}
