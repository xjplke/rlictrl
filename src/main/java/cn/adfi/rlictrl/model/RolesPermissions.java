package cn.adfi.rlictrl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="tbl_roles_permissions")
public class RolesPermissions {
	@Id
	@Column(length=32,name="role_name",nullable=false)
	private String rolename;
	
	@Column(name="permission",nullable=false)
	private String permission;
}
