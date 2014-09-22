package com.heracles.weixin.web.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.heracles.eat.entity.account.HelpInfo;
import com.heracles.eat.entity.account.ReceiveMessage;
import com.heracles.eat.service.account.HelpInfoManager;
import com.heracles.eat.service.account.ReceiveMessageManager;

import com.heracles.framework.cache.TemplateCache;
import com.heracles.framework.general.DispatchServices;
import com.heracles.framework.security.MD5Util;
import com.heracles.framework.service.account.OrganizationManager;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.CrudActionSupport;
import com.heracles.weixin.Encrypt;
import com.heracles.weixin.Rule;
import com.heracles.weixin.RuleValue;
import com.heracles.weixin.Translation;
import com.heracles.weixin.inteface.Plug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 微信接收信息Action.
 * 
 * 
 * @author 31307@sohu.com
 */
//定义URL映射对应/eat/table.action
@Namespace("/wx")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "weixin.action", type = "redirect") })
public class WeixinAction extends CrudActionSupport<ReceiveMessage> {

	private static final long serialVersionUID = -7894387268760943017L;

	private ReceiveMessageManager receiveMessageManager;
	private OrganizationManager orgManager;
	private HelpInfoManager helpInfoManager;
	
	private String echostr;
	private String timestamp;
	private String nonce;
	private String signature;
	private Long orgId;
	private String key;
	private String token;
	
	public void setEchostr(String echostr){
		this.echostr = echostr;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}
	
	public void setNonce(String nonce){
		this.nonce = nonce;
	}
	
	public void setSignature(String signature){
		this.signature = signature;
	}
	
	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	
	public void setKey(String key){
		this.key = key;
	}
	//-- 页面属性 --//

	private ReceiveMessage entity;

	//-- ModelDriven 与 Preparable函数 --//
	public ReceiveMessage getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return null;
	}
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String receiveMessage() throws Exception {
		String xml = "";
		this.token = orgManager.getOrg(orgId).getIdentity();
		if (Unit.isNotNull(echostr) && Unit.isNotNull(signature) && Unit.isNotNull(timestamp) && Unit.isNotNull(nonce) && distinguish()){
			Struts2Utils.renderText(echostr);
			return null;
		}
		String msgXml = "";
		try {
			msgXml = IOUtils.toString(getRequest().getInputStream(),"utf-8");
			boolean status = false;
			if (Unit.isNotNull(msgXml)){
				Translation translation = Translation.getInstance(msgXml);
				if (Encrypt.equals(orgId, this.token, key)){
					if (translation.getMsgType().trim().equals(Translation.TEXT_TYPE)){
						List<RuleValue> ruleList = Rule.getAllRule();
						String content = translation.getContent().trim();
						for (RuleValue value : ruleList){
							Pattern pattern = Pattern.compile(value.getRegex());
							Matcher matcher = pattern.matcher(content);
							if (matcher.matches()){
								status = true;
								xml = invoke(value, Rule.getMapRule(), translation, orgId);
							}
						}
					}
					if (translation.getMsgType().trim().equals(Translation.EVENT_TYPE)){
						HelpInfo helpInfo = helpInfoManager.getHelpInfo(orgId);
						if (helpInfo != null){
							xml = replaceTemplate(translation, helpInfo.getContent());
						}
					}
				}
				if (!status){
					HelpInfo helpInfo = helpInfoManager.getHelpInfo(orgId);
					if (helpInfo != null){
						xml = replaceTemplate(translation, helpInfo.getContent());
					}
				}
			}else{
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><errorMassage>非法或者错误的请求</errorMassage>";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><errorMassage>非法或者错误的请求</errorMassage>";
		}
		if(Unit.isNotNull(msgXml)){
			entity = new ReceiveMessage();
			entity.setDatetime(Datetime.getNow());
			entity.setContent(msgXml);
			receiveMessageManager.save(entity);
		}
		Struts2Utils.renderXml(xml);
		return null;
	}

	@Autowired
	public void setReceiveMessageManager(ReceiveMessageManager receiveMessageManager) {
		this.receiveMessageManager = receiveMessageManager;
	}
	
	@Autowired
	public void setOrganizationManager(OrganizationManager orgManager) {
		this.orgManager = orgManager;
	}
	
	@Autowired
	public void setHelpInfoManager(HelpInfoManager helpInfoManager) {
		this.helpInfoManager = helpInfoManager;
	}

	private boolean distinguish(){
		if (!Unit.isNotNull(timestamp)) return false;
		if (!Unit.isNotNull(nonce)) return false;
		if (!Unit.isNotNull(signature)) return false;
		List<String> ss = new ArrayList<String>();
		ss.add(timestamp);
		ss.add(nonce);
		ss.add(token);
		Collections.sort(ss);
		StringBuilder builder = new StringBuilder();
		for(String s : ss) {
			builder.append(s);
		}
		return signature.equalsIgnoreCase(MD5Util.sha1(builder.toString()));
	}
	
	private String invoke(RuleValue value, Map<String, List<RuleValue>> ruleMap, Translation translation, Long orgId){
		WebApplicationContext context = DispatchServices.getWebAppContext();
		Plug plug = (Plug) context.getBean(value.getClassName());
		plug.setRuleMap(ruleMap);
		plug.setTranslation(translation);
		plug.setOrgId(orgId);
		plug.setWeixinId(translation.getFromUserName());
		return plug.execut();
	}
	
	private String replaceTemplate(Translation translation, String content){
		String textTemplate = TemplateCache.getXmlTempLate("text");
		textTemplate = textTemplate.replace(Unit.prettify("toUser"), translation.getFromUserName());
		textTemplate = textTemplate.replace(Unit.prettify("fromUser"), translation.getToUserName());
		textTemplate = textTemplate.replace(Unit.prettify("createTime"), String.valueOf(System.currentTimeMillis()));
		textTemplate = textTemplate.replace(Unit.prettify("content"), content);
		return textTemplate;
	}

	
}
