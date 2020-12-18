package com.internetbanking.resource;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.internetbanking.bean.AccountAutoBean;
import com.internetbanking.bean.PersonAutoBean;
import com.internetbanking.bean.UpdatePasswordBean;
import com.internetbanking.exception.InvalidTokenException;
import com.internetbanking.model.Account;
import com.internetbanking.model.Address;
import com.internetbanking.model.Contact;
import com.internetbanking.model.MonthlyAverageBalance;
import com.internetbanking.model.Person;
import com.internetbanking.model.Role;
import com.internetbanking.model.User;
import com.internetbanking.model.UserRole;
import com.internetbanking.service.AddressService;
import com.internetbanking.service.CacheService;
import com.internetbanking.service.ContactService;
import com.internetbanking.service.OTPService;
import com.internetbanking.service.PersonService;
import com.internetbanking.service.RoleService;
import com.internetbanking.service.UserService;
import com.internetbanking.service.UserTokenService;
import com.internetbanking.token.UserToken;


@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4200"})
@RestController
@RequestMapping("/user")
public class UserResource {
	Logger logger=LoggerFactory.getLogger(UserResource.class);
	@Autowired
    private UserService userService; 
	@Autowired
	private PersonService personService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private UserTokenService userTokenService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private OTPService otpService;
	@Autowired
	private CacheService cacheService;
	
	@PostMapping("/saveUser")
	public ResponseEntity<User> saveUser(@RequestBody PersonAutoBean autoBean){
		logger.info("enter into the saveUser Method");
		String personSortname=null;
		User user=new User();
		Person person=new Person();
		if(autoBean!=null) {
			
			user.setUsername(autoBean.getUsername());
			user.setPassword(autoBean.getPassword());
			UserToken token =new UserToken(UUID.randomUUID().toString());
			
			Role role=new Role();
			role.setName(1);		
			role.getUsers().add(user);
			user.getRoles().add(role);
			
			person.setFirstname(autoBean.getFirstname());
			person.setLastname(autoBean.getLastname());
			 personSortname=autoBean.getFirstname()+" "+autoBean.getLastname();
			person.setSortname(personSortname);
			person.setGender(autoBean.getGender());
			
			Address address=new Address();
			address.setAddress1(autoBean.getAddress1());
			address.setAddress2(autoBean.getAddress2());
			address.setAddress3(autoBean.getAddress3());
			address.setCity(autoBean.getCity());
			address.setState(autoBean.getStateName());
			address.setZipcode(autoBean.getZipcode());
			
			Contact contact=new Contact();
			contact.setEmail(autoBean.getEmail());
			contact.setMobilenumber(autoBean.getMobilenumber());
			contact.setPrimaryPhone(autoBean.getIsPrimaryPhone());
		
			
			addressService.saveAddress(address);
			contactService.saveContact(contact);
			person.setAddress(address);
			person.setContact(contact);
			personService.savePerson(person);
			user.setPerson(person);
			roleService.roleSave(role);
			userService.saveUser(user);
			//roleService.roleSave(role);
			token.setUser(user);
			userTokenService.saveToken(token);
			userService.sendConfirmationMail(contact.getEmail(),token.getTokenInfo());
			cacheService.saveObject("userinfo", user);
		}
		logger.info("exist the saveUser Method");
		return  new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	@PostMapping("/loginUser")
	public ResponseEntity<AccountAutoBean> loginUser(@RequestBody User user) {
		logger.info("enter into the loginUser Method");
		AccountAutoBean account = userService.findByUsername(user.getUsername());
		if(account!=null && account.getMessage()!=null){
			return new ResponseEntity<AccountAutoBean>(account,HttpStatus.ACCEPTED);
		}else
			return new ResponseEntity<AccountAutoBean>(account,HttpStatus.OK);
		
		
	}

	@PostMapping("/validateToken")
	public ResponseEntity<String> validateToken(@RequestBody String token) {
		logger.info("enter into the validateToken Method");
		String tokenValidation = null;
		try {
		if (token.contains("%")) {
				tokenValidation = token.substring(3, token.length() - 1);
				UserToken validateToken = userTokenService.validateToken(tokenValidation);
				if(validateToken==null) {
					logger.error("validateToken is invalid");
					throw new InvalidTokenException("token is invalid please try with valid token");
				}else if(validateToken.getExpiryDate()!=null) {
					throw new InvalidTokenException("Account already activated ");
				}else {
					User user = validateToken.getUser();
					validateToken.setExpiryDate(new Date());
					Calendar cal = Calendar.getInstance();
					if((validateToken.getCreatedDate().getTime() - cal.getTime().getTime()) >= 0) {
						user.setEnabled(true); 
						userService.saveUser(user);
						userTokenService.saveToken(validateToken);
						tokenValidation="Account is activated successfully";
					}else
						tokenValidation="token is expried";
				}
				
		}
		}catch(InvalidTokenException ex) {
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(tokenValidation,HttpStatus.OK);
	}
	@PostMapping("/generateOTP")
	public String generateOtp(@RequestBody String name){
		logger.info("enter into the generateOtp Method");
		if(name.contains("=")) {
			String username = name.substring(0, name.length()-1);
			otpService.generateOTP(username);
		}else
			otpService.generateOTP(name);
		return null;
	}
	
	@PostMapping("/submitOTP")
	public ResponseEntity<String> submitOTP(@RequestBody OtpInfo otpInfo){
		logger.info("enter into the submitOTP Method");
		return otpService.getOtp(otpInfo.getUsername(), otpInfo.getOtp());
		
	}
	@PostMapping("/loaduserinfo")
	public ResponseEntity<Object> loaduserinfo(@RequestBody String key){
		Object cachedObject=null;
		if(key.contains("=")) {
			String cacheKey = key.substring(0, key.length()-1);
			 cachedObject = cacheService.getObject(cacheKey);
		}else
			 cachedObject = cacheService.getObject(key);
		 return new ResponseEntity<Object>(cachedObject,HttpStatus.OK);
		
	}
	@PostMapping("/updateloginPassowrd")
	public ResponseEntity<String> updateloginPassowrd(@RequestBody UpdatePasswordBean passwordBean) {
		return userService.updateLoginPassword(passwordBean.getUsername(),passwordBean.getOldPassword(), passwordBean.getNewPassword());
		
		
	}
	public static class OtpInfo{
		public OtpInfo() {
			// TODO Auto-generated constructor stub
		}
		private String username;
		private Integer otp;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public Integer getOtp() {
			return otp;
		}
		public void setOtp(Integer otp) {
			this.otp = otp;
		}
		
		
		
	}

}
