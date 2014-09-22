package com.heracles.framework.web.value;

public class NavigationMenu {

	private String path;
	private String name;
	private String target;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	//main 显示在主窗口中, dialog 弹出对话框, window 弹出新窗口
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
}
