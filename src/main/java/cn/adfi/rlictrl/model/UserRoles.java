package cn.adfi.rlictrl.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;




@Entity
@Table(name="tbl_user_roles")
public class UserRoles {

	@EmbeddedId
	private UserRolesPK id;
	
}
