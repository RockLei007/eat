package com.heracles.framework.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.heracles.framework.annotation.RecordOperation;
import com.heracles.framework.entity.account.OperationLog;
import com.heracles.framework.service.account.OperationLogManager;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.Unit;

/**
 * 操作记录类.
 * 
 * @author yinzj
 */
//Spring Bean的标识.
@Component
public class Record {

	public static Record getInstance(){
		return new Record();
	}
	
	private OperationLogManager operationLogManager;
	
	public void execute(Class<?> operationClass, HttpServletRequest request){
		Method[] methods = operationClass.getDeclaredMethods();
		for (Method method : methods) {
        	for (Annotation anno : method.getAnnotations()) {
        		if (anno.annotationType().equals(RecordOperation.class)){
        			RecordOperation c = (RecordOperation) anno;
        			if (intercept(request.getRequestURI(), method.getName())){
        				OperationLog operation = new OperationLog();
                		operation.setClassName(operationClass.getSimpleName());
                		operation.setDatetime(Datetime.getNow());
                		String l = Language.getMessage(operationClass.getSimpleName()+"."+method.getName()+".log");
                		if (Unit.isNotNull(l)){
                			operation.setDescription(l);
                		}
                		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                		operation.setIp(request.getRemoteAddr());
                		operation.setMethodName(method.getName());
                		operation.setUserName(userDetails.getUsername());
                		if (Unit.isNotNull(c.fieldId())){
                			String id = request.getParameter(c.fieldId());
                			if (Unit.isNotNull(id)){
                				operation.setKeyId(id);
                			}
                  		}
                		if (Unit.isNotNull(c.filedName())){
                			String name = request.getParameter(c.filedName());
                			if (Unit.isNotNull(name)){
                				operation.setKeyName(name);
                			}
                		}
                		operation.setOrgId(userDetails.getOrganization().getId());
                		operationLogManager.saveLog(operation);
        			}
        		}
        	}
		}
	}
	
	public void setManager(OperationLogManager operationLogManager){
		this.operationLogManager = operationLogManager;
	}
	
	private boolean intercept(String uri, String mothedName){
		int n = uri.indexOf("?");
		int i = uri.lastIndexOf("/");
		if (n > 0){
			uri = uri.substring(i, n);
		}else
			uri = uri.substring(i);	
		return uri.indexOf(mothedName) > 0;
	}

}
