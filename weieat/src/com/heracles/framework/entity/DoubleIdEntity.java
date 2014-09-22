package com.heracles.framework.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

//JPA 基类的标识
@MappedSuperclass
public abstract class DoubleIdEntity implements Serializable {
  
	private static final long serialVersionUID = -5639142803831398059L;
	
	public DoubleIdEntity() {  
    }  

    @Override  
    public int hashCode() {  
        return 0;  
    }  
  
    @Override  
    public boolean equals(Object obj) {  
        return true;  
    } 
	
}
