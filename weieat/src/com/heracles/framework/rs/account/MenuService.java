package com.heracles.framework.rs.account;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.heracles.framework.entity.account.Menu;
import com.heracles.framework.rs.RsService;
import com.heracles.framework.rs.dto.MenuDTO;
import com.heracles.framework.service.account.MenuManager;

@Component
@Path("/menu")
public class MenuService extends RsService{
	
	@Autowired
	private MenuManager menuManager;
	
	@Autowired
	public void setMenuManager(MenuManager menuManager) {
		this.menuManager = menuManager;
	}
	
	/**
	 * 获取主菜单.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + CHARSET })
	public List<MenuDTO> getRootMenu() {
		try {
			List<Menu> entityList = menuManager.getRootMenu();

			List<MenuDTO> dtoList = Lists.newArrayList();
			for (Menu menu : entityList) {
				dtoList.add(dozer.map(menu, MenuDTO.class));
			}
			return dtoList;
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException();
		}
	}

	/**
	 * 获取子菜单.
	 */
	@GET
	@Path("{parentId}")
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + CHARSET })
	public List<MenuDTO> getChildMenu(@PathParam("parentId") Long parentId) {
		try {
			List<Menu> entityList = menuManager.getChildMenu(parentId);

			List<MenuDTO> dtoList = Lists.newArrayList();
			for (Menu menu : entityList) {
				dtoList.add(dozer.map(menu, MenuDTO.class));
			}
			return dtoList;
		} catch (ObjectNotFoundException e) {
			String message = "父菜单下的子菜单不存在(parentId:" + parentId + ")";
			logger.error(message, e);
			throw buildException(Status.NOT_FOUND, message);
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException();
		}
	}
	
}
