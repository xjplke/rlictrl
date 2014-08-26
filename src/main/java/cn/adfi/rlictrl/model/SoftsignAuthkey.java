package cn.adfi.rlictrl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_softsign_authkey")
public class SoftsignAuthkey {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;
	
	private String softsign;
	private String authkey;
	private String validate;//validate RegExp
	private String defvalue;//default value for Trial。
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSoftsign() {
		return softsign;
	}
	public void setSoftsign(String softsign) {
		this.softsign = softsign;
	}
	public String getAuthkey() {
		return authkey;
	}
	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}
	public String getValtype() {
		return validate;
	}
	public void setValtype(String valtype) {
		this.validate = valtype;
	}
	public String getDefvalue() {
		return defvalue;
	}
	public void setDefvalue(String defvalue) {
		this.defvalue = defvalue;
	}
	
	
}
