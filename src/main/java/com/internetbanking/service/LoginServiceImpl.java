package com.internetbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internetbanking.dao.LoginDao;
import com.internetbanking.model.User;
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;

	@Override
	public boolean validateUser(User user) {
		if(user.getUsername().length()>0 && user.getPassword().length()>0)
			return true;
		return false;
	}
}
