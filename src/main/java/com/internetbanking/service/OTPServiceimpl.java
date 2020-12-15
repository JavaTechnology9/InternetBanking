package com.internetbanking.service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.internetbanking.resource.UserResource;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OTPServiceimpl implements OTPService{
	Logger logger=LoggerFactory.getLogger(UserResource.class);
	private static final String TWILIO_NUMBER="+12673802908";
	private static final String ACCOUNT_SID="AC3a7f39d7860b4ce36fe630b8f7c315d5";
	private static final String AUTH_TOKEN="40dac3ef79452e969a04b3d7efa182a8";
	private LoadingCache<String, Integer> otpCache=null;
	private static final Integer EXPIRE_MNS=5;
	
	public OTPServiceimpl() {
		super();
		otpCache=CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MNS,TimeUnit.MINUTES).build(new CacheLoader<String, Integer>() {
			@Override
			public Integer load(String key) throws Exception {
				// TODO Auto-generated method stub
				return 0;
			}
		}) ;
	}

	@Override
	public void generateOTP(String name) {
		Random random=new Random();
		Integer otp=100000+random.nextInt(900000);
		otpCache.put(name, otp);
		logger.debug("generated OPT: {}",otp);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 Message.creator(new PhoneNumber("+919069128993"), new PhoneNumber(TWILIO_NUMBER),
		         "Message from Spring Boot Application:"+otp).create();
		
	}

	@Override
	public ResponseEntity<String> getOtp(String name,Integer number) {
		String status=null;
		try {
			Integer cachedOtp = otpCache.get(name);
			if(cachedOtp.equals(number)) {
				status="OTP successfully authenticated";
				otpCache.invalidate(name);
				return new ResponseEntity<String>(status, HttpStatus.OK);
			}
			else 
				status="OTP authentication failed";
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(status, HttpStatus.FORBIDDEN);
	}

}
