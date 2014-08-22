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
	
	@Column(name="create_at",nullable=false)
	private Date   createAt;

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

	public Date getCreate() {
		return createAt;
	}

	public void setCreate(Date create) {
		this.createAt = create;
	}
	
	
}
