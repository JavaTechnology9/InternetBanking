package com.internetbanking.service;

import org.springframework.http.ResponseEntity;

import com.internetbanking.bean.AccountAutoBean;
import com.internetbanking.model.User;

public interface UserService {
	public void  saveUser(User user);
	AccountAutoBean findByUsername(String username);
	public String  sendConfirmationMail(String emailId, String token);
	public ResponseEntity<String> updateLoginPassword(String username,String oldPassword, String newPassword);

}
