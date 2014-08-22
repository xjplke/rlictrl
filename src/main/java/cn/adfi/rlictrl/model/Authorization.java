package cn.adfi.rlictrl.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name="tbl_authoriz")
public class Authorization implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6163040031542000742L;

	@EmbeddedId
	private AuthPK id;
	
	@Column(length=64,nullable=false)
	private String authvalue;
	
	@Column(name="expire")
	private Date  expire;
	
	@Column(name="create_at")
	private Date  createAt;
	
	@Column(name="last_update")
	private Date  lastUpdate;

	public AuthPK getId() {
		return id;
	}

	public void setId(AuthPK id) {
		this.id = id;
	}

	public String getAuthvalue() {
		return authvalue;
	}

	public void setAuthvalue(String authvalue) {
		this.authvalue = authvalue;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public Date getCreate() {
		return createAt;
	}

	public void setCreate(Date create) {
		this.createAt = create;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
