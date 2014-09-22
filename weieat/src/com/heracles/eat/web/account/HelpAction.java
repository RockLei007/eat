package com.heracles.eat.web.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.HelpInfo;
import com.heracles.eat.service.account.HelpInfoManager;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.file.ConfigureHelper;
import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.service.account.OrganizationManager;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.CrudActionSupport;
import com.heracles.weixin.Encrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 返回帮助信息管理Action.
 * 
 * 
 * @author 31307@sohu.com
 */
//定义URL映射对应/eat/table.action
@Namespace("/eat")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "help.action", type = "redirect") })
public class HelpAction extends CrudActionSupport<HelpInfo> {

	private static final long serialVersionUID = -3293526967469728157L;

	private HelpInfoManager helpInfoManager;
	private OrganizationManager orgManager;
	private Long orgId;
	
	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	
	public Long getOrgId(){
		return entity.getOrgId();
	}
	
	public String getContent(){
		return entity.getContent();
	}
	
	public String getUrl(){
		orgId = getUserDetails().getOrganization().getId();
		Organization org = orgManager.getOrg(orgId);
		StringBuffer sb = new StringBuffer();
		if (org != null && Unit.isNotNull(org.getIdentity())){
			sb.append(ConfigureHelper.getValue("domain"));
			sb.append("/wx/weixin!receiveMessage.action?");
			Long orgId = org.getId();
			sb.append("orgId=" + orgId +"&");
			sb.append("key="+Encrypt.make(orgId, org.getIdentity()));
		}else
			addActionMessage("请完善你的组织机构信息!");
		return sb.toString();
	}

	//-- 页面属性 --//

	private HelpInfo entity;
	private Page<HelpInfo> page = new Page<HelpInfo>(size);

	//-- ModelDriven 与 Preparable函数 --//
	public HelpInfo getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (orgId == null) {
			orgId = getUserDetails().getOrganization().getId();
		} 
		entity = helpInfoManager.getHelpInfo(orgId);
		if (entity == null){
			entity = new HelpInfo();
			entity.setOrgId(orgId);
		}
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
		page = helpInfoManager.searchHelpInfo(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	public String wxUrl() throws Exception {
		return "url";
	}
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@RecordOperation(fieldId = "orgId", filedName = "")
	public String save() throws Exception {
		try {
			helpInfoManager.save(entity);
			addActionMessage(Language.getMessage("HelpAction.save.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(Language.getMessage("HelpAction.save.fail"));
		}
		return INPUT;
	}
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<HelpInfo> getPage() {
		return page;
	}

	@Autowired
	public void setHelpInfoManager(HelpInfoManager helpInfoManager) {
		this.helpInfoManager = helpInfoManager;
	}

	@Autowired
	public void setOrganizationManager(OrganizationManager orgManager) {
		this.orgManager = orgManager;
	}
	

	
}
