package com.heracles.weixin.inteface;

import java.util.List;
import java.util.Map;

import com.heracles.weixin.RuleValue;
import com.heracles.weixin.Translation;

public interface Plug {

	public abstract String execut();
	
	public void setRuleMap(Map<String , List<RuleValue>> ruleMap);
	
	public void setTranslation(Translation translation);
	
	public void setOrgId(Long orgId);
	
	public void setWeixinId(String weixinId);
	
	public void setContent(String content);
	
	public void setMusicUrl(String musicUrl);
	
	public void setHQMusicUrl(String hqMusicUrl);
	
}
