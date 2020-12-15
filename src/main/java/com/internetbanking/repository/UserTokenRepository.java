package com.internetbanking.repository;

import com.internetbanking.token.UserToken;

public interface UserTokenRepository {
	public UserToken validateToken(String token);
	public void saveToken(UserToken token);
	

}
