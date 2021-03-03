package com.config.springrest.h2db.springbootrestinmemoryh2db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * Config
 *   
 * This ia an entity class that represents the config record in the H2 database
 */

@Entity
public class Config {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;

	@Column(name="APPCODE")
	private String appCode;
	@Column(name="PORT")
	private int port;
	@Column(name="VERSION")
	private String version;
	@Column(name="LASTMODIFIEDDATE")
	private Date lastModifiedDate;

	/**
	 * Default constructor
	 */
	public Config() {
	}

	/**
	 * @param appCode
	 * @param port
	 * @param version
	 */
	public Config(String appCode, int port, String version, Date lastModifiedDate) {
		this.appCode = appCode;
		this.port = port;
		this.version = version;
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param appCode the appCode to set
	 */

	public void setAppCode(String appCode) {
		this.appCode=appCode;
	}

	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return this.appCode;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return this.version;
	}
	
	/**
	 * @param version the version to set
	 */

	public void setVersion(String version) {
		this.version=version;
	}
	
	/**
	 * @param lastModifiedDate the last modified date to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate){
		this.lastModifiedDate = lastModifiedDate;
	}
	
	/**
	 * 
	 * @return the last modified date
	 */
	public Date getLastModifiedDate(){
		return this.lastModifiedDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", appCode=" + appCode + ", port=" + port + ", version=" + version + ", lastModifiedDate=" + lastModifiedDate.toString() + "]";
	}
}
