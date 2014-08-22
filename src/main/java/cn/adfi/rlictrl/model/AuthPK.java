package cn.adfi.rlictrl.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class AuthPK implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7203482358298380114L;

	@Column(length=32,nullable=false)
	private String uuid;
	
	@Column(length=64,nullable=false)
	private String authkey;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

	
}
