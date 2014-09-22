package com.heracles.weixin.plug;

import org.springframework.stereotype.Component;

import com.heracles.weixin.inteface.AbstractPlug;


@Component
public class TableReservePlug extends AbstractPlug {
	
	@Override
	public String execut() {
		return getReplaceTemplate("餐饮业务","yuding");
	}

	
}
