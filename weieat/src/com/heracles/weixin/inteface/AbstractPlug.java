package com.heracles.weixin.inteface;

import java.util.List;
import java.util.Map;

import com.heracles.framework.cache.TemplateCache;
import com.heracles.framework.file.ConfigureHelper;
import com.heracles.framework.tools.Unit;
import com.heracles.weixin.RuleValue;
import com.heracles.weixin.Translation;

public abstract class AbstractPlug implements Plug  {

	protected Map<String , List<RuleValue>> ruleMap = null;
	protected Translation translation = null;
	protected Long orgId;
	protected String weixinId;
	protected String content;
	protected String musicUrl;
	protected String hqMusicUrl;

	@Override
	public void setRuleMap(Map<String, List<RuleValue>> ruleMap) {
		this.ruleMap = ruleMap;
	}
	
	@Override
	public void setTranslation(Translation translation) {
		this.translation = translation;	
	}
	
	public String getReplaceTemplate(String businessName, String py){
		RuleValue value = getRuleValue(businessName.trim(), py.trim());
		String xmlTemplate = "";
		if (value != null){
			xmlTemplate = TemplateCache.getXmlTempLate(value.getReturnType());
			xmlTemplate = xmlTemplate.replace(prettify("toUser"), translation.getFromUserName());
			xmlTemplate = xmlTemplate.replace(prettify("fromUser"), translation.getToUserName());
			xmlTemplate = xmlTemplate.replace(prettify("createTime"), String.valueOf(System.currentTimeMillis()));
			xmlTemplate = xmlTemplate.replace(prettify("count"), "1");
			xmlTemplate = xmlTemplate.replace(prettify("title"), value.getTitle());
			xmlTemplate = xmlTemplate.replace(prettify("description"), value.getDescription());
			if (Unit.isNotNull(value.getPicUrl())){
				xmlTemplate = xmlTemplate.replace(prettify("picur_url"), value.getPicUrl());
			}
			if (Unit.isNotNull(value.getUrl())){
				xmlTemplate = xmlTemplate.replace(prettify("url"), value.getUrl());
			}
			if (Unit.isNotNull(value.getContent())){
				xmlTemplate = xmlTemplate.replace(prettify("content"), value.getContent());
			}
			if (Unit.isNotNull(value.getMusicUrl())){
				xmlTemplate = xmlTemplate.replace(prettify("musicUrl"), value.getMusicUrl());
			}
			if (Unit.isNotNull(value.getHqMusicUrl())){
				xmlTemplate = xmlTemplate.replace(prettify("hqMusicUrl"), value.getHqMusicUrl());
			}
			xmlTemplate = xmlTemplate.replace(prettify("domain"), ConfigureHelper.getValue("domain"));
			if (orgId != null && orgId >0){
				xmlTemplate = xmlTemplate.replace(prettify("orgId"), String.valueOf(orgId));
			}
			if (Unit.isNotNull(weixinId)){
				xmlTemplate = xmlTemplate.replace(prettify("weixinId"), weixinId);
			}
			//xmlTemplate = xmlTemplate.replace("&amp;", "&");
			
		}
		return xmlTemplate;
	}

	public void setOrgId(Long orgId){
		this.orgId = orgId;
	}
	
	public void setWeixinId(String weixinId){
		this.weixinId = weixinId;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public void setMusicUrl(String musicUrl){
		this.musicUrl = musicUrl;
	}
	
	public void setHQMusicUrl(String hqMusicUrl){
		this.hqMusicUrl = hqMusicUrl;
	}
	
	private RuleValue getRuleValue(String businessName, String name){
		if (Unit.isNotNull(businessName)){
			List<RuleValue> ruleList = ruleMap.get(businessName);
			for (RuleValue value : ruleList){
				if (value.getName().equals(name)){
					return value;
				}
			}
		}
		return null;
	}

	private String prettify(String name){
		return "$" + name + "$";
	}
	


}
