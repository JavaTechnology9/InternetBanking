package com.internetbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService{
	@Autowired
	private JavaMailSender javaMailSender;

	private static final String EMAIL_ID="javatechnology.github@gmail.com"; 

	@Override
	public String sendEmail(String userMail, String token) {
		
		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userMail);
		mailMessage.setSubject("Account Activation Link!");
		mailMessage.setFrom(EMAIL_ID);
		String message="Thank you for registering. Please click on the below link to activate your account.";
		mailMessage.setText(message+"\r\n"+ "http://localhost:3000/sign-up/confirm?"+token);
		 javaMailSender.send(mailMessage);
	
		return "Check your Email for activation";
		
		
	}

}
