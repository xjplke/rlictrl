package cn.adfi.rlictrl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_dailyauth")
public class Dailyauth {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(length=32,nullable=false)
	String uuid;
	
	@Column(length=32,nullable=false)
	String localkey;
	
	@Column(length=18,nullable=false)
	String localip;
	
	@Column(length=18,nullable=false)
	String requireip;
	
	@Column(nullable=false)
	Date   time;
	
	@Column(nullable=false)
	String request;
	
	@Column(nullable=false)
	String response;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getLocalip() {
		return localip;
	}

	public void setLocalip(String localip) {
		this.localip = localip;
	}

	public String getRequireip() {
		return requireip;
	}

	public void setRequireip(String requireip) {
		this.requireip = requireip;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}
