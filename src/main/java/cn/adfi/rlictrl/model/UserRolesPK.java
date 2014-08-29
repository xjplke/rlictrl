package cn.adfi.rlictrl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class UserRolesPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2404668312466194047L;

	@Column(length=32,unique=true, nullable=false)
	private String username;
	
	@Column(length=32,name="role_name",nullable=false)
	private String rolename;
}
