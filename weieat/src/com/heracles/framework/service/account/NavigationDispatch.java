package com.heracles.framework.service.account;

import java.util.List;
import java.util.Map;

import com.heracles.framework.cache.AppCache;
import com.heracles.framework.entity.account.Menu;
import com.heracles.framework.service.Language;
import com.heracles.framework.tools.Unit;
import com.heracles.framework.web.value.NavigationMenu;

/*
 * 页面导航菜单管理类.
 * @author 31307@sohu.com
 * 
 */

public class NavigationDispatch{

	public static Map<Long, String> getMenuMap(){
		return (Map<Long, String>)AppCache.getObjectMap("menuMap");
	}
	
	/*
	 *生成导航菜单的菜单项, 通过递归方法将结果放入到resultList中。 
	 */
	public static void retrospect(List<NavigationMenu> resultList, Long parentId){
		List<?> menuList = AppCache.getObjectList("menuList");
		for(int i = 0; i < menuList.size(); i++){
			Menu menu = (Menu) menuList.get(i);
			if (menu.getId().equals(parentId)){
				NavigationMenu nav = new NavigationMenu();
				nav.setName(menu.getName());
				nav.setPath(menu.getPath());
				nav.setTarget(menu.getTarget());
				resultList.add(nav);
				retrospect(resultList, menu.getParent());
				if (menu.getParent()==0L){
					NavigationMenu rootNav = new NavigationMenu();
					rootNav.setName(Language.getMessage("index.title"));
					rootNav.setPath("/");
					rootNav.setTarget("window");
					resultList.add(rootNav);
				}
			}
		}
	}
	
	/*
	 * 从ApplicationContent中查询某一个菜单详细的内容
	 */
	public static Menu getMenu(Long id){
		List<?> menuList = AppCache.getObjectList("menuList");
		if (Unit.isNotNull(menuList)){
			for(int i = 0; i < menuList.size(); i++){
				Menu menu = (Menu) menuList.get(i);
				if (menu.getId().equals(id)){
					return menu;
				}
			}
		}
		return null;
	}

	
}
