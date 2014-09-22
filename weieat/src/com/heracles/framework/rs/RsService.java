package com.heracles.framework.rs;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * REST服务的父类.
 * 
 * @author yinzj
 */


public class RsService {
	protected static final String CHARSET = ";charset=UTF-8";
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Context
	protected UriInfo uriInfo;

	protected DozerBeanMapper dozer = new DozerBeanMapper();
	
	/**
	 * 创建WebApplicationException, 使用标准状态码与自定义信息.
	 */
	protected WebApplicationException buildException(Status status, String message) {
		return new WebApplicationException(Response.status(status).entity(message).type(MediaType.TEXT_PLAIN).build());
	}

	/**
	 * 创建WebApplicationException, 使用自定义状态码与自定义信息.
	 */
	protected WebApplicationException buildException(int status, String message) {
		return new WebApplicationException(Response.status(status).entity(message).type(MediaType.TEXT_PLAIN).build());
	}
	

}
