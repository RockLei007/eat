package com.heracles.framework.service.account;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.heracles.framework.dao.account.MenuDao;
import com.heracles.framework.entity.account.Menu;
import com.heracles.framework.entity.account.User;
import com.heracles.framework.service.Language;
import com.heracles.framework.service.ServiceException;
import com.heracles.framework.tools.Unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

/**
 * 界面菜单的管理类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class MenuManager {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private MenuDao menuDao;
	private AccountManager account;

	public void saveMenu(Menu entity) {
		menuDao.save(entity);
	}

	/**
	 * 使用属性过滤条件查询用户.
	 */
	@Transactional(readOnly = true)
	public Page<Menu> searchMenu(final Page<Menu> page, Map<Long, String> roleMap, final List<PropertyFilter> filters) {
		return menuDao.findPage(page, roleMap ,filters);
	}
	
	/**
	 * 按id得到一个实体.
	 */	
	public Menu getMenu(Long id){
		return menuDao.get(id);
	}
	
	public Map<Long, String> getMenuMap(){
		return menuDao.getAllMenu();
	}
	
	public List<Menu> getMenuList(){
		return menuDao.getMenuList();
	}
	
	/**
	 * 删除菜单,如果尝试删除一级菜单抛出异常.
	 */
	public void deleteMenu(Long id) {
		if (isRootMenu(id)) {
			logger.warn(Language.getMessage("MenuManager.deleteMenu.warn", new String[]{SpringSecurityUtils.getCurrentUserName()}));
			throw new ServiceException(Language.getMessage("MenuManager.deleteMenu.error"));
		}
		menuDao.delete(id);
	}

	public List<Menu> getAll(){
		List<Menu> menuList = menuDao.getAll();
		Map<Long, String> menuMap = NavigationDispatch.getMenuMap();
		for(int i = 0; i <menuList.size(); i++){
			Menu menu = menuList.get(i);
			menu.setParentName(menuMap.get(menu.getParent()));
			menuList.add(i, menu);
		}
		return menuList;
	}
	
	/**
	 * 查询一级菜单.
	 */
	@Transactional(readOnly = true)
	public List<Menu> getRootMenu(){
		return judge(menuDao.getRootMenu());
	}
	
	/**
	 * 查询一级菜单.
	 */
	@Transactional(readOnly = true)
	public List<Menu> getChildMenu(long parentId){
		return judge(menuDao.getChildMenu(parentId));
	}

	@Autowired
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	
	@Autowired
	public void setAccountManager(AccountManager account) {
		this.account = account;
	}

	/**
	 * 判断是否一级菜单.
	 */
	@Transactional(readOnly = true)
	private boolean isRootMenu(Long id) {
		Menu menu = menuDao.get(id);
		return menu.getParent() == 0;
	}

	/**
	 * 判断该用户是否有菜单的权限.
	 */
	private List<Menu> judge(List<Menu> menuList){
		if (menuList != null && menuList.size()>0){
			Iterator<Menu> iter = menuList.iterator();
			Map<Long, String> menuMap = NavigationDispatch.getMenuMap();
			while (iter.hasNext()){
				Menu menu = (Menu)iter.next();
				//插入父菜单的名字
				menu.setParentName(menuMap.get(menu.getParent()));
				String role = menu.getRole();
				String[] roles = null;
				boolean result = false;
				if (Unit.isNotNull(role)){
					roles = role.split(",");
				}
				if (roles != null && roles.length > 0){
					User user = account.findUserByLoginName(SpringSecurityUtils.getCurrentUserName());
					if (user != null){
						List<Long> userRoleIds = user.getRoleIds();
						if (userRoleIds != null && userRoleIds.size() > 0){
							for (Long id: userRoleIds){
								result = Unit.indexOfArray(roles, id);
								if (result) break;
							}
						}
					}
				}
				if (!result){
					iter.remove();
				}
			}
		}
		return menuList;
	}
 
	
	
}
