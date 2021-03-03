package com.config.springrest.h2db.springbootrestinmemoryh2db.model;

/*
 * ConfigVO
 *   
 *     This is a config value object that is used to transfer data between layers
 */
public class ConfigVO {
	
	private String appCode;
	
	private int port;
	
	private String version;
	/**
	 * Default constructor
	 */
	public ConfigVO() {
	}
	
	/**
	 * @param appCode
	 * @param port
	 * @param version
	 */
	public ConfigVO(String appCode, int port, String version) {
		this.appCode = appCode;
		this.port = port;
		this.version = version;
	}


	/**
	 * @return the appCode
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * @param appCode the appCode to set
	 */
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfigVO [appCode=" + appCode + ", port=" + port + ", version=" + version + "]";
	}
	


}
