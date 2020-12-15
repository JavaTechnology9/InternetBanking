package com.internetbanking.bean;

import java.io.Serializable;

public class UpdatePasswordBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UpdatePasswordBean() {
		// TODO Auto-generated constructor stub
	}
	private String oldPassword;
	private String newPassword;

	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
