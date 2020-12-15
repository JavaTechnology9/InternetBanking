package com.internetbanking.service;

import com.internetbanking.token.UserToken;

public interface UserTokenService {
	public void saveToken(UserToken token);
	public UserToken validateToken(String token);
}
