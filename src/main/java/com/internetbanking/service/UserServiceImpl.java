package com.internetbanking.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.internetbanking.model.Account;
import com.internetbanking.model.AccountType;
import com.internetbanking.model.Role;
import com.internetbanking.model.User;
import com.internetbanking.repository.AccountRepository;
import com.internetbanking.repository.UserRepository;
import com.internetbanking.resource.UserResource;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
	Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private EmailSenderService emailService;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private PersonService personService;
	@Autowired
	private CacheService cacheService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(role.getName())));
		}
		/*
		 * else { throw new UsernameNotFoundException(MessageFormat.
		 * format("User with email {0} cannot be found.", username));
		 */
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);

	}

	public String sendConfirmationMail(String userMail, String token) {
		return emailService.sendEmail(userMail,token);
		
	}

	@Override
	public void saveUser(User user) {
		final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		 userRepository.saveUser(user);
		

	}

	@Override
	public Account findByUsername(String username) {
		logger.info("findByUsername method is called with username: {}",username);
		 User user = userRepository.findByUsername(username);
		 Account account=user.getPerson().getAccount();
		 if(user!=null && user.isEnabled() && user.getPerson()!=null &&account==null) {
			  account =new Account(); 
			 int accountNumber=ThreadLocalRandom.current().nextInt(); 
			 account.setAccountNumber(String.valueOf(accountNumber));
			 account.setAccountPassword(user.getPassword());
			 account.setCreatedDate(new Date());
			 account.setAccountType(AccountType.savingAccount);
			 accountRepository.saveAccount(account);
			 cacheService.saveObject("accountInfo", account);
			 user.getPerson().setAccount(account);
			 logger.debug("person sort name: {}",user.getPerson().getSortname());
			 personService.savePerson(user.getPerson());
			
		 }
		 return account;
	}

	@Override
	public ResponseEntity<String> updateLoginPassword(String username,String oldPassword, String newPassword) {
		 User user = userRepository.findByUsername(username);
		 final boolean encryptedPassword = bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
		 String statusMessage=null;
		 if(user!=null && encryptedPassword) {
			 userRepository.saveUser(user);
			 statusMessage="New Password is updated successfully";
			 return new ResponseEntity<String>(statusMessage,HttpStatus.OK);
		 }else
			 statusMessage="Password is not matched";
		return new ResponseEntity<String>(statusMessage,HttpStatus.OK);
	}

}
