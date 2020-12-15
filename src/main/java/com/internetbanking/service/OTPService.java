package com.internetbanking.service;

import org.springframework.http.ResponseEntity;

public interface OTPService {
	public void generateOTP(String name);
	public ResponseEntity<String> getOtp(String name,Integer number);

}
