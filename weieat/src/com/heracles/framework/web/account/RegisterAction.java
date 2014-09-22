package com.heracles.framework.web.account;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.heracles.framework.entity.account.Organization;
import com.heracles.framework.entity.account.Role;
import com.heracles.framework.entity.account.User;
import com.heracles.framework.entity.account.UserInfo;
import com.heracles.framework.general.GeneralToken;
import com.heracles.framework.security.MD5Util;
import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.service.account.AccountManager;
import com.heracles.framework.service.account.OrganizationManager;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.CrudActionSupport;
import com.octo.captcha.service.CaptchaService;


/**
 * 用户注册、忘记密码、修改密码管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author yinzj
 */
//定义URL映射对应/register.action
@Namespace("/")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "register.action", type = "redirect") })
public class RegisterAction extends CrudActionSupport<User> {
	
	private static final long serialVersionUID = -7579358703356739751L;
	private AccountManager accountManager;
	private OrganizationManager orgManager;
	private CaptchaService captchaService; 
	private User entity;
	private UserInfo userInfo;
	
	private String loginName = "";
	private String j_captcha = "";
	private String password = "";
	private String name = "";
	private String email = "";
	
	public void setLoginName(String loginName){
		this.loginName = loginName;
    };
    
    public void setJ_captcha(String j_captcha){
    	this.j_captcha = j_captcha;
    }
    
	public void setPassword(String password){
		this.password = password;
    };
    
	public void setName(String name){
		this.name = name;
    };
    
	public void setEmail(String email){
		this.email = email;
    };
    
	/*public void setAddress(String address){
		userInfo.setAddress(address);
    };
    
	public void setPhone(String phone){
		userInfo.setPhone(phone);
    };
    
	public void setMobile(String mobile){
		userInfo.setMobile(mobile);
    };
    
	public void setQq(String qq){
		userInfo.setQq(qq);
    };
    
	public void setMsn(String msn){
		userInfo.setMsn(msn);
    };
    
	public void setSex(int sex){
		userInfo.setSex(sex);
    };
    
    public void setCredentialsType(Long credentialsType){
    	userInfo.setCredentialsType(credentialsType);
    }
    
    public void setIdentity(String identity){
		userInfo.setIdentity(identity);
    };
    
    public void setDiplomas(Long diplomas){
    	userInfo.setDiplomas(diplomas);
    }
    
    public void setPolitical(Long political){
    	userInfo.setPolitical(political);
    }*/

	@Override
	public User getModel() {
		return entity;
	}
	
	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		this.entity = new User();
		this.userInfo = new UserInfo();
	}
	
	public String checkCaptcha(){
		String captchaID = getSession().getId();
		String challengeResponse = this.j_captcha;
		if (Unit.isNotNull(challengeResponse)){
			if (captchaService.validateResponseForID(captchaID, challengeResponse)){
				Struts2Utils.renderHtml("true", "encoding:UTF-8");
			}else
				Struts2Utils.renderHtml("false", "encoding:UTF-8");
		}
		return null;
	}
	
	@Override
	public String save() throws Exception {
		if (GeneralToken.getRegister()){
			try {
				entity.setCreateDate(Datetime.getNow());
				entity.setEmail(this.email);
				entity.setLoginName(this.loginName);
				entity.setName(this.name);
				entity.setPassword(MD5Util.MD5(this.password));
				Organization org = orgManager.getOrg(2L);
				entity.setOrganization(org);
				userInfo.setUser(entity);
				entity.setUserInfo(userInfo);
				Role role = accountManager.getRole(2L);
				List<Role> roleList = new ArrayList<Role>();
				roleList.add(role);
				entity.setRoleList(roleList);
				accountManager.saveUser(entity);
				addActionMessage(Language.getMessage("RegisterAction.save.success"));
			} catch (ServiceException e) {
				logger.error(e.getMessage(), e);
				addActionMessage(Language.getMessage("RegisterAction.save.fail"));
			}
			/*Map<String, String> replaceMap = new HashMap<String, String>();
			replaceMap.put("$userName", this.loginName);
			replaceMap.put("$password", this.password);
			replaceMap.put("$j_captcha", this.j_captcha);
			
			String html = Replace.replace(TemplateCache.getHtmlTempLate("register_login"), replaceMap);
			Struts2Utils.renderHtml(html, "encoding:UTF-8");*/
			//Struts2Utils.renderHtml(TemplateCache.getHtmlTempLate("register_login"), "encoding:UTF-8");
			Struts2Utils.getResponse().sendRedirect("/html/register_login.html");
		}else{
			Struts2Utils.renderHtml("<script language='javascript' type='text/javascript'> alert('网站禁止注册！');</script>", "encoding:UTF-8");
		}
		return null;
	}
	
	public String checkLoginName() throws Exception {
		User user = accountManager.findUserByLoginName(this.loginName);
		if (user != null){
			Struts2Utils.renderText("false");
		}else
			Struts2Utils.renderText("ture");
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	@Autowired
	public void setOrganizationManager(OrganizationManager orgManager) {
		this.orgManager = orgManager;
	}

	@Autowired
	public void setCaptchaService(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	

}
