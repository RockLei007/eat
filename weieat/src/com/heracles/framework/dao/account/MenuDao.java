package com.heracles.framework.dao.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.heracles.framework.entity.account.Menu;
import com.heracles.framework.tools.Unit;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

/**
 * 界面菜单的泛型DAO类.
 * 
 * @author yinzj
 */
@Component
public class MenuDao extends HibernateDao<Menu, Long> {
	
	private List<Menu> menuList;
	
	public List<Menu> getRootMenu(){
		String hql = "from Menu where parent = 0 order by serial";
		return find(hql);
	}
	
	public List<Menu> getChildMenu(long parentId){
		String hql = "from Menu where parent = " + parentId + " order by serial";
		return find(hql);
	}
	
	/**
	 * 按Criteria分页查询.
	 * @param <T>
	 * @param <T>
	 * 
	 * @param page 分页参数.
	 * @param criterions 数量可变的Criterion.
	 * 
	 * @return 分页查询结果.附带结果列表及所有查询输入参数.
	 */
	@SuppressWarnings("unchecked")
	public Page<Menu> findPage(Page<Menu> page, Map<Long, String> roleMap, List<PropertyFilter> filters) {
		Criterion[] criterions = buildCriterionByPropertyFilter(filters);
		Criteria c = createCriteria(criterions);
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(c, page);

		List<Menu> result = c.list();
		
		Map<Long, String> menuMap = getAllMenu();
		for(int i = 0; i < result.size(); i++){ 
			Menu menu = result.get(i);
			menu.setParentName(menuMap.get(menu.getParent()));
			String r = menu.getRole();
			if (Unit.isNotNull(r)){
				String[] roleIds = r.split(",");
				StringBuffer sb = new StringBuffer();
				for (int n = 0; n < roleIds.length; n++){
					String t = roleMap.get(Long.valueOf(roleIds[n]));
					if (Unit.isNotNull(t)){ 
						sb.append(t);
						if (n < roleIds.length - 1) sb.append(",");
					}
				}
				menu.setRoleName(sb.toString());
			}
		}
		
		page.setResult(result);
		return page;
	}
	
	/*
	 * 为查询父菜单名提供一个map
	 */
	public Map<Long, String> getAllMenu(){
		menuList = getAll();
		Map<Long, String> menuMap = null;
		if (menuList != null && menuList.size() > 0){
			menuMap = new HashMap<Long, String>();
			for(int i = 0; i < menuList.size(); i++){
				Menu menu = menuList.get(i);
				menuMap.put(menu.getId(), menu.getName());
			}
		}
		menuMap.put(0L, "ROOT");
		return menuMap;
	}

	public List<Menu> getMenuList(){
		return menuList;
	}
	
	
}
