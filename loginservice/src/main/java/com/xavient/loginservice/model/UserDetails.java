package com.xavient.loginservice.model;

public class UserDetails {

	private String userName;
	private String password;
	private Integer userType;
	private String copName;
	private Integer copId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getCopName() {
		return copName;
	}
	public void setCopName(String copName) {
		this.copName = copName;
	}
	public Integer getCopId() {
		return copId;
	}
	public void setCopId(Integer copId) {
		this.copId = copId;
	}
}
