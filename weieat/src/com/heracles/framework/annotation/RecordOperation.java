package com.heracles.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target; 

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented 

/*
 * 日志拦截接口.
 * @author 31307@sohu.com
 */

public @interface RecordOperation {
	public String fieldId();
	public String filedName();
}
