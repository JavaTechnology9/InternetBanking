package com.internetbanking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internetbanking.repository.UserTokenRepository;
import com.internetbanking.resource.UserResource;
import com.internetbanking.token.UserToken;

@Service
public class UserTokenServiceImpl implements UserTokenService{
	Logger logger=LoggerFactory.getLogger(UserResource.class);
	@Autowired
	private  UserTokenRepository userTokenRepository;

	

	@Override
	public void saveToken(UserToken token) {
		userTokenRepository.saveToken(token);
		
	}



	@Override
	public UserToken validateToken(String token) {
		logger.debug("token information: {}",token);
		return userTokenRepository.validateToken(token);
	}

}
