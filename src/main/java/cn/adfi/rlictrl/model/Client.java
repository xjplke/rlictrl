package cn.adfi.rlictrl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="client")
public class Client {
	
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid") 
	@GeneratedValue(generator="idGenerator") 
	@Column(length=32)
	private String uuid;
	
	@Column(length=32,nullable=false, unique=true)
	private String localkey;
	
	@Column(length=64,nullable=false)
	private String company;
	
	@Column(length=16,nullable=false)
	private String phone;
	
	@Column(nullable=false)
	private String softsign;
	
	@Column(name="status",nullable=false)
	private ClientStatus status;
	
	@Column(name="create_at",nullable=false)
	private Date   createAt;
	
	@Column(name="expire",nullable=false)
	private Date   expire;
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getLocalkey() {
		return localkey;
	}

	public void setLocalkey(String localkey) {
		this.localkey = localkey;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public ClientStatus getStatus() {
		return status;
	}

	public void setStatus(ClientStatus status) {
		this.status = status;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public String getSoftsign() {
		return softsign;
	}

	public void setSoftsign(String softsign) {
		this.softsign = softsign;
	}
	
}
