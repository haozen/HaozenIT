package com.haozen.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="t_role")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends  IdEntity{

	private static final long serialVersionUID = 1L;
	private String rolename;
	private String status;
	
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
