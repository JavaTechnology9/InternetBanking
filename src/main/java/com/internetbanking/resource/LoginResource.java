package com.internetbanking.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internetbanking.bean.PersonAutoBean;
import com.internetbanking.model.User;
import com.internetbanking.service.LoginService;
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4200"})
@RestController
@RequestMapping("/login")
public class LoginResource {
	@Autowired
	private LoginService loginservice;
	@PostMapping("/validate")
	public ResponseEntity<?> getUserPassword1(@RequestBody User user){
		System.out.println("calling controller");
		loginservice.validateUser(user);
		return null;
		
	}
	@PostMapping("/insert")
	public ResponseEntity<?> insertPersonInfo(@RequestBody PersonAutoBean personAutoBean){
		System.out.println("calling controller");
		//loginservice.validateUser(user);
		return null;
		
	}
	

}
