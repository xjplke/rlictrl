package cn.adfi.rlictrl.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable 
public class AuthPK implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7203482358298380114L;

	@Column(nullable=false)
	private UUID uuid;
	
	@Column(length=64,nullable=false)
	private String authkey;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

	
}
